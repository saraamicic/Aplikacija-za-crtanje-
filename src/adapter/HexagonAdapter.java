package adapter;

import java.awt.Color;
import java.awt.Graphics;
import geometry.Shape;
import hexagon.Hexagon;

public class HexagonAdapter extends Shape {

	Hexagon hexagon;
	
	public HexagonAdapter (int x, int y, int radius)
	{
		this.hexagon = new Hexagon(x,y,radius);
	}
	
	//ovo mi ne treba
	@Override
	public void moveTo(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	//ni ovo mi ne treba
	@Override
	public void moveBy(int x, int y) {
		// TODO Auto-generated method stub
		
	}
	//ne znam jel mi ovo treba, uzela sam od kruga pa izmenila
	@Override
	public int compareTo(Object obj) {
		if (obj instanceof HexagonAdapter hex) {
			return Integer.compare(this.hexagon.getR(), hex.hexagon.getR());
		}
		return 0;
	}

	@Override
	public boolean contains(int x, int y) {
		return hexagon.doesContain(x, y);
	}
	//dodala
	public String toString() {
		return "HEXAGON " + hexagon.getX() + "," + hexagon.getY() + ";radius=" + hexagon.getR();
	}

	@Override
	public void draw(Graphics g) {
		
		hexagon.paint(g);
		
		//za selektovanje
		if (this.isSelected()) {
		    g.setColor(Color.BLUE);

		    int cx = hexagon.getX();
		    int cy = hexagon.getY();

		    // za centar
		    g.drawRect(cx - 2, cy - 2, 4, 4);

		    // temena heksagona
		    for (int i = 0; i < 6; i++) {
		        double angle = Math.toRadians(60 * i);
		        int x = (int) (cx + hexagon.getR() * Math.cos(angle));
		        int y = (int) (cy + hexagon.getR() * Math.sin(angle));

		        g.drawRect(x - 2, y - 2, 4, 4);
		    }
		}
	}
	
	
	
	public void setBorderColor(Color borderColor)
	{
		hexagon.setBorderColor(borderColor);
	}
	
	public void setAreaColor(Color areaColor)
	{
		hexagon.setAreaColor(areaColor);
	}

	
	public Color getBorderColor()
	{
		return hexagon.getBorderColor();
	}
	
	public Color getAreaColor()
	{
		return hexagon.getAreaColor();
	}
	
	public void setRadius(int radius) throws Exception 
	{
		if (radius < 0)
			throw new Exception("Radius ne sme biti negativna vrednost");
		hexagon.setR(radius);
	}
	
	public int getRadius()
	{
		return hexagon.getR();
	}
	
	public void setX(int x)
	{
		hexagon.setX(x);
	}
	
	public int getX()
	{
		return hexagon.getX();
	}
	
	public void setY(int y)
	{
		hexagon.setY(y);
	}
	
	public int getY()
	{
		return hexagon.getY();
	}
	
}
