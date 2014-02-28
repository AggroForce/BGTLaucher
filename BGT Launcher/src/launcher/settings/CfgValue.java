package launcher.settings;

public class CfgValue {
	
	protected String key;
	protected Object value;
	
	public CfgValue(String key, Object value){
		this.key = key;
		this.value = value;
	}
	
	public Object getKey(){
		return this.key;
	}
	
	public Object getValue(){
		return this.value;
	}
	
	public String toString(){
		return "["+this.key+":"+this.value+"]";
	}
	
	protected String saveStr(){
		return "\""+this.key+"\":"+this.value;
	}
}
