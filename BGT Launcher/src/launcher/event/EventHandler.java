package launcher.event;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import launcher.file.FileOperations;
import launcher.frame.MainFrame;
import launcher.game.GameLaunch;
import launcher.zip.Unzip;

public class EventHandler implements ActionListener{

	private static final EventHandler evth = new EventHandler();
	private EventHandler(){
		
	}
	
	public static EventHandler getEventBus(){
		return evth;
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
//		System.out.println(arg0);
		if(arg0.getActionCommand().equalsIgnoreCase("download")){
			FileOperations.addTaskToQueue(MainFrame.instance.fd);
			FileOperations.addTaskToQueue(new Unzip(MainFrame.instance.file,MainFrame.instance.gamedir).setUnzipCallback(MainFrame.instance));
		}else if(arg0.getActionCommand().equalsIgnoreCase("startgame")){
			GameLaunch.launchGame();
		}
	}

}
