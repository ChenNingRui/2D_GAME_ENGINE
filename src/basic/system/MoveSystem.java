package basic.system;

import java.util.ArrayList;

import basic.component.InputComponent;
import basic.component.MoveComponent;
import basic.component.MoveComponent.ORIENTATION;
import basic.component.PositionComponent;
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
	private PositionComponent playerPosition;
	
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
		playerPosition = (PositionComponent) world.getComponentByEntity(ComponentType.position, player);
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
		renderBullet();
		renderEnemy();
	}
	
	private void renderBullet() {
		if(bulletList == null || bulletList.size() == 0) {
			return;
		}
		
		for(int i = 0; i < bulletList.size(); i++) {
			Entity bullet = bulletList.get(i);
			PositionComponent bulletPosition = (PositionComponent) world.getComponentByEntity(ComponentType.position, bullet);
			MoveComponent bulletMove = (MoveComponent) world.getComponentByEntity(ComponentType.move, bullet);
			if(bulletPosition != null) {
				bulletPosition.setLocationY(bulletPosition.getLocationY() - bulletMove.getVelocity());
			}
		}
	}
	
	private void renderEnemy() {
		if(enemyList == null || enemyList.size() == 0) {
			return;
		}
		
		for(int i = 0; i < enemyList.size(); i++) {
			Entity enemy = enemyList.get(i);
			PositionComponent enemyPosition = (PositionComponent) world.getComponentByEntity(ComponentType.position, enemy);
			MoveComponent enemyMove = (MoveComponent) world.getComponentByEntity(ComponentType.move, enemy);
			if(enemyPosition != null) {
				enemyPosition.setLocationY(enemyPosition.getLocationY() + enemyMove.getVelocity());
			}
		}
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
			if (playerPosition.getLocationX() < playerInput.getMouseX())
			{
				playerPosition.setLocationX(playerPosition.getLocationX() + playerMove.getVelocity());
				if (playerPosition.getLocationX() > playerInput.getMouseX())
					playerPosition.setLocationX(playerInput.getMouseX());
			}
			else if (playerPosition.getLocationX() > playerInput.getMouseX())
			{
				playerPosition.setLocationX(playerPosition.getLocationX() - playerMove.getVelocity());
				if (playerPosition.getLocationX() < playerInput.getMouseX())
					playerPosition.setLocationX(playerInput.getMouseX());
			}
			
			if (playerPosition.getLocationY() < playerInput.getMouseY())
			{
				playerPosition.setLocationY(playerPosition.getLocationY() + playerMove.getVelocity());
				if (playerPosition.getLocationY() > playerInput.getMouseY())
					playerPosition.setLocationY(playerInput.getMouseY());
			}
			else if (playerPosition.getLocationY() > playerInput.getMouseY())
			{
				playerPosition.setLocationY(playerPosition.getLocationY() - playerMove.getVelocity());
				if (playerPosition.getLocationY() < playerInput.getMouseY())
					playerPosition.setLocationY(playerInput.getMouseY());
			}
		}
		
		if(bulletList != null && bulletList.size() != 0) {
			for(int i = 0; i < bulletList.size(); i++) {
				Entity bullet = bulletList.get(i);
				PositionComponent bulletPosition = (PositionComponent) world.getComponentByEntity(ComponentType.position, bullet);
				if(bulletPosition.getLocationY() <= -150) {
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
				PositionComponent enemyPosition = (PositionComponent) world.getComponentByEntity(ComponentType.position, enemy);
				if(enemyPosition.getLocationY() >= 600) {
					for(RemoveEnemyEvent listener : removeEnemyEventList) {
						listener.onRemoveEnemyEvent(enemy);
					}
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
