package basic.component.manager;

import basic.entity.*;

import java.util.ArrayList;

import basic.component.ComponentBase;

public abstract class ComponentManagerBase {
	private ArrayList<ECSObject> objList;
	protected int type;
	
	public ComponentManagerBase() {
		objList = new ArrayList<ECSObject>();
	}
	
	public ArrayList<ECSObject> getComponentList() {
		return objList;
	}
	
	public void addComponent(Entity entity, ComponentBase component) {
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
	
	public Entity getEntityByComponent(ComponentBase component) {
		for(int i = 0; i < objList.size(); i++) {
			ECSObject obj = objList.get(i);
			if(obj.getComponent() == component) {
				return obj.getEntity();
			}
		}
		return null;
	}
	
	public ComponentBase getComponentByEntity(Entity entity) {
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
