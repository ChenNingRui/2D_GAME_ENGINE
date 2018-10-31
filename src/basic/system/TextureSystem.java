package basic.system;

import java.util.ArrayList;

import basic.component.MoveComponent;
import basic.component.TextureComponent;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.event.CreateBulletEventListener;
import basic.world.World;

public class TextureSystem implements System, CreateBulletEventListener{
	private World world;
	private Entity player;
	private TextureComponent playerTexture;
	
	private ArrayList<Entity> bulletList;
	
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
	
	private void renderBullet() {
		if(bulletList == null || bulletList.size() == 0) {
			return;
		}
		
		for(int i = 0; i < bulletList.size(); i++) {
			Entity bullet = bulletList.get(i);
			TextureComponent bulletTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, bullet);
			MoveComponent bulletMove = (MoveComponent) world.getComponentByEntity(ComponentType.move, bullet);
			bulletTexture.setLocationY(bulletTexture.getLocationY() - bulletMove.getVelocity());
		}
	}
	
	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
		//mouse control
		renderBullet();
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
	
	
}
