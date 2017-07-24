package hr.fer.zemris.java.hw16.jvdraw;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import hr.fer.zemris.java.hw16.jvdraw.color.ColorChangeListener;
import hr.fer.zemris.java.hw16.jvdraw.color.IColorProvider;

/**
 * <code>StatusBar</code> represents our status bar in application which tells
 * us which colors are selected as foreground and background at any moment.
 *
 * @author Ivan Rezic
 */
public class StatusBar extends JLabel implements ColorChangeListener {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Foreground color provider. */
	private IColorProvider fgColorProvider;

	/** Background color provider. */
	private IColorProvider bgColorProvider;

	/**
	 * Constructor which instantiates new status bar.
	 *
	 * @param fgColorProvider
	 *            the fg color provider
	 * @param bgColorProvider
	 *            the bg color provider
	 */
	public StatusBar(IColorProvider fgColorProvider, IColorProvider bgColorProvider) {
		this.fgColorProvider = fgColorProvider;
		this.bgColorProvider = bgColorProvider;

		fgColorProvider.addColorChangeListener(this);
		bgColorProvider.addColorChangeListener(this);
		setBorder(BorderFactory.createMatteBorder(1, 0, 0, 0, Color.LIGHT_GRAY));
		setHorizontalAlignment(SwingConstants.CENTER);

		displayColor();
	}

	@Override
	public void newColorSelected(IColorProvider source, Color oldColor, Color newColor) {
		displayColor();
	}

	/**
	 * Displays color on status bar.
	 */
	private void displayColor() {
		Color fgSelected = fgColorProvider.getCurrentColor();
		Color bgSelected = bgColorProvider.getCurrentColor();

		String output = String.format("Foreground color: (%d, %d, %d), background color: (%d, %d, %d).",
				fgSelected.getRed(), fgSelected.getGreen(), fgSelected.getBlue(), bgSelected.getRed(),
				bgSelected.getGreen(), bgSelected.getBlue());

		setText(output);
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(getParent().getWidth(), 20);
	}
}
