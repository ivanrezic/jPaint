package hr.fer.zemris.java.hw16.jvdraw.color;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JColorChooser;

/**
 * <code>JColorArea</code> represents our color palette component which enables
 * user color choosing for foreground and backgroun colors.
 *
 * @author Ivan Rezic
 */
public class JColorArea extends JButton implements IColorProvider {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Current selected color. */
	private Color selectedColor;

	/** Listeners. */
	private List<ColorChangeListener> listeners;

	/**
	 * Constructor which instantiates new JColorArea.
	 *
	 * @param selectedColor
	 *            the selected color
	 */
	public JColorArea(Color selectedColor) {
		setSelectedColor(selectedColor);

		addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				Color newColor = JColorChooser.showDialog(null, "Choose color", selectedColor);
				if (newColor != null) {
					setSelectedColor(newColor);
					listeners.forEach(l -> l.newColorSelected(JColorArea.this, selectedColor, newColor));
				}
			}
		});
	}

	@Override
	public Dimension getPreferredSize() {
		return new Dimension(15, 15);
	}

	/**
	 * Method which sets new value as selected color.
	 *
	 * @param selectedColor
	 *            the new selected color
	 */
	public void setSelectedColor(Color selectedColor) {
		this.selectedColor = selectedColor;
		setBackground(selectedColor);
	}

	@Override
	public Color getCurrentColor() {
		return selectedColor;
	}

	@Override
	public void addColorChangeListener(ColorChangeListener l) {
		if (listeners == null) {
			listeners = new ArrayList<>();
		}

		listeners.add(l);
	}

	@Override
	public void removeColorChangeListener(ColorChangeListener l) {
		if (listeners == null) {
			return;
		}

		listeners.remove(l);
	}
}
