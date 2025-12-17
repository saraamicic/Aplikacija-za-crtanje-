package geometry;

import java.awt.Color;
import java.awt.Graphics;

public class Point extends Shape {
	private int xCoordinate;
	private int yCoordinate;

	// defaultni konstruktor
	public Point() {

	}

	// konstruktori koji imaju parametre
	public Point(int xCoordinate, int yCoordinate) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
	}
	public Point(int xCoordinate, int yCoordinate, Color color) {
		this.xCoordinate = xCoordinate;
		this.yCoordinate = yCoordinate;
		this.borderColor = color;
	}

	public Point(int xCoordinate, int yCoordinate, boolean selected) {
		this(xCoordinate, yCoordinate); // koristimo ovaj konstruktor gore da ne bismo ponavljali kod
		// ne moze jer je selected privatna this.selected = selected;
		setSelected(selected);
	}

	public void moveTo(int x, int y) {
		xCoordinate = x;
		yCoordinate = y;
	}

	public void moveBy(int x, int y) {
		xCoordinate += x;
		yCoordinate += y;
	}

	public int compareTo(Object o) {
		if (o instanceof Point) {
			Point shapeToCompare = (Point) o;
			return (int) this.distance(new Point(0, 0)) - (int) shapeToCompare.distance(new Point(0, 0));
		}
		return 0;
	}

	// Get i set metode
	public int getXCoordinate() {
		return xCoordinate;
	}

	public void setXCoordinate(int newX) {
		xCoordinate = newX;
	}

	public int getYCoordinate() {
		return yCoordinate;
	}

	public void setYCoordinate(int yCoordinate) {
		this.yCoordinate = yCoordinate;
	}

	public double distance(Point secondPoint) {
		int dx = secondPoint.xCoordinate - this.xCoordinate;
		// od druge tacke oduzimamo ovu nad kojom smo pozvali
		int dy = secondPoint.yCoordinate - this.yCoordinate;
		double result = Math.sqrt(dx * dx + dy * dy);

		return result;

	}

	public String toString() {
		return "(" + xCoordinate + "," + yCoordinate + ")";
	}

	// point1 == point2 to je poredjenje po referenci i gledamo da li imaju istu
	// memorijsku lokaciju
	// point1.equals(point2) poredjenje po jednakosti:
	// poredjenje po jednakosti
	public boolean equals(Object obj) {
		// proveramo da li je obj koji nam je prosledjen tipa point
		if (obj instanceof Point) // da li je to objekat klase point
		{
			Point pomocna = (Point) obj; // posto smo usli u if znamo da je obj point
			if (this.xCoordinate == pomocna.xCoordinate && this.yCoordinate == pomocna.yCoordinate) {
				return true; // da li su x i y coordinate tacke nad kojom smo pozvali (this) jednake x i y
								// koord tacke koju smo prosledili (pomocna)
			} else {
				return false;
			}
		} else {
			return false;
		}

	}

	public boolean contains(int x, int y) {
		return this.distance(new Point(x, y)) <= 2;
	}

	public boolean contains(Point p) {
		return this.distance(p) <= 2;
		// ovde imamo overloading - dve metode se identicno zovu ali imaju drugaciju
		// listu parametara (imaju drugaciji potpis metode)
	}

	@Override
	public void draw(Graphics g) {
		g.setColor(borderColor);
		// horizontalna linija
		g.drawLine(xCoordinate - 2, yCoordinate, xCoordinate + 2, yCoordinate);
		g.drawLine(xCoordinate, yCoordinate - 2, xCoordinate, yCoordinate + 2);

		if (this.isSelected()) {

			// crtam kvadratic plave boje 4x4 piksela
			g.setColor(Color.blue);
			g.drawRect(xCoordinate - 2, yCoordinate - 2, 4, 4);

		}
		g.setColor(Color.BLACK);
	}

	

}
