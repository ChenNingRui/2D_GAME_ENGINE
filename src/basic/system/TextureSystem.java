package basic.system;

import java.util.ArrayList;

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
	
	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
		
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
	public void onCreateBulletEvent(ArrayList<Entity> bulletList) {
		// TODO Auto-generated method stub
		for(int i = 0; i < bulletList.size(); i++) {
			Entity bullet = bulletList.get(i);
			addBulletToStage(bullet);
		}
	}

	@Override
	public void onCreateEnemyEvent(ArrayList<Entity> enemyList) {
		// TODO Auto-generated method stub
		for(int i = 0; i < enemyList.size(); i++) {
			Entity enemy = enemyList.get(i);
			addEnemyToStage(enemy);
		}
	}
	
	
}
