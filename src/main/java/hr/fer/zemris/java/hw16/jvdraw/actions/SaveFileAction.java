package hr.fer.zemris.java.hw16.jvdraw.actions;

import java.awt.event.ActionEvent;

import hr.fer.zemris.java.hw16.jvdraw.models.DrawingModel;

/**
 * <code>SaveFileAction</code> provides file saving.
 *
 * @author Ivan Rezic
 */
public class SaveFileAction extends MyAction {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor which instantiates new save file action.
	 *
	 * @param actionName the action name
	 * @param model the model
	 */
	public SaveFileAction(String actionName, DrawingModel model) {
		super(actionName, model);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		saveFile();
	}
}
