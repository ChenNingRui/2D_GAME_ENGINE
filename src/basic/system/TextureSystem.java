package basic.system;

import java.util.ArrayList;

import basic.component.MoveComponent;
import basic.component.TextureComponent;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.event.CreateBulletEvent;
import basic.event.CreateEnemyEvent;
import basic.world.World;

public class TextureSystem implements SystemBase, CreateBulletEvent, CreateEnemyEvent{
	private World world;
	private Entity player;
	private TextureComponent playerTexture;
	
	private ArrayList<Entity> bulletList;
	private ArrayList<Entity> enemyList;
	
	public TextureSystem(World world) {
		this.world = world;
		player = world.getEntityByName("player");
		playerTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, player);
		world.getImageByTextureComponent(playerTexture);
		world.AddToStage(playerTexture);
	}
	
	private void addBulletToStage(Entity bullet) {
		TextureComponent bulletTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, bullet);
		world.getImageByTextureComponent(bulletTexture);
		world.AddToStage(bulletTexture);
	}
	
	private void addEnemyToStage(Entity enemy) {
		TextureComponent enemyTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, enemy);
		world.getImageByTextureComponent(enemyTexture);
		world.AddToStage(enemyTexture);
	}
	
	private void renderBullet() {
		if(bulletList == null || bulletList.size() == 0) {
			return;
		}
		
		for(int i = 0; i < bulletList.size(); i++) {
			Entity bullet = bulletList.get(i);
			TextureComponent bulletTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, bullet);
			MoveComponent bulletMove = (MoveComponent) world.getComponentByEntity(ComponentType.move, bullet);
			if(bulletTexture != null) {
				bulletTexture.setLocationY(bulletTexture.getLocationY() - bulletMove.getVelocity());
			}
		}
	}
	
	private void renderEnemy() {
		if(enemyList == null || enemyList.size() == 0) {
			return;
		}
		
		for(int i = 0; i < enemyList.size(); i++) {
			Entity enemy = enemyList.get(i);
			TextureComponent enemyTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, enemy);
			MoveComponent enemyMove = (MoveComponent) world.getComponentByEntity(ComponentType.move, enemy);
			if(enemyTexture != null) {
				enemyTexture.setLocationY(enemyTexture.getLocationY() + enemyMove.getVelocity());
			}
		}
	}
	
	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		renderBullet();
		renderEnemy();
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onCreateBulletEvent(ArrayList<Entity> bulletList) {
		// TODO Auto-generated method stub
		for(int i = 0; i < bulletList.size(); i++) {
			Entity bullet = bulletList.get(i);
			addBulletToStage(bullet);
		}
		
		this.bulletList = bulletList;
	}

	@Override
	public void onCreateEnemyEvent(ArrayList<Entity> enemyList) {
		// TODO Auto-generated method stub
		for(int i = 0; i < enemyList.size(); i++) {
			Entity enemy = enemyList.get(i);
			addEnemyToStage(enemy);
		}
		
		this.enemyList = enemyList;
	}
	
	
}
