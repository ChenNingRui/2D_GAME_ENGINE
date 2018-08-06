package basic.event;

import java.util.EventObject;

public class ECSEvent extends EventObject {
	
	private String name;
	private Object source;
	
	public ECSEvent(Object source, String name) {
		super(source);
		
		this.name = name;
		this.source = source;
	}
	
	public Object getObj() {
		return this.source;
	}
	
	public String getName() {
		return this.name;
	}
}
