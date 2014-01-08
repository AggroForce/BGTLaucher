package launcher.frame;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JButton;

public class MainFrame extends JFrame implements ActionListener{

	public static final int width = 800;
	public static final int height = 600;
	
	public MainFrame(){
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(width, height);
		this.setLocation((screen.width/2)-(width/2), (screen.height/2)-(height/2));
		
		JButton testButton = new JButton();
		
		testButton.setText("HI ENZO");
		testButton.setSize(100, 50);
		testButton.setVisible(true);
		testButton.addActionListener(this);
		
		this.add(testButton);
		
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action: "+e.getActionCommand());
	}
	

}
