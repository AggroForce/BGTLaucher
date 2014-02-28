package launcher.settings;

import java.util.ArrayList;

public class Config {

	private static boolean isLoaded = false;
	private static ArrayList<CfgValue> vals;
	private static CfgFile cfgfile;
	
	public Config(String file){
		if(cfgfile==null){
			cfgfile = new CfgFile(file);
			isLoaded = cfgfile.readFile();
		}
	}
	
	
	
	protected static ArrayList<CfgValue> getValueList(){
		if(vals==null){
			vals = new ArrayList<CfgValue>();
		}
		return vals;
	}
}
