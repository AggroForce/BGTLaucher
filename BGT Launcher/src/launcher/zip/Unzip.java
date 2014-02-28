package launcher.zip;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import launcher.file.IFileTask;

public class Unzip implements IFileTask {
	
	private File file;
	private String dir;
	private IUnzipCallback callback = null;
	
	public Unzip(File src, String destDir){
		this.file = src;
		this.dir = destDir;
	}
	
	public Unzip setUnzipCallback(IUnzipCallback callback){
		this.callback=callback;
		return this;
	}
	
	public void executeTask(){
		try{
			ZipInputStream in = new ZipInputStream(new FileInputStream(file));
			
			File folder = new File(dir);
			
			if(!folder.exists()){
				folder.mkdirs();
			}
			
			
			ZipEntry entry;
			while((entry=in.getNextEntry())!=null){
				File out = new File(folder,entry.getName());
				
				out.getParentFile().mkdirs();
				
				if(!entry.isDirectory()){
					FileOutputStream fw = new FileOutputStream(out);
					
					long prog = 0;
					int progress = 0;
					int sigval = 0;
					int b;
					while((b=in.read())!=-1){
						fw.write(b);
						prog+=1;
						progress=(int)(((double)prog/(double)entry.getSize())*100d);
						if(sigval!=progress&&this.callback!=null){
							this.callback.progressUpdated(sigval=progress, entry.getName());
						}
					}
					
					fw.close();
					fw = null;
				}
			}
			in.closeEntry();
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}
