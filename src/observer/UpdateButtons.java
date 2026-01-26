package observer;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import mvc.DrawingFrame;

public class UpdateButtons implements PropertyChangeListener {

	private DrawingFrame frame;
	
	
	public UpdateButtons(DrawingFrame frame)
	{
		this.frame=frame;
	}
	
	@Override
	public void propertyChange(PropertyChangeEvent evt) {
	
		if(evt.getPropertyName()=="Undo")
		{
			if((boolean)evt.getNewValue())
			{
				frame.getBtnUndo().setEnabled(false);
			}
			else
			{
				frame.getBtnUndo().setEnabled(true);

			}
		}
		else if(evt.getPropertyName()=="Redo")
		{
			if((boolean)evt.getNewValue())
			{
				frame.getBtnRedo().setEnabled(false);
			}
			else
			{
				frame.getBtnRedo().setEnabled(true);

			}
			
			
		}
		
		
	}
	
	
	

}
