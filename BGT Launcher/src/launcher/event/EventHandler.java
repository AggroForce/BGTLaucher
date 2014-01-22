package launcher.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class EventHandler implements ActionListener{

	private static final EventHandler evth = new EventHandler();
	private EventHandler(){
		
	}
	
	public static EventHandler getEventBus(){
		return evth;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println(arg0);
	}

}
