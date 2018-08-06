package basic.event;

import java.util.ArrayList;

public class SystemEvent {
	protected ArrayList<ECSEventListener> listener;
	
	public SystemEvent() {}
	
	protected void init() {
		listener = new ArrayList<ECSEventListener>();
	}

	public void addEventListener(ECSEventListener Listener) {
		this.listener.add(Listener);
	}
	
	public void removeEventListener(ECSEventListener Listener) {
		this.listener.remove(Listener);
	}
	
	//generating the event
	protected void generateEvent(String name){
		ECSEvent me = new ECSEvent(this, name);
		notifyListener(me);
	}
	
	//noticed all listener that events have been actived
	protected void notifyListener(ECSEvent e){
		listener.forEach(listener -> listener.systemEvent(e));
	}

}
