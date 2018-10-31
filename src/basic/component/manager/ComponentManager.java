package basic.component.manager;

import basic.entity.*;

import java.util.ArrayList;

import basic.component.Component;

public class ComponentManager {
	private ArrayList<ECObject> objList;
	protected int type;
	
	public ComponentManager() {
		objList = new ArrayList<ECObject>();
	}
	
	public ArrayList<ECObject> getComponentList() {
		return objList;
	}
	
	public void addComponent(Entity entity, Component component) {
		ECObject obj = new ECObject(entity, component);
		objList.add(obj);
	}
	
	public void removeComponentByEntity(Entity entity) {
		for(int i = 0; i < objList.size(); i++) {
			ECObject obj = objList.get(i);
			if(obj.getEntity() == entity) {
				objList.remove(i);
				obj = null;
			}
		}
		java.lang.System.out.println("objList: " + objList.size());
	}
	
	public void clearAll() {
		objList.removeAll(objList);
	}
	
	public Entity getEntityByComponent(Component component) {
		for(int i = 0; i < objList.size(); i++) {
			ECObject obj = objList.get(i);
			if(obj.getComponent() == component) {
				return obj.getEntity();
			}
		}
		return null;
	}
	
	public Component getComponentByEntity(Entity entity) {
		for(int i = 0; i < objList.size(); i++) {
			ECObject obj = objList.get(i);
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
