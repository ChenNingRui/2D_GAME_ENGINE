package basic.system;

import java.util.ArrayList;

import basic.component.*;
import basic.component.MoveComponent.ORIENTATION;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.entity.EntityHandle;
import basic.event.CreateBulletEventListener;
import basic.event.KeyBoardEventListener;
import basic.world.World;

public class PlayinglayerSystem implements System, KeyBoardEventListener{

	private World world;
	private EntityHandle player;
	private ArrayList<Entity> bulletList;
	
	private ArrayList<CreateBulletEventListener> createBulletlistenerList = new ArrayList<CreateBulletEventListener>();
	
	public PlayinglayerSystem(World world) {
		this.world = world;
	}
	
	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
		player = world.createEntity("player");
		player.addComponent(new InputComponent());
		player.addComponent(new MoveComponent(0, 20, ORIENTATION.SOUTH));
		player.addComponent(new TextureComponent("enemyRed5.png", 180, 200, 200, 1, 1));
		
		bulletList = new ArrayList<Entity>();
	}
	
	public Entity createBullet() {
		TextureComponent playTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, player.getEntity());
		EntityHandle bullet = world.createEntity("bullet" + bulletList.size());
		bullet.addComponent(new MoveComponent(0, 10, ORIENTATION.SOUTH));
		bullet.addComponent(new TextureComponent("fire03.png", 180, (playTexture.getLocationX() + 40), playTexture.getLocationY() + 10, 1, 1));
		
		bulletList.add(bullet.getEntity());
		
		return bullet.getEntity();
	}
	
	public void addCreateBulletListener(CreateBulletEventListener listener) {
		createBulletlistenerList.add(listener);
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		// TODO Auto -generated method stub
	}

	@Override
	public void onPressTheKey() {
		// TODO Auto-generated method stub
		//java.lang.System.out.print("fight");
		createBullet();
		for(CreateBulletEventListener listener : createBulletlistenerList) {
			listener.onCreateBulletEvent(bulletList);
		}
	}
}
