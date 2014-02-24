package launcher.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

import launcher.download.FileDownload;
import launcher.download.IProgressCallback;
import launcher.event.EventHandler;


@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener,IProgressCallback{

	public static final int width = 800;
	public static final int height = 600;
	JButton testButton = new JButton();
	JButton dlLocBtn = new JButton();
	JButton gameLocBtn = new JButton();
	JProgressBar bar = new JProgressBar(0,100);
	JTextField dlLoc = new JTextField();
	JTextField gameloc = new JTextField();
	FileDownload fd;
	private final String url = "https://dl.dropboxusercontent.com/u/20064876/zip.zip";
	private File file = new File("game.zip");
	
	public static MainFrame instance;
	
	public MainFrame(){
		instance = this;
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("SubRoute Launcher");
		this.setResizable(false);
		this.setSize(width, height);
		this.setLocation((screen.width/2)-(width/2), (screen.height/2)-(height/2));
		try {
	        this.setIconImage(ImageIO.read(new File("src/Utility7.png")));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		
		fd=this.getNewThread();
		
		testButton.setText("Download");
//		testButton.setSize(100, 50);
		testButton.setVisible(true);
		testButton.addActionListener(EventHandler.getEventBus());
		
		dlLocBtn.setText("Set");
		dlLocBtn.setPreferredSize(new Dimension(150, 50));
		dlLocBtn.setVisible(true);
		dlLocBtn.addActionListener(EventHandler.getEventBus());
		dlLocBtn.setActionCommand("dlLoc");
		
		gameLocBtn.setText("Set");
		gameLocBtn.setPreferredSize(new Dimension(150, 50));
		gameLocBtn.setVisible(true);
		gameLocBtn.addActionListener(EventHandler.getEventBus());
		gameLocBtn.setActionCommand("gameLoc");
		
		dlLoc.setText(System.getProperty("user.home")+File.separator+"Downloads"+File.separator);
		dlLoc.setMaximumSize(new Dimension(300,20));
		dlLoc.setVisible(true);
		dlLoc.setEditable(false);
		dlLoc.addActionListener(EventHandler.getEventBus());
		
		gameloc.setText((new File("")).getAbsolutePath()+File.separator+"Subroute"+File.separator);
		gameloc.setMaximumSize(new Dimension(300,20));
		gameloc.setVisible(true);
		gameloc.setEditable(false);
		gameloc.addActionListener(EventHandler.getEventBus());
		
		//set progress bar
		bar.setStringPainted(true);
		bar.setName("Progress");
		bar.setSize(785, 30);
		bar.setValue(0);
		
		
		//panels
	        JTabbedPane tabs = new JTabbedPane();
	        
	        tabs.setTabPlacement(JTabbedPane.TOP);
	        tabs.setSize(width-100, height-200);
	        tabs.setPreferredSize(new Dimension(width, height-200));
	        
	        
	        JComponent panel1 = makeTextPanel("Panel #1");
	        panel1.setBounds(tabs.getBounds());
	        panel1.setLayout(new BorderLayout());
	        tabs.addTab("News", null, panel1,"Recent news and changes");
	        tabs.setMnemonicAt(0, KeyEvent.VK_1);
	         
	        JComponent panel2 = makeTextPanel("Panel #2");
	        panel2.setBounds(tabs.getBounds());
//	        panel2.setLayout(null);
	        tabs.addTab("Mods", null, panel2,"Official/community game mods");
	        tabs.setMnemonicAt(1, KeyEvent.VK_2);
	        
	        JComponent panel3 = makeTextPanel("Panel #3");
	        panel3.setBounds(tabs.getBounds());
	        GroupLayout setlyt = new GroupLayout(panel3);
	        panel3.setLayout(setlyt);
	        tabs.addTab("Settings", null, panel3,"Some pre-load game settings");
	        tabs.setMnemonicAt(2, KeyEvent.VK_3);
	        
	        
	        
	        
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
	         
	        //The following line enables to use scrolling tabs
	        
	        
	        
	        //panel adding everything
	        setlyt.setAutoCreateGaps(true);
	        setlyt.setAutoCreateContainerGaps(true);
	        setlyt.setHorizontalGroup(
	        		setlyt.createParallelGroup().addGroup(
	        				setlyt.createSequentialGroup()
	        					.addComponent(dlLocBtn)
	        					.addComponent(dlLoc)
	        				).addGroup(
	        					setlyt.createSequentialGroup()
	        						.addComponent(gameLocBtn)
	        						.addComponent(gameloc)
	        				)
	        		);
	        setlyt.setVerticalGroup(
	        		setlyt.createSequentialGroup().addGroup(
	        				setlyt.createParallelGroup(Alignment.CENTER)
	        					.addComponent(dlLocBtn)
	        					.addComponent(dlLoc)
	        			).addGroup(
	        				setlyt.createParallelGroup(Alignment.CENTER)
	        					.addComponent(gameLocBtn)
	        					.addComponent(gameloc)
	        			)
	        		);
	        
	    BorderLayout layt = new BorderLayout();
		this.setLayout(layt);
		this.add(tabs, BorderLayout.NORTH);
		this.add(testButton, BorderLayout.CENTER);
		this.add(bar, BorderLayout.SOUTH);

		tabs.setVisible(true);
		
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
		if(e.getSource() == dlLocBtn){
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
