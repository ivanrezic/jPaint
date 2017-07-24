package hr.fer.zemris.java.hw16.jvdraw.models;

/**
 * The listener interface for receiving drawingModel events.
 * The class that is interested in processing a drawingModel
 * event implements this interface, and the object created
 * with that class is registered with a component using the
 * component's <code>addDrawingModelListener<code> method. When
 * the drawingModel event occurs, that object's appropriate
 * method is invoked.
 *
 * @see DrawingModelEvent
 */
public interface DrawingModelListener {
	
	/**
	 * Objects added.
	 *
	 * @param source the source
	 * @param index0 the index 0
	 * @param index1 the index 1
	 */
	public void objectsAdded(DrawingModel source, int index0, int index1);

	/**
	 * Objects removed.
	 *
	 * @param source the source
	 * @param index0 the index 0
	 * @param index1 the index 1
	 */
	public void objectsRemoved(DrawingModel source, int index0, int index1);

	/**
	 * Objects changed.
	 *
	 * @param source the source
	 * @param index0 the index 0
	 * @param index1 the index 1
	 */
	public void objectsChanged(DrawingModel source, int index0, int index1);
}