package basic.component.manager;

import basic.entity.*;

import java.util.ArrayList;

import basic.component.Component;

public class ComponentManager {
	private ArrayList<ECSObject> objList;
	protected int type;
	
	public ComponentManager() {
		objList = new ArrayList<ECSObject>();
	}
	
	public ArrayList<ECSObject> getComponentList() {
		return objList;
	}
	
	public void addComponent(Entity entity, Component component) {
		ECSObject obj = new ECSObject(entity, component);
		objList.add(obj);
	}
	
	public void removeComponentByEntity(Entity entity) {
		for(int i = 0; i < objList.size(); i++) {
			ECSObject obj = objList.get(i);
			if(obj.getEntity() == entity) {
				objList.remove(i);
				obj = null;
			}
		}
	}
	
	public void clearAll() {
		objList.removeAll(objList);
	}
	
	public Entity getEntityByComponent(Component component) {
		for(int i = 0; i < objList.size(); i++) {
			ECSObject obj = objList.get(i);
			if(obj.getComponent() == component) {
				return obj.getEntity();
			}
		}
		return null;
	}
	
	public Component getComponentByEntity(Entity entity) {
		for(int i = 0; i < objList.size(); i++) {
			ECSObject obj = objList.get(i);
			if(obj.getEntity() == entity) {
				return obj.getComponent();
			}
		}
		return null;
	}

	public int getType() {
		return type;
	}
}
