package launcher.frame;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;

public class MainFrame extends JFrame implements ActionListener{

	public static final int width = 800;
	public static final int height = 600;
	
	public MainFrame(){
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(width, height);
		this.setLocation((screen.width/2)-(width/2), (screen.height/2)-(height/2));
		
		this.setLayout(null);
		
		JButton testButton = new JButton();
		
		testButton.setText("HI ENZO");
		testButton.setBounds(350,450, 100, 50);
		testButton.setVisible(true);
		testButton.addActionListener(this);
		
		JButton Button1 = new JButton();
		
		Button1.setText("Hello Panel 1");
		Button1.setBounds(200,200, 100, 50);
		Button1.setVisible(true);
		Button1.addActionListener(this);
		
		
		//panels
	       
	        JTabbedPane tabbedPane = new JTabbedPane();
	        
	        ImageIcon icon = new ImageIcon("knifeicon.ico");         
	        
	        
	        JComponent panel1 = makeTextPanel("Panel #1");
	        panel1.add(Button1);
	        
	        tabbedPane.addTab("Tab 1", icon, panel1,
	                "Does nothing");
	        
	        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	         
	        JComponent panel2 = makeTextPanel("Panel #2");
	        tabbedPane.addTab("Tab 2", icon, panel2,
	                "Does twice as much nothing");
	        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	         
	         
	        //Add the tabbed pane to this panel.
	      
	         
	        //The following line enables to use scrolling tabs.
	        tabbedPane.setTabPlacement(JTabbedPane.TOP);
	        tabbedPane.setSize(width, height-200);
	        tabbedPane.setTabLayoutPolicy(JTabbedPane.SCROLL_TAB_LAYOUT);
		
	        tabbedPane.setVisible(true);
	        
		this.add(tabbedPane);
		this.add(testButton);
		this.setVisible(true);
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Action: "+e.getActionCommand());
	}
	
    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel(false);
        JLabel filler = new JLabel(text);
        filler.setHorizontalAlignment(JLabel.CENTER);
        panel.setLayout(new GridLayout(1, 1));
        panel.add(filler);
        return panel;
    }
	

}
