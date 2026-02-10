package command;

import adapter.HexagonAdapter;

public class ModifyHexagonCmd implements Command {

	
	private HexagonAdapter hexagon;
	private HexagonAdapter newState;
	private HexagonAdapter oldState = new HexagonAdapter(0,0,0);
	
	public ModifyHexagonCmd (HexagonAdapter hexagon, HexagonAdapter newState)
	{
		this.hexagon = hexagon;
		this.newState = newState;
	}
	
	@Override
	public void execute() {
		oldState.setX(hexagon.getX());
		oldState.setY(hexagon.getY());
		try {
			oldState.setRadius(hexagon.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		oldState.setBorderColor(hexagon.getBorderColor());
		oldState.setAreaColor(hexagon.getAreaColor());
		
		hexagon.setX(newState.getX());
		hexagon.setY(newState.getY());
		try {
			hexagon.setRadius(newState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		hexagon.setBorderColor(newState.getBorderColor());
		hexagon.setAreaColor(newState.getAreaColor());
		
	}

	@Override
	public void unexecute() {
		
		hexagon.setX(oldState.getX());
		hexagon.setY(oldState.getY());
		try {
			hexagon.setRadius(oldState.getRadius());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		hexagon.setBorderColor(oldState.getBorderColor());
		hexagon.setAreaColor(oldState.getAreaColor());

	}
	
	public String toString()
	{
		return "MODIFY--" + oldState.toString() + ";Border color:" + oldState.getBorderColor().getRGB() + ";Area color:" + oldState.getAreaColor().getRGB()
				+ ">>" + newState.toString() + ";Border color:" + newState.getBorderColor().getRGB() + ";Area color:" + newState.getAreaColor().getRGB();
	}
	

}
