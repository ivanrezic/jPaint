package hr.fer.zemris.java.hw16.jvdraw.color;

import java.awt.Color;

/**
 * <code>IColorProvider</code> is subject in observer pattern it provides
 * insight in latest selected color.
 *
 * @author Ivan Rezic
 */
public interface IColorProvider {

	/**
	 * Method used for getting property <code>CurrentColor</code>.
	 *
	 * @return current color
	 */
	public Color getCurrentColor();

	/**
	 * Adds the color change listener.
	 *
	 * @param l
	 *            the l
	 */
	public void addColorChangeListener(ColorChangeListener l);

	/**
	 * Removes the color change listener.
	 *
	 * @param l
	 *            the l
	 */
	public void removeColorChangeListener(ColorChangeListener l);
}
