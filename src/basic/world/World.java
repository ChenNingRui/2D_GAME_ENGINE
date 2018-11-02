package basic.world;
import java.util.HashMap;

import org.json.simple.JSONObject;

import basic.component.*;
import basic.component.manager.*;
import basic.entity.*;
import basic.system.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;

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
	}
	
	public void gameLayer(Stage primaryStage) throws Exception {
    	playinglayer = new PlayinglayerSystem(this);
    	playinglayer.instantiation();
    	texture = new TextureSystem(this);
    	windows.showWindow(primaryStage);
    	input = new InputSystem(this, primaryStage);
    	move = new MoveSystem(this);
    	
    	input.addKeyPressListener(playinglayer);
    	playinglayer.addCreateBulletListener(texture);
    	playinglayer.addCreateBulletListener(move);
    	move.addRemoveBulletListener(playinglayer);
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
    
    public WindowsComponent getWindowsComponent() {
    	return windows.getWindowsComponent();
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
    
	public Image getImageByTextureComponent(TextureComponent textureComponent) {
		JSONObject jsonObject = json.getObjByName(textureComponent.getTextureName());
		Image image = GraphicsLoad.getImgageByJson(jsonObject);
		textureComponent.setImage(image);
		
		return image;
	}
    
	public void run() {	
//		double t = (currentNanoTime - startNanoTime) / 1000000000.0;
		texture.render();
		windows.render();
		move.update();
	}
}