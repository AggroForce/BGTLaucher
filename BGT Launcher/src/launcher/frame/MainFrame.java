package launcher.frame;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.IOException;
import java.lang.Thread.State;
import java.util.logging.Logger;

import javax.imageio.ImageIO;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JEditorPane;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import launcher.download.FileDownload;
import launcher.download.IProgressCallback;
import launcher.event.EventHandler;
import launcher.file.FileOperations;
import launcher.zip.IUnzipCallback;


@SuppressWarnings("serial")
public class MainFrame extends JFrame implements ActionListener,IProgressCallback,IUnzipCallback{

	public static final int width = 800;
	public static final int height = 600;
	JButton dlBtn = new JButton();
	JButton dlLocBtn = new JButton();
	JButton gameLocBtn = new JButton();
	JButton startBtn = new JButton();
    JEditorPane jep = new JEditorPane();
	JProgressBar bar = new JProgressBar(0,100);
	JLabel plbl = new JLabel();
	JTextField dlLoc = new JTextField();
	JTextField gameloc = new JTextField();
	public String dldir = System.getProperty("user.home")+File.separator+"Downloads"+File.separator;
	public String gamedir = (new File("")).getAbsolutePath()+File.separator+"Subroute"+File.separator;
	private final String url = "https://dl.dropboxusercontent.com/u/20064876/game.zip";
	public final File file = new File(dldir,"game.zip");
	public final FileDownload fd = new FileDownload(this.url,this.file).setProgressCallback(this);
	
	public static MainFrame instance;
	
	public MainFrame(){
		instance = this;
		new FileOperations().start();
		Dimension screen = java.awt.Toolkit.getDefaultToolkit().getScreenSize();
		this.setTitle("SubRoute Launcher");
		this.setResizable(false);
		this.setSize(width, height);
		this.setLocation((screen.width/2)-(width/2), (screen.height/2)-(height/2));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		try {
	        this.setIconImage(ImageIO.read(new File("src/Utility7.png")));
	    } catch (IOException e) {
	        e.printStackTrace();
	    }
		
		dlBtn.setText("Download");
//		testButton.setSize(100, 50);
		dlBtn.setVisible(true);
		dlBtn.addActionListener(EventHandler.getEventBus());
		
		startBtn.setText("Start Game");
		startBtn.setVisible(true);
		startBtn.setActionCommand("startgame");
		startBtn.addActionListener(EventHandler.getEventBus());
		
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
		
		dlLoc.setText(dldir);
		dlLoc.setMaximumSize(new Dimension(300,20));
		dlLoc.setVisible(true);
		dlLoc.setEditable(false);
		dlLoc.addActionListener(EventHandler.getEventBus());
		
		gameloc.setText(gamedir);
		gameloc.setMaximumSize(new Dimension(300,20));
		gameloc.setVisible(true);
		gameloc.setEditable(false);
		gameloc.addActionListener(EventHandler.getEventBus());
		
		plbl.setText("0%");
		plbl.setHorizontalTextPosition(JLabel.CENTER);
		plbl.setHorizontalAlignment(JLabel.CENTER);
		bar.setLayout(new BorderLayout());
		bar.setValue(0);
		bar.setPreferredSize(new Dimension(700,50));
		bar.add(plbl,BorderLayout.CENTER);
		
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
	        
	        jep.setEditable(false);    

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
	        
	    GroupLayout layt = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layt);
		layt.setHorizontalGroup(
				layt.createParallelGroup(Alignment.CENTER)
					.addComponent(tabs)
					.addComponent(dlBtn)
					.addComponent(startBtn,Alignment.TRAILING)
					.addComponent(bar)
		);
		layt.setVerticalGroup(
				layt.createSequentialGroup()
					.addComponent(tabs).addGap(10)
					.addGroup(layt.createParallelGroup()
						.addComponent(dlBtn)
						.addComponent(startBtn)
					).addGap(5)
					.addComponent(bar).addGap(5)
		);

		tabs.setVisible(true);
		
		this.setVisible(true);
		this.loadWebPages(); 
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == dlBtn){
			System.out.println("you pressed testButton");
			System.out.println("Now DOWNLOADING...");
			
		}
		if(e.getSource() == dlLocBtn){
			System.out.println("you pressed Button1");
		}
	}
	
	protected void loadWebPages(){
        try {
	        jep.setPage("https://dl.dropboxusercontent.com/u/20064876/UpdateLog.html");
	    }catch (IOException e) {
	        jep.setContentType("text/html");
	        jep.setText("<html>Could not load</html>");
	    }
	}

    protected JComponent makeTextPanel(String text) {
        JPanel panel = new JPanel();
        return panel;
    }

	@Override
	public void progressUpdated(int progress) {
		bar.setValue(progress);
		plbl.setText(progress+"%");
		bar.paint(bar.getGraphics());
	}

	@Override
	public void progressUpdated(int i, String filename) {
		bar.setValue(i);
		plbl.setText(i+"%:"+filename);
		bar.paint(bar.getGraphics());
	}

}
