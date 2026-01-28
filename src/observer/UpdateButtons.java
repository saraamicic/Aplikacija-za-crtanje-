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
	
		if(evt.getPropertyName().equals("Undo"))
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
		else if(evt.getPropertyName().equals("Redo"))
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
		else if(evt.getPropertyName().equals("Selection"))
		{
			if((int)evt.getNewValue()==1)
			{
				frame.getBtnDelete().setEnabled(true);
				frame.getBtnModify().setEnabled(true);
			}
			else if ((int)evt.getNewValue()>1 )
			{
				frame.getBtnDelete().setEnabled(true);
				frame.getBtnModify().setEnabled(false);
			}
			else
			{
				frame.getBtnDelete().setEnabled(false);
				frame.getBtnModify().setEnabled(false);
			}
			
		}
		else if(evt.getPropertyName().equals("BringToBack"))
		{
			if((boolean)evt.getNewValue())
			{
				frame.getBtnBringToBack().setEnabled(true);
				frame.getBtnToBack().setEnabled(true);
			}
			else
			{
				frame.getBtnBringToBack().setEnabled(false);
				frame.getBtnToBack().setEnabled(false);
			}
			
		}
		else if(evt.getPropertyName().equals("BringToFront"))
		{
			if((boolean)evt.getNewValue())
			{
				frame.getBtnBringToFront().setEnabled(true);
				frame.getBtnToFront().setEnabled(true);
			}
			else
			{
				frame.getBtnBringToFront().setEnabled(false);
				frame.getBtnToFront().setEnabled(false);
			}
			
			
		}
		
		
			
		}
		
		
	}
	