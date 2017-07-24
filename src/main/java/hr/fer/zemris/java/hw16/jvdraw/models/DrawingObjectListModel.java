package hr.fer.zemris.java.hw16.jvdraw.models;

import javax.swing.AbstractListModel;

import hr.fer.zemris.java.hw16.jvdraw.objects.GeometricalObject;

/**
 * <code>DrawingObjectListModel</code> represents our list model used for
 * communication with subject(DrawingModel) for data retreiving and then
 * displaying in right side of our application.
 *
 * @author Ivan Rezic
 */
public class DrawingObjectListModel extends AbstractListModel<GeometricalObject> implements DrawingModelListener {

	/** Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;

	/** Drawing model. */
	private DrawingModel model;

	/**
	 * Constructor which instantiates new drawing object list model.
	 *
	 * @param model
	 *            the model
	 */
	public DrawingObjectListModel(DrawingModel model) {
		this.model = model;
		this.model.addDrawingModelListener(this);
	}

	@Override
	public int getSize() {
		return model.getSize();
	}

	@Override
	public GeometricalObject getElementAt(int index) {
		return model.getObject(index);
	}

	
	@Override
	public void objectsAdded(DrawingModel source, int index0, int index1) {
		fireIntervalAdded(source, index0, index1);
	}

	@Override
	public void objectsRemoved(DrawingModel source, int index0, int index1) {
		fireIntervalRemoved(source, index0, index1);
	}

	@Override
	public void objectsChanged(DrawingModel source, int index0, int index1) {
		fireContentsChanged(source, index0, index1);
	}

}
