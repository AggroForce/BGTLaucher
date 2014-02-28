package launcher.settings;

import java.util.ArrayList;

public class CfgGroup extends CfgValue{

	private ArrayList<CfgValue> vals;
	
	public CfgGroup(String key){
		super(key,null);
		if(this.vals==null){
			this.vals = new ArrayList<CfgValue>();
		}
	}
	
	public Object getKey(){
		return this.key;
	}
	
	public ArrayList<CfgValue> getValue(){
		return this.vals;
	}
	
	public void addValue(CfgValue val){
		this.vals.add(val);
	}
	
	public String toString(){
		String str = "";
		for(CfgValue val : vals){
			str += ","+val;
		}
		return "["+key+":{"+str.substring(1)+"}]";
	}
}
