package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Circle extends Shape {

	private Point center;
	private int radius;

	// konstruktori

	public Circle() {

	}

	public Circle(Point center, int radius) {
		this.center = center;
		this.radius = radius;
	}

	public Circle(Point center, int radius, boolean selected) {
		this(center, radius);
		setSelected(selected);

	}

	public void moveTo(int x, int y) {
		center.moveTo(x, y);
	}

	public void moveBy(int x, int y) {
		center.moveBy(x, y);
	}

	@Override
	public int compareTo(Object obj) {
		if (obj instanceof Circle) {
			Circle shapeToCompare = (Circle) obj;
			return (int) (this.area() - shapeToCompare.area());
		}
		return 0;
	}

	// metode pristupa

	public Point getCenter() {
		return center;
	}

	public void setCenter(Point center) {
		this.center = center;
	}

	public int getRadius() {
		return radius;
	}

	public void setRadius(int radius) throws Exception // moze da baci izuzetak
	{
		if (radius < 0)// ukoliko je radius manji od nula
			throw new Exception("Radius ne sme biti negativna vrednost");
		this.radius = radius;
	}

	// povrsina i obim

	public double area() {
		return radius * radius * Math.PI;
	}

	public double circumference() {
		return 2 * radius * Math.PI;
	}

	public String toString() {
		return "CIRCLE Center: (" + center.getXCoordinate()  + ", " + center.getYCoordinate() + "), radius=" + radius;
	}

	public boolean equals(Object obj) {
		if (obj instanceof Circle) {
			Circle pomocna = (Circle) obj;
			if (this.center == pomocna.center && this.radius == pomocna.radius) {
				return true;
			} else {
				return false;
			}
		} else {
			return false;
		}
	}

	public boolean contains(int x, int y) {
		return center.distance(new Point(x, y)) <= radius;
	}

	public boolean contains(Point p) {
		// return center.distance(p) <= radius;
		return this.contains(p.getXCoordinate(), p.getYCoordinate());
	}

	@Override
	// ima implementaciju pa nije apstraktna
	public void draw(Graphics g) {
		g.setColor(areaColor);
		g.fillOval(center.getXCoordinate() - radius, center.getYCoordinate() - radius, 2 * radius, 2 * radius);
		g.setColor(borderColor);
		// obratiti paznju na potpis metode drawOwal
		g.drawOval(center.getXCoordinate() - radius, center.getYCoordinate() - radius, 2 * radius, 2 * radius);

		if (this.isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(center.getXCoordinate() - 2, center.getYCoordinate() - 2, 4, 4); // za kvadratic oko centra
			g.drawRect(center.getXCoordinate() - 2 - radius, center.getYCoordinate() - 2, 4, 4); // za kvadratic levo od
																									// centra
			g.drawRect(center.getXCoordinate() - 2 + radius, center.getYCoordinate() - 2, 4, 4); // za kvadratic desno
																									// od centra
			g.drawRect(center.getXCoordinate() - 2, center.getYCoordinate() - 2 - radius, 4, 4); // za kvadratic gore od
																									// centra
			g.drawRect(center.getXCoordinate() - 2, center.getYCoordinate() - 2 + radius, 4, 4); // za kvadratic dole od
																									// centra
		}
		g.setColor(Color.BLACK);
	}
}
