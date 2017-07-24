package hr.fer.zemris.java.hw16.jvdraw.objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;

/**
 * <code>FilledCircle</code> represents one filled circle painted on our panel.
 *
 * @author Ivan Rezic
 */
public class FilledCircle extends GeometricalObject {

	/**
	 * Constructor which instantiates new filled circle.
	 *
	 * @param objectType the object type
	 * @param startX the start X
	 * @param startY the start Y
	 * @param radius the radius
	 * @param fgColor the fg color
	 * @param bgColor the bg color
	 */
	public FilledCircle(String objectType, int startX, int startY, int radius, Color fgColor, Color bgColor) {
		super(objectType, startX, startY, radius, fgColor, bgColor);
	}
	
	/**
	 * Constructor which instantiates new filled circle.
	 *
	 * @param objectType the object type
	 * @param startX the start X
	 * @param startY the start Y
	 * @param endX the end X
	 * @param endY the end Y
	 * @param fgColor the fg color
	 * @param bgColor the bg color
	 */
	public FilledCircle(String objectType, int startX, int startY, int endX, int endY, Color fgColor, Color bgColor) {
		super(objectType, startX, startY, endX, endY, fgColor, bgColor);
	}



	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	@Override
	public void paintObject(Graphics2D g2d) {
		g2d.setStroke(new BasicStroke(LINE_WIDTH));
		int radius = getRadius();

		g2d.setColor(getBgColor());
		g2d.fillOval(getStartX() - radius, getStartY() - radius, radius * 2, radius * 2);
		g2d.setColor(getFgColor());
		g2d.drawOval(getStartX() - radius, getStartY() - radius, radius * 2, radius * 2);
	}

}
