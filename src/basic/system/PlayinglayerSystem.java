package basic.system;

import java.util.ArrayList;

import basic.component.*;
import basic.component.MoveComponent.ORIENTATION;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.entity.EntityHandle;
import basic.event.CreateBulletEvent;
import basic.event.KeyBoardEvent;
import basic.event.RemoveBulletEvent;
import basic.world.World;

public class PlayinglayerSystem implements SystemBase, KeyBoardEvent, RemoveBulletEvent{

	private World world;
	private EntityHandle player;
	private ArrayList<Entity> bulletList;
	private ArrayList<Entity> enemyList;
	private ArrayList<CreateBulletEvent> createBulletlistenerList = new ArrayList<CreateBulletEvent>();
	
	public PlayinglayerSystem(World world) {
		this.world = world;
	}
	
	@Override
	public void instantiation() {
		// TODO Auto-generated method stub

		createPlayer();
		bulletList = new ArrayList<Entity>();
		enemyList = new ArrayList<Entity>();
	}
	
	private void createPlayer() {
		player = world.createEntity("player");
		player.addComponent(new InputComponent());
		player.addComponent(new MoveComponent(0, 20, ORIENTATION.SOUTH));
		player.addComponent(new TextureComponent("playerShip1_blue.png", 0, 200, 200, 1, 1));
	}
	
	private Entity createBullet() {
		TextureComponent playTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, player.getEntity());
		EntityHandle bullet = world.createEntity("bullet" + bulletList.size());
		bullet.addComponent(new MoveComponent(0, 10, ORIENTATION.SOUTH));
		bullet.addComponent(new TextureComponent("laserBlue16.png", 0, playTexture.getLocationX() + 40, playTexture.getLocationY() + 10, 1, 1));
		bulletList.add(bullet.getEntity());
		
		return bullet.getEntity();
	}
	
	private void removeBullet(Entity bullet) {
		TextureComponent bulletTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, bullet);
		world.removeFromStage(bulletTexture);
		if(world.existInStage(bulletTexture) == -1) {
			MoveComponent bulletMove = (MoveComponent) world.getComponentByEntity(ComponentType.move, bullet);
			world.removeComponent(bullet, bulletMove);
			world.removeComponent(bullet, bulletTexture);
			bulletList.remove(bullet);
			world.destroyEntity(bullet);
		}
	}
	
	private void createEnemy() {
		EntityHandle enemy = world.createEntity("enemy" + enemyList.size());
		enemy.addComponent(new MoveComponent(0, 10, ORIENTATION.SOUTH));
		enemy.addComponent(new TextureComponent("enemyBlack5.png", 0, 0, 0, 1, 1));
		bulletList.add(enemy.getEntity());
	}
	
	private void removeEnemy(Entity enemy) {
		TextureComponent enemyTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, enemy);
		world.removeFromStage(enemyTexture);
		if(world.existInStage(enemyTexture) == -1) {
			MoveComponent bulletMove = (MoveComponent) world.getComponentByEntity(ComponentType.move, enemy);
			world.removeComponent(enemy, bulletMove);
			world.removeComponent(enemy, enemyTexture);
			enemyList.remove(enemy);
			world.destroyEntity(enemy);
		}
	}
	
	public void addCreateBulletListener(CreateBulletEvent listener) {
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
		for(CreateBulletEvent listener : createBulletlistenerList) {
			listener.onCreateBulletEvent(bulletList);
		}
	}

	@Override
	public void onRemoveBulletEvent(Entity bullet) {
		// TODO Auto-generated method stub
		removeBullet(bullet);
	}
}
