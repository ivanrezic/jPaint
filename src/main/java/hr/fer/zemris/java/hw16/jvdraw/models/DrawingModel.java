package hr.fer.zemris.java.hw16.jvdraw.models;

import java.nio.file.Path;

import hr.fer.zemris.java.hw16.jvdraw.objects.GeometricalObject;

/**
 * <code>DrawingModel</code> represents subject in our observer pattern.
 * Listeners use this model to retrieve all relevant data for displaying and
 * painting.
 *
 * @author Ivan Rezic
 */
public interface DrawingModel {

	/**
	 * Method used for getting property <code>Size</code>.
	 *
	 * @return size
	 */
	public int getSize();

	/**
	 * Method used for getting property <code>Object</code>.
	 *
	 * @param index
	 *            the index
	 * @return object
	 */
	public GeometricalObject getObject(int index);

	/**
	 * Adds the.
	 *
	 * @param object
	 *            the object
	 */
	public void add(GeometricalObject object);

	/**
	 * Removes the geometrical object form objects list.
	 *
	 * @param object
	 *            the object
	 */
	public void remove(GeometricalObject object);

	/**
	 * Notifies all listeners about change.
	 *
	 * @param object
	 *            the object
	 */
	public void change(GeometricalObject object);

	/**
	 * Adds the drawing model listener.
	 *
	 * @param l
	 *            the l
	 */
	public void addDrawingModelListener(DrawingModelListener l);

	/**
	 * Removes the drawing model listener.
	 *
	 * @param l
	 *            the l
	 */
	public void removeDrawingModelListener(DrawingModelListener l);

	/**
	 * Method used for getting property <code>FilePath</code>.
	 *
	 * @return file path
	 */
	public Path getFilePath();

	/**
	 * Method which sets new value as file path.
	 *
	 * @param filePath
	 *            the new file path
	 */
	public void setFilePath(Path filePath);

	/**
	 * Checks if paint panel has been changed.
	 *
	 * @return true, if it is updated, false otherwise
	 */
	public boolean isUpdated();

	/**
	 * Method which sets new value as updated.
	 *
	 * @param updated
	 *            the new updated
	 */
	public void setUpdated(boolean updated);
}