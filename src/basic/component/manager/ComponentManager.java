package basic.component.manager;

import java.util.Hashtable;
import basic.entity.*;
import basic.component.Component;

public class ComponentManager {
	private Hashtable<Entity, Component> componentDic;
	private ComponentType type;
	
	public ComponentManager() {
		componentDic = new Hashtable<Entity, Component>();
	}
	
	public void setComponentType(ComponentType type) {
		this.type = type;
	}
	
	public ComponentType getComponentType() {
		return type;
	}
	
	public void addComponent(Entity e, Component c) {
		componentDic.put(e, c);
	}
	
	public void removeComponent(Entity e) {
		componentDic.remove(e);
	}
	
	public void clearAll() {
		componentDic.clear();
	}
	
	public Component searchComponentByKey(Entity e) {
		Component c = componentDic.get(e);
		return c;
	}
}
