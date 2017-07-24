package hr.fer.zemris.java.hw16.jvdraw.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.StringJoiner;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;

import hr.fer.zemris.java.hw16.jvdraw.models.DrawingModel;

/**
 * <code>MyAction</code> encapsulates all actions provided in file menu.
 *
 * @author Ivan Rezic
 */
public abstract class MyAction extends AbstractAction {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Drawing model. */
	protected DrawingModel model;

	/**
	 * Constructor which instantiates new MyAction.
	 *
	 * @param actionName
	 *            the action name
	 * @param model
	 *            the model
	 */
	public MyAction(String actionName, DrawingModel model) {
		this.putValue(Action.NAME, actionName);
		this.model = model;
	}

	@Override
	public abstract void actionPerformed(ActionEvent e);

	/**
	 * Method used for getting property <code>NewFileText</code> which contains
	 * all shapes in textual representation.
	 *
	 * @param model
	 *            Drawing model.
	 * @return new file text
	 */
	protected byte[] getNewFileText(DrawingModel model) {
		StringJoiner joiner = new StringJoiner(System.lineSeparator());

		for (int i = 0, n = model.getSize(); i < n; i++) {
			String objectType = model.getObject(i).getObjectType();
			String startX = String.valueOf(model.getObject(i).getStartX());
			String startY = String.valueOf(model.getObject(i).getStartY());
			String endX = String.valueOf(model.getObject(i).getEndX());
			String endY = String.valueOf(model.getObject(i).getEndY());
			String radius = String.valueOf(model.getObject(i).getRadius());
			Color fgColor = model.getObject(i).getFgColor();
			Color bgColor = model.getObject(i).getBgColor();

			String line = null;
			if (objectType.startsWith("Line")) {
				line = String.format("%s %s %s %s %s %s %s %s", "LINE", startX, startY, endX, endY,
						String.valueOf(fgColor.getRed()), String.valueOf(fgColor.getGreen()),
						String.valueOf(fgColor.getBlue()));
			} else if (objectType.startsWith("Circle")) {
				line = String.format("%s %s %s %s %s %s %s", "CIRCLE", startX, startY, radius,
						String.valueOf(fgColor.getRed()), String.valueOf(fgColor.getGreen()),
						String.valueOf(fgColor.getBlue()));
			} else if (objectType.startsWith("Filled")) {
				line = String.format("%s %s %s %s %s %s %s %s %s %s", "FCIRCLE", startX, startY, radius,
						String.valueOf(fgColor.getRed()), String.valueOf(fgColor.getGreen()),
						String.valueOf(fgColor.getBlue()), String.valueOf(bgColor.getRed()),
						String.valueOf(bgColor.getGreen()), String.valueOf(bgColor.getBlue()));
			}
			joiner.add(line);
		}

		return joiner.toString().getBytes();
	}

	/**
	 * Save file action which check if file has been saved initially, if not asks to do so.
	 */
	protected void saveFile() {
		Path openedFilePath = model.getFilePath();
		if (openedFilePath == null) {
			JOptionPane.showMessageDialog(null, "First save as, then you can save.", "Warning",
					JOptionPane.INFORMATION_MESSAGE);
			return;
		}

		try {
			Files.write(openedFilePath, getNewFileText(model));
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Saving aborted! File status not clear.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(null, "File saved.", "Information", JOptionPane.INFORMATION_MESSAGE);
		model.setUpdated(false);
	}
}
