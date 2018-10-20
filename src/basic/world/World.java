package basic.world;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import org.json.simple.JSONObject;

import basic.component.*;
import basic.component.manager.*;
import basic.entity.*;
import basic.system.*;

public class World{
	
	private HashMap<Integer, ComponentManager> managerMap;
	
	private WindowsSystem windows;
	private JsonSystem json;
	private GraphicsLoadSystem GraphicsLoad;
	private InputSystem input;
	private MoveSystem move;
	private PlayinglayerSystem playinglayer;
	private TextureSystem texture;
	
	private EntityManager entityManager;
	
	 // Add a new system (should happen before init)
    public void addSystem() {
    	windows = new WindowsSystem();
    	json = new JsonSystem();
    	GraphicsLoad = new GraphicsLoadSystem();
    }
    
	 // Called on engine init
	public void init() throws Exception {
		entityManager = new EntityManager();
		
		json.loadJson("src/Spritesheet/sheet.json");
		GraphicsLoad.loadGraphics("src/Spritesheet/sheet.png");
		windows.showWindows();
	}
	
	public void gameLayer() {
    	playinglayer = new PlayinglayerSystem(this);
    	playinglayer.instantiation();
    	input = new InputSystem(this, windows.getCanvas());
    	move = new MoveSystem(this);
    	texture = new TextureSystem(this);
	}
	
	//destory this world
	public void destory() {}
	
	///------------------------- Component -------------------------///
	public void initComponentMnager() {
		managerMap = new HashMap<Integer, ComponentManager>();
		managerMap.put(ComponentType.input, new InputComponentManager());
		managerMap.put(ComponentType.move, new MoveComponentManager());
		managerMap.put(ComponentType.texture, new TextureComponentManager());
	}
	
	public void destoryComponentManager() {
		managerMap.clear();
	}
	
	// Add a component to an entity
	public void addComponent(Entity entity, Component component) {
		managerMap.get(component.getType()).addComponent(entity, component);
	}
	
	// Remove a component from an entity
	// wakes up every 16 milliseconds, 
	// renders every Entity that has the 
	// Renderable Component, and then goes back to sleep.
	public void removeComponent(Entity entity, Component component) {
		managerMap.get(component.getType()).removeComponentByEntity(entity);
	}

	public ComponentManager getComponentManagerByType(int type) {
		return managerMap.get(type);
	}
	
    
    public Entity getEntityByComponent(Component component) {
    	return managerMap.get(component.getType()).getEntityByComponent(component);
    }
    
    public Component getComponentByEntity(int ComponentType, Entity entity) {
    	return managerMap.get(ComponentType).getComponentByEntity(entity);
    }
    
	///------------------------- entity -------------------------///
    // Destroy an entity and all its components
    public void destroyEntity(Entity e) {
    	entityManager.destoryEntityByName(e.getName());
    }
	
    // Create a new entity
    public EntityHandle createEntity(String name) {
    	Entity entity = entityManager.createEntity(name);
    	EntityHandle entityHandle = new EntityHandle(entity, this);
    	return entityHandle;
    };
	
    public Entity getEntityByName(String name) {
    	Entity entity = entityManager.getEntityByName(name);
    	return entity;
    }
	///------------------------- render -------------------------///
    public void AddToStage(TextureComponent textureComponent) {
    	windows.addToStage(textureComponent);
    }
    
    public void removeFromStage(TextureComponent textureComponent) {
    	windows.removeFromStage(textureComponent);
    }
    
	public BufferedImage getImageByTextureComponent(TextureComponent textureComponent) {
		JSONObject jsonObject = json.getObjByName(textureComponent.getTextureName());
		BufferedImage image = GraphicsLoad.getImgageByJson(jsonObject);
		textureComponent.setImage(image);
		
		return textureComponent.getImage();
	}
    
	public void run() {
		long lastTime = java.lang.System.nanoTime(); //long 2^63
		double nanoSecondConversion = 1000000000.0 / 60; //60 frames per second
		double changeInSeconds = 0;
		
		while(true) {
			long now = java.lang.System.nanoTime();

			changeInSeconds += (now - lastTime) / nanoSecondConversion;
			while(changeInSeconds >= 1) {
				update();
				changeInSeconds--;
			}

			lastTime = now;
			render();
		}
	}
	
	//drawn the entity on stage in each frame
	private void render() {
		windows.render();
	}
	
	//updated the entity's data in each frames
	private void update() {
		texture.update();
		move.update();
	}

}