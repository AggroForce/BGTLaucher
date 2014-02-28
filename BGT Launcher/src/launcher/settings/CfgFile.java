package launcher.settings;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class CfgFile{

	private File sf;
	private ArrayList<CfgGroup> nestedVals = new ArrayList<CfgGroup>();
	
	protected CfgFile(File file) {
		if(!file.exists()){
			try{
				file.createNewFile();
			}catch(IOException e){
				e.printStackTrace();
			}
		}
		this.sf = file;
	}
	
	protected CfgFile(String file){
		this(new File(file));
	}
	
	protected boolean readFile(){
		try{
			BufferedReader in = new BufferedReader(new FileReader(sf));
			
			String ln;
			while((ln=in.readLine())!=null){
				ln=ln.replaceAll("\t", "");
//				System.out.println();
				CfgValue cv;
				if((cv=this.parseValue(ln))!=null){
					System.out.println(cv);
					Config.getValueList().add(cv);
				}
			}
			in.close();
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	private int level = 0;
	protected boolean saveFile(){
		try{
			BufferedWriter bw = new BufferedWriter(new FileWriter(sf));
			for(CfgValue val : Config.getValueList()){
				if(val instanceof CfgGroup){
					bw.write(this.tabulate());
				}
			}
			return true;
		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
	}
	private String tabulate(){
		if(level != 0){
			String str = "";
			for(int i = 0; i<level; i++){
				str+="\t";
			}
			return str;
		}
		return "";
	}
	private CfgValue parseValue(String line){
		if(!line.isEmpty()){
			if(line.charAt(0)=='"'){
				String key = line.substring(1, line.indexOf('"', 1));
				String value = line.substring(line.indexOf('"', 1)+2);
				if(value.charAt(0)=='['){
					this.nestedVals.add(0,new CfgGroup(key));
					return null;
				}
				if(!this.nestedVals.isEmpty()&&this.nestedVals.get(0)!=null){
					this.nestedVals.get(0).addValue(new CfgValue(key,value));
				}else{
					return new CfgValue(key,value);
				}
				return null;
			}else if(line.charAt(0)==']'){
				CfgValue cfgval = this.nestedVals.get(0);
				this.nestedVals.remove(0);
				if(this.nestedVals.size()==0){
					return cfgval;
				}else if(this.nestedVals.size()>0){
					this.nestedVals.get(0).addValue(cfgval);
					return null;
				}else{	
					return null;
				}
			}
		}
		return null;
	}
	
}
