package hr.fer.zemris.java.hw16.jvdraw.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * <code>Line</code> represents one line drawn on our panel.
 *
 * @author Ivan Rezic
 */
public class Line extends GeometricalObject {
	
	/**
	 * Constructor which instantiates new line.
	 *
	 * @param objectType the object type
	 * @param startX the start X
	 * @param startY the start Y
	 * @param endX the end X
	 * @param endY the end Y
	 * @param fgColor the fg color
	 */
	public Line(String objectType, int startX, int startY, int endX, int endY, Color fgColor) {
		super(objectType, startX, startY, endX, endY, fgColor);
	}

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Override
	public void paintObject(Graphics2D g2d) {
		g2d.setColor(getFgColor());
		g2d.setStroke(new BasicStroke(LINE_WIDTH));
		g2d.drawLine(getStartX(), getStartY(), getEndX(), getEndY());
	}
}
