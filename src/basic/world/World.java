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
	
	private HashMap<Integer, ComponentManagerBase> managerMap;
	
	private WindowsSystem windows;
	private JsonSystem json;
	private GraphicsLoadSystem GraphicsLoad;
	private InputSystem input;
	private MoveSystem move;
	private PlayinglayerSystem playinglayer;
	private TextureSystem texture;
	private CollisionSystem collision;
	
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
    	collision = new CollisionSystem(this);
    	collision.instantiation();
    	
    	input.addKeyPressListener(playinglayer);
    	
    	playinglayer.addCreateBulletEvent(texture);
    	playinglayer.addCreateBulletEvent(move);
    	playinglayer.addCreateBulletEvent(collision);
    	playinglayer.addCreateEnemyEvent(texture);
    	playinglayer.addCreateEnemyEvent(move);
    	playinglayer.addCreateEnemyEvent(collision);
    	
    	
    	collision.removeBulletEvent(playinglayer);
    	collision.removeEnemyEvent(playinglayer);
    	
    	move.removeBulletEvent(playinglayer);
    	move.removeEnemyEvent(playinglayer);
	}
	
	//destory this world
	public void destory() {}
	
	///------------------------- Component -------------------------///
	public void initComponentMnager() {
		managerMap = new HashMap<Integer, ComponentManagerBase>();
		managerMap.put(ComponentType.input, new InputComponentManager());
		managerMap.put(ComponentType.move, new MoveComponentManager());
		managerMap.put(ComponentType.texture, new TextureComponentManager());
	}
	
	public void destoryComponentManager() {
		managerMap.clear();
	}
	
	// Add a component to an entity
	public void addComponent(Entity entity, ComponentBase component) {
		managerMap.get(component.getType()).addComponent(entity, component);
	}
	
	public void removeComponent(Entity entity, ComponentBase component) {
		managerMap.get(component.getType()).removeComponentByEntity(entity);
	}

	public ComponentManagerBase getComponentManagerByType(int type) {
		return managerMap.get(type);
	}
	
    
    public Entity getEntityByComponent(ComponentBase component) {
    	return managerMap.get(component.getType()).getEntityByComponent(component);
    }
    
    public ComponentBase getComponentByEntity(int ComponentType, Entity entity) {
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
    public void AddToStage(TextureComponent component) {
    	windows.addToStage(component);
    }
    
    public void removeFromStage(TextureComponent component) {
    	windows.removeFromStage(component);
    	//return textureComponent;
    }
    
    public int existInStage(TextureComponent component) {
    	return windows.existInStage(component);
    }
    
	public Image getImageByTextureComponent(TextureComponent textureComponent) {
		JSONObject jsonObject = json.getObjByName(textureComponent.getTextureName());
		Image image = GraphicsLoad.getImgageByJson(jsonObject);
		textureComponent.setImage(image);
		
		return image;
	}
	
	public void render() {
		texture.render();
		windows.render();
	}
    
	public void update() {	
//		double t = (currentNanoTime - startNanoTime) / 1000000000.0;
		move.update();
		playinglayer.update();
		collision.update();
	}
}