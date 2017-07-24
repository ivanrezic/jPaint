package hr.fer.zemris.java.hw16.jvdraw.models;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import hr.fer.zemris.java.hw16.jvdraw.objects.GeometricalObject;

/**
 * <code>PaintModel</code> is implementation of DrawingModel and it is main
 * subject of application. It conatins all relevant data for painting and
 * displaying.
 *
 * @author Ivan Rezic
 */
public class PaintModel implements DrawingModel {

	/** Geometrical objects. */
	private List<GeometricalObject> objects;

	/** Listeners. */
	private List<DrawingModelListener> listeners;

	/** Number of lines. */
	private int numberOfLines;

	/** Number of circles. */
	private int numberOfCircles;

	/** Number of filled circles. */
	private int numberOfFilledCircles;

	/** Current opened file. */
	private Path file;

	/** Whether paint has been changed from last saving. */
	private boolean updated;

	@Override
	public int getSize() {
		return objects == null ? 0 : objects.size();
	}

	@Override
	public GeometricalObject getObject(int index) {
		return objects.get(index);
	}

	@Override
	public void add(GeometricalObject object) {
		if (objects == null) {
			objects = new ArrayList<>();
		}

		numerateObject(object);
		objects.add(object);
		listeners.forEach(e -> e.objectsAdded(this, getSize() - 1, getSize() - 1));
		setUpdated(true);
	}

	@Override
	public void remove(GeometricalObject object) {
		if (objects == null || object == null) {
			return;
		}

		int index = objects.indexOf(object);
		objects.remove(object);
		listeners.forEach(e -> e.objectsRemoved(this, index, index));
		setUpdated(true);
	}

	@Override
	public void change(GeometricalObject object) {
		int index = objects.indexOf(object);
		objects.set(index, object);

		listeners.forEach(e -> e.objectsChanged(this, index, index));
		setUpdated(true);
	}

	@Override
	public Path getFilePath() {
		return file;
	}

	@Override
	public void setFilePath(Path filePath) {
		this.file = filePath;
	}

	@Override
	public boolean isUpdated() {
		return updated;
	}

	@Override
	public void setUpdated(boolean updated) {
		this.updated = updated;
	}

	/**
	 * Helper method which adds unique number to shape name which shows its all
	 * time order.
	 *
	 * @param object
	 *            the object
	 */
	private void numerateObject(GeometricalObject object) {
		switch (object.getObjectType()) {
		case "Line":
			object.setObjectType(object.getObjectType() + " " + ++numberOfLines);
			break;
		case "Circle":
			object.setObjectType(object.getObjectType() + " " + ++numberOfCircles);
			break;
		case "Filled circle":
			object.setObjectType(object.getObjectType() + " " + ++numberOfFilledCircles);
			break;
		}
	}

	@Override
	public void addDrawingModelListener(DrawingModelListener l) {
		if (listeners == null) {
			listeners = new ArrayList<>();
		}

		listeners.add(l);
	}

	@Override
	public void removeDrawingModelListener(DrawingModelListener l) {
		if (listeners == null) {
			return;
		}

		listeners.remove(l);
	}

	/**
	 * Method used for getting property <code>NumberOfLines</code>.
	 *
	 * @return number of lines
	 */
	public int getNumberOfLines() {
		return numberOfLines;
	}

	/**
	 * Method which sets new value as number of lines.
	 *
	 * @param numberOfLines
	 *            the new number of lines
	 */
	public void setNumberOfLines(int numberOfLines) {
		this.numberOfLines = numberOfLines;
	}

	/**
	 * Method used for getting property <code>NumberOfCircles</code>.
	 *
	 * @return number of circles
	 */
	public int getNumberOfCircles() {
		return numberOfCircles;
	}

	/**
	 * Method which sets new value as number of circles.
	 *
	 * @param numberOfCircles
	 *            the new number of circles
	 */
	public void setNumberOfCircles(int numberOfCircles) {
		this.numberOfCircles = numberOfCircles;
	}

	/**
	 * Method used for getting property <code>NumberOfFilledCircles</code>.
	 *
	 * @return number of filled circles
	 */
	public int getNumberOfFilledCircles() {
		return numberOfFilledCircles;
	}

	/**
	 * Method which sets new value as number of filled circles.
	 *
	 * @param numberOfFilledCircles
	 *            the new number of filled circles
	 */
	public void setNumberOfFilledCircles(int numberOfFilledCircles) {
		this.numberOfFilledCircles = numberOfFilledCircles;
	}
}
