import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import launcher.frame.MainFrame;
import launcher.settings.Config;


public class MainStart{
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new Config("settings.cfg");
		new MainFrame();
	}

}
