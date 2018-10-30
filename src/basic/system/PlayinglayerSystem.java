package basic.system;

import java.util.ArrayList;

import basic.component.*;
import basic.component.MoveComponent.ORIENTATION;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.entity.EntityHandle;
import basic.event.KeyBoardEventListener;
import basic.world.World;

public class PlayinglayerSystem implements System, KeyBoardEventListener{

	private World world;
	private EntityHandle player;
	private ArrayList<Entity> bulletPool;
	
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
		
		bulletPool = new ArrayList<Entity>();
	}
	
	public Entity createBullet() {
		TextureComponent playTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, player.getEntity());
		EntityHandle bullet = world.createEntity("bullet" + bulletPool.size());
		bullet.addComponent(new MoveComponent(0, 10, ORIENTATION.SOUTH));
		player.addComponent(new TextureComponent("beam1.png", 180, playTexture.getLocationX(), playTexture.getLocationY(), 1, 1));
		
		bulletPool.add(bullet.getEntity());
		
		return bullet.getEntity();
	}
	
	public void removeBullet(Entity bullet) {
		int index = bulletPool.indexOf(bullet);
		if(index != -1) {
			bulletPool.remove(index);
		}
	}
	
	public void clearBulletList() {
		bulletPool.clear();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

	@Override
	public void pressTheKey() {
		// TODO Auto-generated method stub
		//java.lang.System.out.print("fight");
		//createBullet();
		java.lang.System.out.print("创造子弹");
	}
}
