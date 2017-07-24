package hr.fer.zemris.java.hw16.jvdraw.actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import hr.fer.zemris.java.hw16.jvdraw.models.DrawingModel;
import hr.fer.zemris.java.hw16.jvdraw.objects.Circle;
import hr.fer.zemris.java.hw16.jvdraw.objects.FilledCircle;
import hr.fer.zemris.java.hw16.jvdraw.objects.GeometricalObject;
import hr.fer.zemris.java.hw16.jvdraw.objects.Line;

/**
 * <code>OpenFileAction</code> provides file opening and importing textual
 * writen shapes.
 *
 * @author Ivan Rezic
 */
public class OpenFileAction extends MyAction {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which instantiates new open file action.
	 *
	 * @param actionName
	 *            the action name
	 * @param model
	 *            the model
	 */
	public OpenFileAction(String actionName, DrawingModel model) {
		super(actionName, model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Java draw files.", "jvd");
		fc.setFileFilter(filter);

		fc.setDialogTitle("Open file");
		if (fc.showOpenDialog(null) != JFileChooser.APPROVE_OPTION)
			return;
		File file = fc.getSelectedFile();
		Path filePath = file.toPath();
		model.setFilePath(filePath);
		System.out.println(model.getFilePath());

		if (!Files.isReadable(filePath)) {
			JOptionPane.showMessageDialog(null, "File" + filePath + "is not readable!", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		List<String> lines = null;
		try {
			lines = Files.readAllLines(filePath);
		} catch (IOException e2) {
			JOptionPane.showMessageDialog(null, "Error while reading" + filePath + ".", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		loadNewData(lines);
		model.setUpdated(false);
	}

	/**
	 * Loads new data from read files.
	 *
	 * @param lines
	 *            the lines
	 */
	private void loadNewData(List<String> lines) {
		List<GeometricalObject> temp = new ArrayList<>();
		for (int i = 0, n = model.getSize(); i < n; i++) {
			temp.add(model.getObject(i));
		}
		temp.forEach(e -> model.remove(e));

		for (String line : lines) {
			String[] parts = line.split(" ");
			int startX = Integer.valueOf(parts[1]);
			int startY = Integer.valueOf(parts[2]);

			if (parts.length == 8) {
				int endX = Integer.valueOf(parts[3]);
				int endY = Integer.valueOf(parts[4]);
				int red = Integer.valueOf(parts[5]);
				int green = Integer.valueOf(parts[6]);
				int blue = Integer.valueOf(parts[7]);
				Color color = new Color(red, green, blue);

				model.add(new Line("Line", startX, startY, endX, endY, color));
			} else if (parts.length == 7) {
				int radius = Integer.valueOf(parts[3]);
				int red = Integer.valueOf(parts[4]);
				int green = Integer.valueOf(parts[5]);
				int blue = Integer.valueOf(parts[6]);
				Color color = new Color(red, green, blue);
				System.out.println(color);

				model.add(new Circle("Circle", startX, startY, radius, color, color));
			} else if (parts.length == 10) {
				int radius = Integer.valueOf(parts[3]);
				int red1 = Integer.valueOf(parts[4]);
				int green1 = Integer.valueOf(parts[5]);
				int blue1 = Integer.valueOf(parts[6]);
				Color color1 = new Color(red1, green1, blue1);
				int red2 = Integer.valueOf(parts[7]);
				int green2 = Integer.valueOf(parts[8]);
				int blue2 = Integer.valueOf(parts[9]);
				Color color2 = new Color(red2, green2, blue2);

				model.add(new FilledCircle("Filled circle", startX, startY, radius, color1, color2));
			}
		}
	}

}
