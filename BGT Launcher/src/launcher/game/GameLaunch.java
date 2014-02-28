package launcher.game;

import java.io.File;
import java.io.IOException;
import java.util.List;

import launcher.frame.MainFrame;

public class GameLaunch {

	public static void launchGame(){
		ProcessBuilder pb = new ProcessBuilder();
		pb.directory(new File(MainFrame.instance.gamedir));
		pb.command("javaw","-jar","game.jar");
		try {
			pb.start();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
