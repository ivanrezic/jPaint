package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import hr.fer.zemris.java.hw16.jvdraw.objects.Circle;
import hr.fer.zemris.java.hw16.jvdraw.objects.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.objects.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.objects.Line;

/**
 * <code>PopupPanel</code> represents out editing shapes pop up. Depending on
 * shapes selected it provides different possibilities.
 *
 * @author Ivan Rezic
 */
public class PopupPanel extends JPanel {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Objects selected. */
	private GeometricalObject object;

	/** start X. */
	private JTextArea startX;

	/** start Y. */
	private JTextArea startY;

	/** end X. */
	private JTextArea endX;

	/** end Y. */
	private JTextArea endY;

	/** radius. */
	private JTextArea radius;

	/** foreground color. */
	private ColorChooserButton fgColor;

	/** background color. */
	private ColorChooserButton bgColor;

	/**
	 * Constructor which instantiates new popup panel.
	 *
	 * @param object
	 *            the object
	 * @param objectType
	 *            the object type
	 */
	public PopupPanel(GeometricalObject object, String objectType) {
		this.object = object;
		initElements();

		if (objectType.startsWith("Line")) {
			createEditLinePopup();
		} else {
			createEditCirclePopup();
		}
	}

	/**
	 * Inits the elements.
	 */
	private void initElements() {
		startX = new JTextArea(Integer.toString(object.getStartX()));
		startY = new JTextArea(Integer.toString(object.getStartY()));
		endX = new JTextArea(Integer.toString(object.getEndX()));
		endY = new JTextArea(Integer.toString(object.getEndY()));

		radius = new JTextArea(Integer.toString(object.getRadius()));

		fgColor = new ColorChooserButton(object.getFgColor());
		bgColor = new ColorChooserButton(object.getBgColor());

		startX.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		startY.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		endX.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		endY.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		radius.setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	/**
	 * Creates the edit line pop up.
	 */
	private void createEditLinePopup() {
		setLayout(new GridLayout(5, 2));

		add(new JLabel("Edit start X "));
		add(startX);

		add(new JLabel("Edit start Y"));
		add(startY);

		add(new JLabel("Edit end X"));
		add(endX);

		add(new JLabel("Edit end Y"));
		add(endY);

		add(new JLabel("Edit foreground color"));
		add(fgColor);
	}

	/**
	 * Creates the edit circle pop up.
	 */
	private void createEditCirclePopup() {
		if (object instanceof Circle) {
			setLayout(new GridLayout(4, 2));
		} else {
			setLayout(new GridLayout(5, 2));
		}

		add(new JLabel("Edit center X"));
		add(startX);

		add(new JLabel("Edit center Y"));
		add(startY);

		add(new JLabel("Edit radius"));
		add(radius);

		add(new JLabel("Edit foreground color"));
		add(fgColor);

		if (object instanceof FilledCircle) {
			add(new JLabel("Edit background color"));
			add(bgColor);
		}
	}

	/**
	 * Updates changed properties of selected object.
	 */
	public void updateResults() {
		object.setStartX(Integer.valueOf(startX.getText()));
		object.setStartY(Integer.valueOf(startY.getText()));

		if (object instanceof Line) {
			object.setEndX(Integer.valueOf(endX.getText()));
			object.setEndY(Integer.valueOf(endY.getText()));
		} else {
			int rad = Integer.valueOf(radius.getText());
			object.setRadius(rad);
		}

		object.setFgColor(fgColor.getSelectedColor());
		object.setBgColor(bgColor.getSelectedColor());
	}

	/**
	 * <code>ColorChooserButton</code> represents color palette.
	 *
	 * @author Ivan Rezic
	 */
	private class ColorChooserButton extends JButton {

		/** Constant serialVersionUID. */
		private static final long serialVersionUID = 1L;

		/** Current color selected.. */
		private Color current;

		/**
		 * Constructor which instantiates new color chooser button.
		 *
		 * @param c
		 *            the color
		 */
		public ColorChooserButton(Color c) {
			setSelectedColor(c);

			addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					Color newColor = JColorChooser.showDialog(null, "Choose a color", current);
					setSelectedColor(newColor);
				}
			});
		}

		/**
		 * Method used for getting property <code>SelectedColor</code>.
		 *
		 * @return selected color
		 */
		public Color getSelectedColor() {
			return current;
		}

		/**
		 * Method which sets new value as selected color.
		 *
		 * @param newColor
		 *            the new selected color
		 */
		public void setSelectedColor(Color newColor) {
			this.current = newColor;
			setBackground(newColor);
		}
	}
}
