package launcher.frame;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

public class DebugConsole extends JFrame implements ActionListener {
	
	private static final long serialVersionUID = 1095474827443662611L;
	private Process game;
	private static DebugConsole instance;
	private StyledDocument con;
	private JTextPane ta = new JTextPane();
	private JScrollPane spane = new JScrollPane(ta);
	private JTextField tf = new JTextField();
	
	public DebugConsole(Process newGame) throws IOException{
		if(instance!=null){
			instance.dispose();
		}
		instance = this;
		game = newGame;
//			in = game.getInputStream();
//			out = game.getOutputStream();
//			err = game.getErrorStream();
		this.con = ta.getStyledDocument();
		this.setMinimumSize(new Dimension(800,600));
		this.setResizable(true);
		GroupLayout layt = new GroupLayout(this.getContentPane());
		this.getContentPane().setLayout(layt);
		this.ta.setAutoscrolls(true);
		ta.setEditable(false);
		ta.setVisible(true);
		
		StyleConstants.setForeground(con.addStyle("Error", null), Color.red);
		StyleConstants.setForeground(con.addStyle("None", null), Color.black);
		
		tf.addActionListener(this);
		
		layt.setHorizontalGroup(
			layt.createParallelGroup()
				.addComponent(spane)
				.addComponent(tf)
		);
		
		layt.setVerticalGroup(
			layt.createSequentialGroup()
				.addComponent(spane)
				.addComponent(tf, 20, 20, 20)
		);
		this.setVisible(true);
		new ProcessReader(game);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
//		System.out.println(e);
		if(e.getSource().equals(this.tf)){
			this.append(e.getActionCommand()+"\n","None");
		}
	}
	
	private void append(String str, String style){
		try {
			this.con.insertString(con.getLength(), str, con.getStyle(style));
		} catch (BadLocationException e) {
			e.printStackTrace();
		}
	}
	
	private class ProcessReader extends Thread{
		
		public InputStream in, err;
		
		public ProcessReader(Process p){
			in = p.getInputStream();
			err = p.getErrorStream();
			this.start();
		}
		
		public void run(){
			while(true){
				try{
				if(in.available()>0){
					append((char)in.read()+"", "None");
				}
				if(err.available()>0){
					append((char)err.read()+"","Error");
				}
				}catch(IOException e){
					e.printStackTrace();
				}
			}
		}
	}
}
