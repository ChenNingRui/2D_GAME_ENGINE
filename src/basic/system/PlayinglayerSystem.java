package basic.system;

import java.util.ArrayList;

import basic.component.*;
import basic.component.MoveComponent.ORIENTATION;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.entity.EntityHandle;
import basic.event.CreateBulletEvent;
import basic.event.CreateEnemyEvent;
import basic.event.KeyBoardEvent;
import basic.event.RemoveBulletEvent;
import basic.event.RemoveEnemyEvent;
import basic.world.World;

public class PlayinglayerSystem implements SystemBase, KeyBoardEvent, RemoveBulletEvent, RemoveEnemyEvent{

	private World world;
	private EntityHandle player;
	private ArrayList<Entity> bulletList;
	private ArrayList<Entity> enemyList;
	private ArrayList<CreateBulletEvent> createBulletEventList = new ArrayList<CreateBulletEvent>();
	private ArrayList<CreateEnemyEvent> createEnemyEventList = new ArrayList<CreateEnemyEvent>();
	private int[] randomNumberList;
	private WindowsComponent windowComponent;
	private long lastUpdate;
	
	public PlayinglayerSystem(World world) {
		this.world = world;
	}
	
	@Override
	public void instantiation() {
		lastUpdate = 0;
		randomNumberList = new int[3];
		windowComponent = world.getWindowsComponent();
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
			
			//java.lang.System.out.println(bulletList.size());
		}
	}
	
	private void createEnemy(int x) {
		EntityHandle enemy = world.createEntity("enemy" + enemyList.size());
		enemy.addComponent(new MoveComponent(0, 3, ORIENTATION.NORTH));
		enemy.addComponent(new TextureComponent("enemyBlack5.png", 0, x, x * -1 , 1, 1));
		enemyList.add(enemy.getEntity());
	}
	
	private void removeEnemy(Entity enemy) {
		TextureComponent enemyTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, enemy);
		world.removeFromStage(enemyTexture);
		if(world.existInStage(enemyTexture) == -1) {
			MoveComponent enemyMove = (MoveComponent) world.getComponentByEntity(ComponentType.move, enemy);
			world.removeComponent(enemy, enemyMove);
			world.removeComponent(enemy, enemyTexture);
			enemyList.remove(enemy);
			world.destroyEntity(enemy);
		}
	}
	
	private void randomNumberList() {
		for(int i = 0, length = randomNumberList.length; i < length; i++) {
			int position = (int)(Math.random() * windowComponent.getBoundWidth() - 100) + 50;
			randomNumberList[i] = position;
		}
	}
	
	public void addCreateBulletEvent(CreateBulletEvent event) {
		createBulletEventList.add(event);
	}
	
	public void addCreateEnemyEvent(CreateEnemyEvent event) {
		createEnemyEventList.add(event);
	}

	@Override
	public void render() {
	}

	@Override
	public void update() {
		long currentTime = java.lang.System.currentTimeMillis();
        	 if (currentTime - lastUpdate >= 3000) {
 				randomNumberList();
 				for(int i = 0, length = randomNumberList.length; i < length; i ++) {
 					createEnemy(randomNumberList[i]);
 				}
 				
 				for(CreateEnemyEvent listener : createEnemyEventList) {
 					listener.onCreateEnemyEvent(enemyList);
 				}
        		lastUpdate = currentTime ;
             }
	}

	@Override
	public void onPressTheKey() {
		createBullet();
		for(CreateBulletEvent listener : createBulletEventList) {
			listener.onCreateBulletEvent(bulletList);
		}
	}

	@Override
	public void onRemoveBulletEvent(Entity bullet) {
		removeBullet(bullet);
	}

	@Override
	public void onRemoveEnemyEvent(Entity enemy) {
		// TODO Auto-generated method stub
		removeEnemy(enemy);
	}
}
