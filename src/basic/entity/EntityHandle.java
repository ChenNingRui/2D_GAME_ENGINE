package basic.entity;

import basic.component.ComponentBase;
import basic.world.World;

public class EntityHandle {
	private Entity entity;
	private World world;
	
	public EntityHandle(Entity entity, World world) {
		this.entity = entity;
		this.world = world;
	}
	
	public Entity getEntity() {
		return entity;
	}

	public void destoryEntity() {
		world.destroyEntity(entity);
	}
	
	public void removeComponent(ComponentBase component) {
		world.removeComponent(entity, component);
	}
	
	public void addComponent(ComponentBase component) {
		world.addComponent(entity ,component);
	}

}
