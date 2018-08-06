package basic.entity;

import java.util.ArrayList;

public class EntityManager {
	private static EntityManager instance;
	private ArrayList<Entity> entityList;
	
	public static EntityManager Instance() {
		if(instance == null) {
			instance = new EntityManager();
		}
		return instance;
	}
	
	public EntityManager() {
		entityList = new ArrayList<Entity>();
	}
	
	public void addEntity(Entity e){
		entityList.add(e);
	}
	
	public void removeEntity(Entity e) {
		entityList.remove(e);
	}
	
	public void clearAll() {
		entityList.clear();
	}
	
	public Entity searchEntityByName(String name) {
		Entity e;
		for(int i = 0; i < entityList.size(); i++) {
			e = entityList.get(i);
			if(e.getName() == name) {
				return e;
			}
		}
		return null;
	}
	
	public Entity searchEntityByID(int id) {
		Entity e;
		for(int i = 0; i < entityList.size(); i++) {
			e = entityList.get(i);
			if(e.getID() == id) {
				return e;
			}
		}
		return null;
	}
}
