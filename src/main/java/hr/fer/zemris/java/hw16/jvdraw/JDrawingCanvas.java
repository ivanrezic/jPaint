package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.JComponent;

import hr.fer.zemris.java.hw16.jvdraw.color.IColorProvider;
import hr.fer.zemris.java.hw16.jvdraw.models.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.models.DrawingModelListener;
import hr.fer.zemris.java.hw16.jvdraw.objects.Circle;
import hr.fer.zemris.java.hw16.jvdraw.objects.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.objects.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.objects.Line;

/**
 * <code>JDrawingCanvas</code> represents our drawing pad.
 *
 * @author Ivan Rezic
 */
public class JDrawingCanvas extends JComponent implements DrawingModelListener {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Drawing model. */
	private DrawingModel model;

	/** Background color. */
	private IColorProvider bgColor;

	/** Foreground color. */
	private IColorProvider fgColor;

	/** Shape start point. */
	private Point startPoint;

	/** Shape end point. */
	private Point endPoint;

	/** Latest object drawn. */
	private GeometricalObject newObject;

	/** Latest object type. */
	private String newObjectType = "Line";

	/** Has mouse been clicked first time? */
	private boolean started;

	/**
	 * Constructor which instantiates new JDrawingCanvas.
	 *
	 * @param model
	 *            the model
	 * @param foregroundColor
	 *            the foreground color
	 * @param backgroundColor
	 *            the background color
	 */
	public JDrawingCanvas(DrawingModel model, IColorProvider foregroundColor, IColorProvider backgroundColor) {
		this.model = model;
		this.fgColor = foregroundColor;
		this.bgColor = backgroundColor;
		this.newObjectType = "Line";
		model.addDrawingModelListener(this);
		setBorder(BorderFactory.createLineBorder(Color.BLACK, 1));

		initializeListeners();
	}

	@Override
	public void objectsAdded(DrawingModel source, int index0, int index1) {
		repaint();
	}

	@Override
	public void objectsRemoved(DrawingModel source, int index0, int index1) {
		repaint();
	}

	@Override
	public void objectsChanged(DrawingModel source, int index0, int index1) {
		repaint();
	}

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g2d.setColor(Color.WHITE);
		g2d.fillRect(0, 0, getWidth(), getHeight());

		for (int i = 0, n = model.getSize(); i < n; i++) {
			model.getObject(i).paintObject(g2d);
		}

		if (newObject != null) {
			newObject.paintObject(g2d);
		}
	}

	/**
	 * Method which sets new value as new object type.
	 *
	 * @param newObjectType
	 *            the new new object type
	 */
	public void setNewObjectType(String newObjectType) {
		this.newObjectType = newObjectType;
	}

	/**
	 * Initialize listeners.
	 */
	private void initializeListeners() {
		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				started = !started;

				if (started) {
					startPoint = e.getPoint();
				} else {
					model.add(newObject);
					newObject = null;
				}
			}
		});

		addMouseMotionListener(new MouseMotionAdapter() {
			@Override
			public void mouseMoved(MouseEvent e) {
				if (started) {
					endPoint = e.getPoint();
					newObject = getNewObject(newObjectType);
					repaint();
				}
			}

			private GeometricalObject getNewObject(String newObjectType) {
				int startX = (int) startPoint.getX();
				int startY = (int) startPoint.getY();
				int endX = (int) endPoint.getX();
				int endY = (int) endPoint.getY();

				switch (newObjectType) {
				case "Line":
					return new Line(newObjectType, startX, startY, endX, endY, fgColor.getCurrentColor());
				case "Circle":
					return new Circle(newObjectType, startX, startY, endX, endY, fgColor.getCurrentColor(),
							bgColor.getCurrentColor());
				case "Filled circle":
					return new FilledCircle(newObjectType, startX, startY, endX, endY, fgColor.getCurrentColor(),
							bgColor.getCurrentColor());
				}

				return null; // never returned
			}
		});

	}
}
