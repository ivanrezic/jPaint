package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;
import javax.swing.SwingUtilities;

import hr.fer.zemris.java.hw16.jvdraw.actions.ExitAction;
import hr.fer.zemris.java.hw16.jvdraw.actions.OpenFileAction;
import hr.fer.zemris.java.hw16.jvdraw.actions.SaveAsAction;
import hr.fer.zemris.java.hw16.jvdraw.actions.SaveFileAction;
import hr.fer.zemris.java.hw16.jvdraw.color.JColorArea;
import hr.fer.zemris.java.hw16.jvdraw.models.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.models.DrawingObjectListModel;
import hr.fer.zemris.java.hw16.jvdraw.models.PaintModel;
import hr.fer.zemris.java.hw16.jvdraw.objects.GeometricalObject;

/**
 * <code>JVDraw</code> represents custom made paint application. It provides
 * line,circle and filled circle drawing. Each shape can be deleted or
 * changed.Also this app allows external importing as saving.
 *
 * @author Ivan Rezic
 */
public class JVDraw extends JFrame {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Foreground color area. */
	private JColorArea fgColorArea;

	/** Background color area. */
	private JColorArea bgColorArea;

	/** Bottom color info. */
	private StatusBar bottomColorInfo;

	/** Drawing canvas. */
	private JDrawingCanvas canvas;

	/** Drawing model. */
	private DrawingModel paintModel;

	/** Rightside list containing all shapes painted. */
	private JList<GeometricalObject> list;

	/**
	 * Constructor which instantiates new JVDraw.
	 */
	public JVDraw() {
		setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
		setBounds(20, 20, 1000, 650);
		setTitle("JVDraw");

		initGUI();
	}

	/**
	 * Inits the GUI.
	 */
	private void initGUI() {
		setLayout(new BorderLayout());
		paintModel = new PaintModel();
		fgColorArea = new JColorArea(Color.RED);
		bgColorArea = new JColorArea(Color.BLUE);

		addToolbar();
		addCanvasAndStatusBar();
		addList();
		addMenuBar();
	}

	/**
	 * Adds the toolbar.
	 */
	private void addToolbar() {
		JToolBar toolBar = new JToolBar();
		add(toolBar, BorderLayout.PAGE_START);

		toolBar.add(fgColorArea);
		toolBar.add(bgColorArea);
		toolBar.addSeparator();

		ButtonGroup group = new ButtonGroup();
		JToggleButton line = new JToggleButton("Line");
		JToggleButton circle = new JToggleButton("Circle");
		JToggleButton filledCircle = new JToggleButton("Filled circle");
		line.setSelected(true);
		group.add(line);
		group.add(circle);
		group.add(filledCircle);
		toolBar.add(line);
		toolBar.add(circle);
		toolBar.add(filledCircle);

		line.addActionListener(e -> canvas.setNewObjectType("Line"));
		circle.addActionListener(e -> canvas.setNewObjectType("Circle"));
		filledCircle.addActionListener(e -> canvas.setNewObjectType("Filled circle"));
	}

	/**
	 * Adds the canvas and status bar.
	 */
	private void addCanvasAndStatusBar() {
		bottomColorInfo = new StatusBar(fgColorArea, bgColorArea);
		add(bottomColorInfo, BorderLayout.PAGE_END);
		canvas = new JDrawingCanvas(paintModel, fgColorArea, bgColorArea);
		add(canvas, BorderLayout.CENTER);
	}

	/**
	 * Adds the list.
	 */
	private void addList() {
		DrawingObjectListModel listModel = new DrawingObjectListModel(paintModel);
		list = new JList<>(listModel);
		JScrollPane scrollPane = new JScrollPane(list);
		scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
		scrollPane.setPreferredSize(new Dimension(getWidth() / 6, getHeight()));
		add(scrollPane, BorderLayout.LINE_END);

		addListeners();
	}

	/**
	 * Adds the menu bar.
	 */
	private void addMenuBar() {
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		JMenu menu = new JMenu("File");
		menuBar.add(menu);

		JMenuItem open = new JMenuItem(new OpenFileAction("Open", paintModel));
		JMenuItem save = new JMenuItem(new SaveFileAction("Save", paintModel));
		JMenuItem saveAs = new JMenuItem(new SaveAsAction("Save As", paintModel));
		JMenuItem exit = new JMenuItem(new ExitAction("Exit", paintModel));
		menu.add(open);
		menu.add(save);
		menu.add(saveAs);
		menu.add(exit);
	}

	/**
	 * Adds the listeners, such as list mouse and action listener and windows
	 * closing listener.
	 */
	private void addListeners() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				if (paintModel.isUpdated()) {
					int result = JOptionPane.showConfirmDialog(JVDraw.this, "File not saved, do you wish to continue?");

					if (result != JOptionPane.OK_OPTION) {
						JOptionPane.showMessageDialog(JVDraw.this, "Go to file -> save or save as.");
						return;
					}
				}

				System.exit(0);
			}
		});

		list.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				@SuppressWarnings("unchecked")
				JList<GeometricalObject> list = (JList<GeometricalObject>) e.getSource();
				if (e.getClickCount() == 2) {
					GeometricalObject object = list.getSelectedValue();

					PopupPanel popupPanel = new PopupPanel(object, object.getObjectType());
					int result = JOptionPane.showConfirmDialog(JVDraw.this, popupPanel);

					if (result == JOptionPane.OK_OPTION) {
						popupPanel.updateResults();
						paintModel.change(object);
					}
				}
			}
		});

		list.addKeyListener(new KeyAdapter() {

			@Override
			public void keyPressed(KeyEvent e) {
				if (e.getKeyCode() == KeyEvent.VK_DELETE) {
					list.getSelectedValuesList().forEach(el -> paintModel.remove(el));
				}
			}

		});
	}

	/**
	 * The main method of this class, used for demonstration purposes.
	 *
	 * @param args
	 *            the arguments from command line, not used here
	 */
	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new JVDraw().setVisible(true));
	}
}
