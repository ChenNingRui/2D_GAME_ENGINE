package basic.component.manager;

import basic.component.Component;
import basic.entity.Entity;

public class ECSObject {
	private Entity entity;
	private Component component;
	
	public ECSObject(Entity e, Component c) {
		this.entity = e;
		this.component = c;
	}
	
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public Component getComponent() {
		return component;
	}
	public void setComponent(Component component) {
		this.component = component;
	}

}
