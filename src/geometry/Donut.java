package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;

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
		return "DONUT " + getCenter().getXCoordinate() + "," + getCenter().getYCoordinate() + ";radius=" + getRadius() + ";innerRadius= " + innerRadius;
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

		Graphics2D g2d = (Graphics2D) g;
		// veci krug (crta iz gornjeg levog ugla umesto centra pa oduzmemo od x i y radius
	    Ellipse2D outerCircle = new Ellipse2D.Double(getCenter().getXCoordinate() - getRadius(),
	            getCenter().getYCoordinate() - getRadius(), 2 * getRadius(), 2 * getRadius()
	    );

	    // manji krug 
	    Ellipse2D innerCircle = new Ellipse2D.Double(getCenter().getXCoordinate() - innerRadius,
	            getCenter().getYCoordinate() - innerRadius, 2 * innerRadius, 2 * innerRadius
	    );

	    // od povrsine velikog kruga ja oduzmem taj manji, pa mi ostane samo krofna
	    Area donutArea = new Area(outerCircle);
	    donutArea.subtract(new Area(innerCircle));
	    
	    g2d.setColor(getAreaColor());
	    g2d.fill(donutArea);
	    g2d.setColor(getBorderColor());
	    g2d.draw(donutArea);

	    if (isSelected()) {
	        g2d.setColor(Color.BLUE);
	        g2d.drawRect(getCenter().getXCoordinate() - 2 - innerRadius, getCenter().getYCoordinate() - 2, 4, 4);
	        g2d.drawRect(getCenter().getXCoordinate() - 2 + innerRadius, getCenter().getYCoordinate() - 2, 4, 4);
	        g2d.drawRect(getCenter().getXCoordinate() - 2, getCenter().getYCoordinate() - 2 - innerRadius, 4, 4);
	        g2d.drawRect(getCenter().getXCoordinate() - 2, getCenter().getYCoordinate() - 2 + innerRadius, 4, 4);
	        
	        g2d.drawRect(getCenter().getXCoordinate() - 2 - getRadius(), getCenter().getYCoordinate() - 2, 4, 4);
	        g2d.drawRect(getCenter().getXCoordinate() - 2 + getRadius(), getCenter().getYCoordinate() - 2, 4, 4);
	        g2d.drawRect(getCenter().getXCoordinate() - 2, getCenter().getYCoordinate() - 2 - getRadius(), 4, 4);
	        g2d.drawRect(getCenter().getXCoordinate() - 2, getCenter().getYCoordinate() - 2 + getRadius(), 4, 4);
	    }
	}

	public int compareTo(Object obj) {
		if (obj instanceof Donut) {
			Donut shapeToCompare = (Donut) obj;
			return (int) (this.area() - shapeToCompare.area());
		}
		return 0;
	}

}
