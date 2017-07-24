package hr.fer.zemris.java.hw16.jvdraw.actions;

import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

import hr.fer.zemris.java.hw16.jvdraw.models.DrawingModel;

/**
 * <code>SaveAsAction</code> provides initial file saving and overridng existing files.
 *
 * @author Ivan Rezic
 */
public class SaveAsAction extends MyAction {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which instantiates new save as action.
	 *
	 * @param actionName the action name
	 * @param model the model
	 */
	public SaveAsAction(String actionName, DrawingModel model) {
		super(actionName, model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		JFileChooser fc = new JFileChooser();
		FileNameExtensionFilter filter = new FileNameExtensionFilter("Java draw files.", "jvd");
		fc.setFileFilter(filter);
		fc.setDialogTitle("Save As file");
		
		if (fc.showSaveDialog(null) != JFileChooser.APPROVE_OPTION) {
			JOptionPane.showMessageDialog(null, "Saving canceled.", "Information", JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if (fc.getSelectedFile().exists()) {
			int choose = JOptionPane.showConfirmDialog(null, "Do you want to overwrite existing file?", "",
					JOptionPane.YES_NO_OPTION);
			if (choose != JOptionPane.YES_OPTION)
				return;
		}

		File newFilePath = fc.getSelectedFile();
		File newFile = null;
		if (newFilePath.getPath().endsWith(".jvd")) {			
			newFile = new File(newFilePath.getPath());
		}else {			
			newFile = new File(newFilePath.getPath() + ".jvd");
		}
		try {
			Files.write(newFile.toPath(), getNewFileText(model));
			model.setFilePath(newFile.toPath());
		} catch (IOException e1) {
			JOptionPane.showMessageDialog(null, "Saving aborted! File status not clear.", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}

		JOptionPane.showMessageDialog(null, "File saved.", "Information", JOptionPane.INFORMATION_MESSAGE);
		model.setUpdated(false);
	}
}