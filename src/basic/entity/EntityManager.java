package basic.entity;

import java.util.ArrayList;

public class EntityManager {
	private ArrayList<Entity> list;
	
	public EntityManager() {
		list = new ArrayList<Entity>();
	}
	
	public Entity createEntity(String name) {
		Entity entity = new Entity(name);
		list.add(entity);
		return entity;
	}
	
	public void destoryEntity(Entity e) {
		for(int i = 0; i < list.size(); i++) {
			Entity temp = list.get(i);
			if(temp == e) {
				list.remove(i);
			}
		}
	}
	
	public void destoryEntityByName(String name) {
		for(int i = 0; i < list.size(); i++) {
			Entity temp = list.get(i);
			if(temp.getName() == name) {
				list.remove(temp);
			}
		}
	}
	
	public int getIndexByEntity(Entity entity) {
		return list.indexOf(entity);
	}
	
	public Entity getEntityByName(String name) {
		for(int i = 0; i < list.size(); i++) {
			Entity temp = list.get(i);
			if(temp.getName() == name) {
				return temp;
			}
		}
		return null;
	}
}
