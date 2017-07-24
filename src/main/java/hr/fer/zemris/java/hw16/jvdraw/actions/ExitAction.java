package hr.fer.zemris.java.hw16.jvdraw.actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;

import hr.fer.zemris.java.hw16.jvdraw.models.DrawingModel;

/**
 * <code>ExitAction</code> enables app exiting, if there are unsaved provides
 * save option.
 *
 * @author Ivan Rezic
 */
public class ExitAction extends MyAction {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which instantiates new exit action.
	 *
	 * @param actionName
	 *            the action name
	 * @param model
	 *            the model
	 */
	public ExitAction(String actionName, DrawingModel model) {
		super(actionName, model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (model.isUpdated()) {
			int result = JOptionPane.showConfirmDialog(null, "File not saved, do you wish to save?");

			if (result == JOptionPane.CANCEL_OPTION) {
				return;
			} else if (result == JOptionPane.OK_OPTION) {
				saveFile();
			}
		}

		System.exit(0);
	}

}
