import basic.component.*;
import basic.component.manager.*;
import basic.entity.*;
import basic.system.*;

public class World {
	private static World _instance;
	
	//instance function
	public static World Instance() {
		if(_instance == null) {
			_instance = new World();
		}
		return _instance;
	}
	
	public World() {
	}
	
	 // Called on engine init
	public void init() {}
	
	 // Add a new system (should happen before init)
    public void addSystem() {}
	
	// Create a new entity
	public Entity createEntity(String name, int id) {
		return new Entity(name, id);
	}
	
	// Destroy an entity and all its components
	public void destroyEntity() {}
	
	// Get components for an entity
	public void unpack() {}
	
	// Add a component to an entity
	public void addComponent(ComponentManager component, Entity entity) {}
	
	// Remove a component from an entity
	public void removeComponent() {}
	
	//drawn the entity on stage in each frame
	public void render() {}
	
	//updated the entity's data in each frames
	public void update() {}

}