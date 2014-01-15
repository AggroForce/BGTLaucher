package laucher.download;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.swing.SwingWorker;

public class FileDownload extends SwingWorker{
	
	private URL url;
	private URLConnection netcon;
	private File dest;
	
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
	
	public long getLengthBytes(){
		return netcon.getContentLengthLong();
	}
	
	@Override
	protected Object doInBackground() throws Exception {
		System.out.println("File download started...");
		try{
			InputStream in = netcon.getInputStream();
			OutputStream out = new FileOutputStream(dest);
			long prog = 0;
			long size = this.getLengthBytes();
			int i;
			while((i = in.read())!=-1){
				out.write(i);
				prog++;
				Thread.sleep(1);
				this.setProgress((int)((double)prog/(double)size)*100);
			}
			in.close();
			out.close();
		}catch(IOException e){
			e.printStackTrace();
		}
		System.out.println("File download finished...");
		return null;
	}
}
