package hr.fer.zemris.java.hw16.jvdraw.objects;

import java.awt.Color;
import java.awt.Graphics2D;

import javax.swing.JComponent;

import hr.fer.zemris.java.hw16.jvdraw.color.ColorChangeListener;
import hr.fer.zemris.java.hw16.jvdraw.color.IColorProvider;

/**
 * <code>GeometricalObject</code> represents all our shapes which can be drawn
 * on our panel.
 *
 * @author Ivan Rezic
 */
public abstract class GeometricalObject extends JComponent implements ColorChangeListener {

	/** Constant LINE_WIDTH. */
	protected static final int LINE_WIDTH = 2;

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Object type. */
	private String objectType;

	/** start X. */
	private int startX;

	/** start Y. */
	private int startY;

	/** end X. */
	private int endX;

	/** end Y. */
	private int endY;

	/** radius. */
	private int radius;

	/** fg color. */
	private Color fgColor;

	/** bg color. */
	private Color bgColor;

	/**
	 * Constructor which instantiates new geometrical object.
	 *
	 * @param objectType
	 *            the object type
	 * @param startX
	 *            the start X
	 * @param startY
	 *            the start Y
	 * @param endX
	 *            the end X
	 * @param endY
	 *            the end Y
	 * @param fgColor
	 *            the fg color
	 */
	public GeometricalObject(String objectType, int startX, int startY, int endX, int endY, Color fgColor) {
		this.objectType = objectType;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.fgColor = fgColor;
	}

	/**
	 * Constructor which instantiates new geometrical object.
	 *
	 * @param objectType
	 *            the object type
	 * @param startX
	 *            the start X
	 * @param startY
	 *            the start Y
	 * @param radius
	 *            the radius
	 * @param fgColor
	 *            the fg color
	 * @param bgColor
	 *            the bg color
	 */
	public GeometricalObject(String objectType, int startX, int startY, int radius, Color fgColor, Color bgColor) {
		super();
		this.objectType = objectType;
		this.startX = startX;
		this.startY = startY;
		this.radius = radius;
		this.fgColor = fgColor;
		this.bgColor = bgColor;
	}

	/**
	 * Constructor which instantiates new geometrical object.
	 *
	 * @param objectType
	 *            the object type
	 * @param startX
	 *            the start X
	 * @param startY
	 *            the start Y
	 * @param endX
	 *            the end X
	 * @param endY
	 *            the end Y
	 * @param fgColor
	 *            the fg color
	 * @param bgColor
	 *            the bg color
	 */
	public GeometricalObject(String objectType, int startX, int startY, int endX, int endY, Color fgColor,
			Color bgColor) {
		super();
		this.objectType = objectType;
		this.startX = startX;
		this.startY = startY;
		this.endX = endX;
		this.endY = endY;
		this.fgColor = fgColor;
		this.bgColor = bgColor;
	}

	/**
	 * Paints object.
	 *
	 * @param g2d
	 *            the graphich object
	 */
	public abstract void paintObject(Graphics2D g2d);

	@Override
	public void newColorSelected(IColorProvider source, Color oldColor, Color newColor) {
		source.addColorChangeListener(this);
	}

	/**
	 * Method used for getting property <code>Radius</code>.
	 *
	 * @return radius
	 */
	public int getRadius() {
		if (radius != 0) {
			return radius;
		}

		return (int) Math.hypot(getEndX() - getStartX(), getEndY() - getStartY());
	}

	/**
	 * Method which sets new value as radius.
	 *
	 * @param radius
	 *            the new radius
	 */
	public void setRadius(int radius) {
		this.radius = radius;
	}

	/**
	 * Method used for getting property <code>ObjectType</code>.
	 *
	 * @return object type
	 */
	public String getObjectType() {
		return objectType;
	}

	/**
	 * Method which sets new value as object type.
	 *
	 * @param objectType
	 *            the new object type
	 */
	public void setObjectType(String objectType) {
		this.objectType = objectType;
	}

	/**
	 * Method used for getting property <code>StartX</code>.
	 *
	 * @return start X
	 */
	public int getStartX() {
		return startX;
	}

	/**
	 * Method which sets new value as start X.
	 *
	 * @param startX
	 *            the new start X
	 */
	public void setStartX(int startX) {
		this.startX = startX;
	}

	/**
	 * Method used for getting property <code>StartY</code>.
	 *
	 * @return start Y
	 */
	public int getStartY() {
		return startY;
	}

	/**
	 * Method which sets new value as start Y.
	 *
	 * @param startY
	 *            the new start Y
	 */
	public void setStartY(int startY) {
		this.startY = startY;
	}

	/**
	 * Method used for getting property <code>EndX</code>.
	 *
	 * @return end X
	 */
	public int getEndX() {
		return endX;
	}

	/**
	 * Method which sets new value as end X.
	 *
	 * @param endX
	 *            the new end X
	 */
	public void setEndX(int endX) {
		this.endX = endX;
	}

	/**
	 * Method used for getting property <code>EndY</code>.
	 *
	 * @return end Y
	 */
	public int getEndY() {
		return endY;
	}

	/**
	 * Method which sets new value as end Y.
	 *
	 * @param endY
	 *            the new end Y
	 */
	public void setEndY(int endY) {
		this.endY = endY;
	}

	/**
	 * Method used for getting property <code>FgColor</code>.
	 *
	 * @return fg color
	 */
	public Color getFgColor() {
		return fgColor;
	}

	/**
	 * Method which sets new value as fg color.
	 *
	 * @param fgColor
	 *            the new fg color
	 */
	public void setFgColor(Color fgColor) {
		this.fgColor = fgColor;
	}

	/**
	 * Method used for getting property <code>BgColor</code>.
	 *
	 * @return bg color
	 */
	public Color getBgColor() {
		return bgColor;
	}

	/**
	 * Method which sets new value as bg color.
	 *
	 * @param bgColor
	 *            the new bg color
	 */
	public void setBgColor(Color bgColor) {
		this.bgColor = bgColor;
	}

	/**
	 * Method used for getting property <code>Serialversionuid</code>.
	 *
	 * @return serialversionuid
	 */
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@Override
	public String toString() {
		return objectType;
	}
}
