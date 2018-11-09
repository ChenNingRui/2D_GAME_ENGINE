package basic.system;

import java.util.ArrayList;

import basic.component.InputComponent;
import basic.component.MoveComponent;
import basic.component.MoveComponent.ORIENTATION;
import basic.component.TextureComponent;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.event.CreateBulletEvent;
import basic.event.CreateEnemyEvent;
import basic.event.RemoveBulletEvent;
import basic.event.RemoveEnemyEvent;
import basic.world.World;

public class MoveSystem implements SystemBase, CreateBulletEvent, CreateEnemyEvent{
	private World world;
	
	private Entity player;
	private InputComponent playerInput;
	private MoveComponent playerMove;
	private TextureComponent playerTexture;
	
	private ArrayList<Entity> bulletList;
	private ArrayList<RemoveBulletEvent> removeBulletEventList;
	
	private ArrayList<Entity> enemyList;
	private ArrayList<RemoveEnemyEvent> removeEnemyEventList;
	
	public MoveSystem(World world) {
		this.world = world;
		removeBulletEventList = new ArrayList<RemoveBulletEvent>();
		removeEnemyEventList = new ArrayList<RemoveEnemyEvent>();
		player = world.getEntityByName("player");
		playerInput = (InputComponent) world.getComponentByEntity(ComponentType.input, player);
		playerMove = (MoveComponent) world.getComponentByEntity(ComponentType.move, player);
		playerTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, player);
	}
	
	public void removeBulletEvent(RemoveBulletEvent event) {
		removeBulletEventList.add(event);
	}
	
	public void removeEnemyEvent(RemoveEnemyEvent event){
		this.removeEnemyEventList.add(event);
	}	

	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
	}

	@SuppressWarnings("incomplete-switch")
	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(playerInput.isKeyPress()) {
			switch(playerInput.getCurKeyCode()) {
			case W://up
				playerMove.setDirection(ORIENTATION.NORTH);
				break;
			case S://down
				playerMove.setDirection(ORIENTATION.SOUTH);
				break;
			case A://left
				playerMove.setDirection(ORIENTATION.WEST);
				break;
			case D://right
				playerMove.setDirection(ORIENTATION.EAST);
				break;
			}
		}
		
		
		if(playerInput.isMousePress()) {
			if (playerTexture.getLocationX() < playerInput.getMouseX())
			{
				playerTexture.setLocationX(playerTexture.getLocationX() + playerMove.getVelocity());
				if (playerTexture.getLocationX() > playerInput.getMouseX())
					playerTexture.setLocationX(playerInput.getMouseX());
			}
			else if (playerTexture.getLocationX() > playerInput.getMouseX())
			{
				playerTexture.setLocationX(playerTexture.getLocationX() - playerMove.getVelocity());
				if (playerTexture.getLocationX() < playerInput.getMouseX())
					playerTexture.setLocationX(playerInput.getMouseX());
			}
			
			if (playerTexture.getLocationY() < playerInput.getMouseY())
			{
				playerTexture.setLocationY(playerTexture.getLocationY() + playerMove.getVelocity());
				if (playerTexture.getLocationY() > playerInput.getMouseY())
					playerTexture.setLocationY(playerInput.getMouseY());
			}
			else if (playerTexture.getLocationY() > playerInput.getMouseY())
			{
				playerTexture.setLocationY(playerTexture.getLocationY() - playerMove.getVelocity());
				if (playerTexture.getLocationY() < playerInput.getMouseY())
					playerTexture.setLocationY(playerInput.getMouseY());
			}
		}
		
		if(bulletList != null && bulletList.size() != 0) {
			for(int i = 0; i < bulletList.size(); i++) {
				Entity bullet = bulletList.get(i);
				TextureComponent bulletTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, bullet);
				if(bulletTexture.getLocationY() <= 100) {
					for(RemoveBulletEvent listener : removeBulletEventList) {
						listener.onRemoveBulletEvent(bullet);
					}
					//java.lang.System.out.println("no " + bulletList.size());
				}
			}
		}
		
		if(enemyList != null && enemyList.size() != 0) {
			for(int i = 0; i < enemyList.size(); i++) {
				Entity enemy = enemyList.get(i);
				TextureComponent enemyTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, enemy);
				if(enemyTexture.getLocationY() >= 500) {
					for(RemoveEnemyEvent listener : removeEnemyEventList) {
						listener.onRemoveEnemyEvent(enemy);
					}
					//java.lang.System.out.println("no " + bulletList.size());
				}
			}
		}
	}

	@Override
	public void onCreateBulletEvent(ArrayList<Entity> bulletList) {
		// TODO Auto-generated method stub
		this.bulletList = bulletList;
	}

	@Override
	public void onCreateEnemyEvent(ArrayList<Entity> enemyList) {
		// TODO Auto-generated method stub
		this.enemyList = enemyList;
	}

}
