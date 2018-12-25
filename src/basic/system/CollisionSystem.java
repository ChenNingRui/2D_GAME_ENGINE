package basic.system;

import java.awt.Point;
import java.util.ArrayList;

import basic.component.PositionComponent;
import basic.component.TextureComponent;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.event.CreateBulletEvent;
import basic.event.CreateEnemyEvent;
import basic.event.RemoveBulletEvent;
import basic.event.RemoveEnemyEvent;
import basic.world.World;

public class CollisionSystem implements SystemBase, CreateBulletEvent, CreateEnemyEvent {
	private World world;
	private ArrayList<Entity> bulletList;
	private ArrayList<Entity> enemyList;
	private ArrayList<RemoveBulletEvent> removeBulletEventList;
	private ArrayList<RemoveEnemyEvent> removeEnemyEventList;

	public CollisionSystem(World world) {
		this.world = world;
	}
	
	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
		removeBulletEventList = new ArrayList<RemoveBulletEvent>();
		removeEnemyEventList = new ArrayList<RemoveEnemyEvent>();
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(bulletList != null && bulletList.size() != 0 && enemyList != null && enemyList.size() != 0) {
			for(int i = 0; i < bulletList.size(); i++) {
				for(int j = 0; j < enemyList.size(); j++) {
					//java.lang.System.out.println(enemyList.get(j));
					//java.lang.System.out.println(bulletList.get(i));
					
					Entity enemy = enemyList.get(j);
					
					if(bulletList.size() == 0)
						continue;
					
					Entity bullet = (bulletList.get(i) == null) ? null : bulletList.get(i);
					checkcollision(enemy, bullet);
				}
			}
		}
	}
	
	public void removeBulletEvent(RemoveBulletEvent event) {
		removeBulletEventList.add(event);
	}
	
	public void removeEnemyEvent(RemoveEnemyEvent event){
		this.removeEnemyEventList.add(event);
	}	

	@Override
	public void onCreateEnemyEvent(ArrayList<Entity> enemyList) {
		// TODO Auto-generated method stub
		this.enemyList = enemyList;
	}

	@Override
	public void onCreateBulletEvent(ArrayList<Entity> bulletList) {
		// TODO Auto-generated method stub
		this.bulletList = bulletList;
	}
	
	private void checkcollision(Entity obj1, Entity obj2) {
		if(obj1 != null || obj2 != null){
			PositionComponent obj1Position = (PositionComponent) world.getComponentByEntity(ComponentType.position, obj1);
			PositionComponent obj2Position = (PositionComponent) world.getComponentByEntity(ComponentType.position, obj2);
			
			TextureComponent obj1Texture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, obj1);

			int x = (int) (obj1Position.getLocationX() + obj1Texture.getWidth() / 2);
			int y = (int) (obj1Position.getLocationY() + obj1Texture.getHeight() / 2);
			Point p1 = new Point(x, y);
	        if (getDistance(p1, obj2Position.getLocationX(), obj2Position.getLocationY()) < 80) {
				for(RemoveEnemyEvent listener : removeEnemyEventList) {
					listener.onRemoveEnemyEvent(obj1);
				}
				for(RemoveBulletEvent listener : removeBulletEventList) {
					listener.onRemoveBulletEvent(obj2);
				}
	        }
		}
	}
	
    private double getDistance(Point p, double ox, double oy) {
        double _x = Math.abs(ox - p.x);
        double _y = Math.abs(oy - p.y);
        //java.lang.System.out.println(Math.sqrt(_x * _x + _y * _y));
        return Math.sqrt(_x * _x + _y * _y);
    }
}
