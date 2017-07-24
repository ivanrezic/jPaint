package hr.fer.zemris.java.hw16.jvdraw.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * <code>Circle</code> represent one oval(circle) on uor panel.
 *
 * @author Ivan Rezic
 */
public class Circle extends GeometricalObject {
	
	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which instantiates new circle.
	 *
	 * @param objectType the object type
	 * @param startX the start X
	 * @param startY the start Y
	 * @param radius the radius
	 * @param fgColor the fg color
	 * @param bgColor the bg color
	 */
	public Circle(String objectType, int startX, int startY, int radius, Color fgColor, Color bgColor) {
		super(objectType, startX, startY, radius, fgColor, bgColor);
	}

	/**
	 * Constructor which instantiates new circle.
	 *
	 * @param objectType the object type
	 * @param startX the start X
	 * @param startY the start Y
	 * @param endX the end X
	 * @param endY the end Y
	 * @param fgColor the fg color
	 * @param bgColor the bg color
	 */
	public Circle(String objectType, int startX, int startY, int endX, int endY, Color fgColor, Color bgColor) {
		super(objectType, startX, startY, endX, endY, fgColor, bgColor);
	}

	@Override
	public void paintObject(Graphics2D g2d) {
		g2d.setColor(getFgColor());
		g2d.setStroke(new BasicStroke(LINE_WIDTH));

		int radius = getRadius();
		g2d.drawOval(getStartX() - radius, getStartY() - radius, radius * 2, radius * 2);
	}

}
