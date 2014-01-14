import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import launcher.frame.MainFrame;


public class MainStart{
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		MainFrame frame = new MainFrame();
	}

}
