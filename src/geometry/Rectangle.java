package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Rectangle extends Shape {

	private Point upperLeftPoint;
	private int width;
	private int height;

	// konstruktori

	public Rectangle() {

	}

	public Rectangle(Point upperLeftPoint, int w, int h) {
		this.upperLeftPoint = upperLeftPoint;
		this.width = w;
		this.height = h;
	}

	public Rectangle(Point upperLeftPoint, int w, int h, boolean selected) {
		this(upperLeftPoint, w, h);
		setSelected(selected);

	}

	public void moveTo(int x, int y) {
		upperLeftPoint.moveTo(x, y);
	}

	public void moveBy(int x, int y) {
		upperLeftPoint.moveBy(x, y);
	}

	public int compareTo(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle shapeToCompare = (Rectangle) obj;
			return this.area() - shapeToCompare.area();
		}
		return 0;
	}
	// metode pristupa

	public Point getUpperLeftPoint() {
		return upperLeftPoint;
	}

	public void setUpperLeftPoint(Point upperLeftPoint) {
		this.upperLeftPoint = upperLeftPoint;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	// povrsina i obim

	public int area()// poziva se nad pravugaonikom pa nam ne trebaju ulazni parametri
	{
		return width * height;
	}

	public int circumference() {
		return 2 * width + 2 * height;
	}

	public String toString() {

		return "RECTANGLE Upper left point: " + upperLeftPoint + ", width = " + width + ", height = " + height;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Rectangle) {
			Rectangle pomocna = (Rectangle) obj;
			if (this.upperLeftPoint == pomocna.upperLeftPoint && this.width == pomocna.width
					&& this.height == pomocna.height) {
				return true;
			} else {
				return false;
			}

		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		return (x > upperLeftPoint.getXCoordinate() && x < upperLeftPoint.getXCoordinate() + width
				&& y > upperLeftPoint.getYCoordinate() && y < upperLeftPoint.getYCoordinate() + height);
	}

	public boolean contains(Point p) {
		return this.contains(p.getXCoordinate(), p.getYCoordinate());
	}

	public void draw(Graphics g) {
		g.setColor(areaColor);
		g.fillRect(upperLeftPoint.getXCoordinate(), upperLeftPoint.getYCoordinate(), width, height);
		g.setColor(borderColor);
		g.drawRect(upperLeftPoint.getXCoordinate(), upperLeftPoint.getYCoordinate(), width, height);
		if (this.isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(upperLeftPoint.getXCoordinate() - 2, upperLeftPoint.getYCoordinate() - 2, 4, 4); // gore levo
			g.drawRect(upperLeftPoint.getXCoordinate() - 2 + width, upperLeftPoint.getYCoordinate() - 2, 4, 4); // gore
																												// desno
			g.drawRect(upperLeftPoint.getXCoordinate() - 2, upperLeftPoint.getYCoordinate() - 2 + height, 4, 4); // dole
																													// levo
			g.drawRect(upperLeftPoint.getXCoordinate() - 2 + width, upperLeftPoint.getYCoordinate() - 2 + height, 4, 4); // dole
																															// desno
		}
		g.setColor(Color.BLACK);
	}

}
