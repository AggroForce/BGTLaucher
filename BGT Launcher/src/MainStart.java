import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import launcher.frame.MainFrame;


public class MainStart{
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		new MainFrame();
	}

}
