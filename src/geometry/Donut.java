package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Donut extends Circle {
//iz klase donut je vidljivo sve iz circle (radius, center..)

	int innerRadius;

	// konstruktori
	public Donut() {

	}

	public Donut(Point center, int radius, int innerRadius) {
		// kada pozivamo u nadklasi koristimo super , ovde pozivamo iz klase circle
		// konstruktor
		super(center, radius);
		this.innerRadius = innerRadius;
	}

	public Donut(Point center, int radius, int innerRadius, boolean selected) {

		super(center, radius, selected);
		this.innerRadius = innerRadius;
	}

	// get i set metode
	public int getInnerRadius() {
		return innerRadius;
	}

	public void setInnerRadius(int innerRadius) {
		this.innerRadius = innerRadius;
	}

	public double area() {

		return super.area() - innerRadius * innerRadius * Math.PI;
	}

	@Override
	public String toString() {
		// Center: (x,y), radius = r, innerRadius = ir
		return super.toString() + ", innerRadius =" + innerRadius;
	}

	@Override
	public boolean equals(Object obj) {

		if (obj instanceof Donut) {
			Donut pomocna = (Donut) obj;
			if (this.getCenter().equals(pomocna.getCenter()) && this.getRadius() == pomocna.getRadius()
					&& innerRadius == pomocna.getInnerRadius()) {
				return true;
			}

			else {
				return false;
			}
		} else {
			return false;
		}

	}

	@Override
	public boolean contains(int x, int y) {

		return super.contains(x, y) && this.getCenter().distance(new Point(x, y)) >= innerRadius;
	}

	@Override
	public boolean contains(Point p) {

		return this.contains(p.getXCoordinate(), p.getYCoordinate());
	}

	public void draw(Graphics g) {

		super.draw(g);
		g.setColor(borderColor);
		g.drawOval(getCenter().getXCoordinate() - innerRadius, getCenter().getYCoordinate() - innerRadius,
				2 * innerRadius, 2 * innerRadius);
		g.setColor(new Color(255,255,255));
		g.fillOval(getCenter().getXCoordinate() - innerRadius, getCenter().getYCoordinate() - innerRadius,
				2 * innerRadius, 2 * innerRadius);

		if (this.isSelected()) {
			g.setColor(Color.BLUE);
			g.drawRect(getCenter().getXCoordinate() - 2 - innerRadius, getCenter().getYCoordinate() - 2, 4, 4); // za
																												// kvadratic
																												// levo
																												// od
																												// centra
			g.drawRect(getCenter().getXCoordinate() - 2 + innerRadius, getCenter().getYCoordinate() - 2, 4, 4); // za
																												// kvadratic
																												// desno
																												// od
																												// centra
			g.drawRect(getCenter().getXCoordinate() - 2, getCenter().getYCoordinate() - 2 - innerRadius, 4, 4); // za
																												// kvadratic
																												// gore
																												// od
																												// centra
			g.drawRect(getCenter().getXCoordinate() - 2, getCenter().getYCoordinate() - 2 + innerRadius, 4, 4); // za
																												// kvadratic
																												// dole
																												// od
																												// centra
		}
		g.setColor(Color.BLACK);

	}

	public int compareTo(Object obj) {
		if (obj instanceof Donut) {
			Donut shapeToCompare = (Donut) obj;
			return (int) (this.area() - shapeToCompare.area());
		}
		return 0;
	}

}
