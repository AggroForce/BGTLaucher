package launcher.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;

import laucher.download.FileDownload;
import laucher.download.IProgressCallback;


@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener,IProgressCallback{

	public static final int width = 800;
	public static final int height = 600;
	JButton testButton = new JButton();
	JButton Button1 = new JButton();
	JProgressBar bar = new JProgressBar(0,100);
	FileDownload fd;
	private final String url = "https://dl.dropboxusercontent.com/u/20064876/textures.zip";
	private File file = new File("lwjgl.zip");
	
	public static MainFrame instance;
	
	public MainFrame(){
		instance = this;
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("SubRoute Launcher");
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setResizable(false);
		this.setSize(width, height);
		this.setLocation((screen.width/2)-(width/2), (screen.height/2)-(height/2));
		try {
	        this.setIconImage(ImageIO.read(new File("src/Utility7.png")));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		this.setLayout(null);
		
		fd=this.getNewThread();
		//hello
		
		testButton.setText("Dowload");
		testButton.setBounds(350,450, 100, 50);
		testButton.setVisible(true);
		testButton.addActionListener(this);

		
		Button1.setText("The Button that does the stuff");
		Button1.setBounds(300,150, 150, 50);
		Button1.setVisible(true);
		Button1.addActionListener(this);
		
		//set progress bar
		bar.setStringPainted(true);
		bar.setName("Progress");
		bar.setBounds(5, 540, 785, 30);
		bar.setValue(0);
		bar.setForeground(Color.WHITE);
		
		
		//panels
	       
	        JTabbedPane tabbedPane = new JTabbedPane();
	        
	        ImageIcon icon = new ImageIcon("knifeicon.ico");
	        
	        
	        JComponent panel1 = makeTextPanel("Panel #1");
	        panel1.setLayout(null);
	        tabbedPane.addTab("News", null, panel1,"Recent news and changes");
	        tabbedPane.setMnemonicAt(0, KeyEvent.VK_1);
	         
	        JComponent panel2 = makeTextPanel("Panel #2");
	        panel2.setLayout(null);
	        tabbedPane.addTab("Mods", null, panel2,"Official/community game mods");
	        tabbedPane.setMnemonicAt(1, KeyEvent.VK_2);
	        
	        JComponent panel3 = makeTextPanel("Panel #3");
	        panel3.setLayout(null);
	        tabbedPane.addTab("Settings", null, panel3,"Some pre-load game settings");
	        tabbedPane.setMnemonicAt(2, KeyEvent.VK_3);
	        
	        
	        
	        
	        //HTML browser
	        JEditorPane jep = new JEditorPane();
	        jep.setEditable(false);   

	        try {
	          jep.setPage("https://dl.dropboxusercontent.com/u/20064876/UpdateLog.html");
	        }catch (IOException e) {
	          jep.setContentType("text/html");
	          jep.setText("<html>Could not load</html>");
	        } 

	        JScrollPane sPane = new JScrollPane(jep);
	        sPane.setSize(800,400);
	        
	        panel1.add(sPane);
	         
	        //The following line enables to use scrolling tabs.
	        tabbedPane.setTabPlacement(JTabbedPane.TOP);
	        tabbedPane.setSize(width, height-200);
	        
	        
	        
	        //panel adding everything
	        panel3.add(Button1); 
	        this.add(bar);
	        
//	        panel1.setLayout(null);
//	        panel2.setLayout(null);
//	        panel3.setLayout(null);
	        tabbedPane.setVisible(true);
	        
		this.add(tabbedPane);
		this.add(testButton);
		this.setVisible(true);
		
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == testButton){
			System.out.println("you pressed testButton");
			System.out.println("Now DOWNLOADING...");
			if(fd.getState()==State.NEW || fd.getState()==State.TERMINATED){
				if(fd.getState()==State.TERMINATED){
					fd = this.getNewThread();
				}
				bar.setValue(0);
				this.update(this.getGraphics());
				fd.start();
			}else{
				System.err.println("Cannot Download. Thread is still active!");
			}
		}
		if(e.getSource() == Button1){
			System.out.println("you pressed Button1");
		}
	}
	

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel();
        return panel;
    }
    
	private FileDownload getNewThread(){
		return new FileDownload(this.url,this.file).setProgressCallback(this);
	}

	@Override
	public void progressUpdated(int progress) {
		bar.setValue(progress);
		bar.paint(bar.getGraphics());
	}

}
