package geometry;

import java.awt.Color;
import java.awt.Graphics;
import java.io.Serializable;

public abstract class Shape implements Moveable, Comparable, Serializable  {

	private boolean selected;
	protected Color borderColor;
	protected Color areaColor;

	// konstruktori
	public Shape() {

	}

	public Shape(boolean selected) {
		this.selected = selected;
	}

	// metode pristupa, nasledjuju se u point, circle, line i sve ostale
	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	// apstraktne metode nemaju implementaciju samo potpis

	public abstract boolean contains(int x, int y); // za prosledjene koord klika proveravamo da li je sadrzana tacka na
													// nekom obliku

	public abstract void draw(Graphics g);
	
	public Color getBorderColor ()
	{
		return borderColor;
	}
	
	public void setBorderColor(Color borderColor)
	{
		this.borderColor = borderColor;
	}

	public Color getAreaColor() {
		return areaColor;
	}

	public void setAreaColor(Color areaColor) {
		this.areaColor = areaColor;
	}
	
	
}
