package basic.component.manager;

import basic.component.ComponentBase;
import basic.entity.Entity;

public class ECSObject {
	private Entity entity;
	private ComponentBase component;
	
	public ECSObject(Entity e, ComponentBase c) {
		this.entity = e;
		this.component = c;
	}
	
	public Entity getEntity() {
		return entity;
	}
	public void setEntity(Entity entity) {
		this.entity = entity;
	}
	public ComponentBase getComponent() {
		return component;
	}
	public void setComponent(ComponentBase component) {
		this.component = component;
	}

}
