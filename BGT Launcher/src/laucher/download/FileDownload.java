package laucher.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.SwingWorker;

import launcher.frame.MainFrame;

public class FileDownload extends Thread{
	
	private URL url;
	private URLConnection netcon;
	private File dest;
	public int progress = 0;
	
	public FileDownload(String url,File dest){
		this.dest = dest;
		try{
			this.url = new URL(url);
			this.netcon = this.url.openConnection();
			this.netcon.connect();
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void run(){
		this.download();
	}
	
	public long getLengthBytes(){
		return netcon.getContentLengthLong();
	}
	
	public void download(){
		System.out.println("File download started...");
		try{
			InputStream in = netcon.getInputStream();
			OutputStream out = new FileOutputStream(dest);
			int lastprogress = 0;
			long prog = 0;
			long size = this.getLengthBytes();
			int i;
			while((i = in.read())!=-1){
				out.write(i);
				prog += 1;
				progress = (int)(((double)prog/(double)size)*100d);
				if(lastprogress != progress){
					lastprogress = progress;
					MainFrame.instance.updateProgress(this.progress);
				}
			}
			in.close();
			out.close();
		}catch(Exception e){
			e.printStackTrace();
		}
		System.out.println("File download finished...");
	}
}
