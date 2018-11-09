package basic.event;

import java.util.ArrayList;

import basic.entity.Entity;

public interface CreateEnemyEvent {
	void onCreateEnemyEvent(ArrayList<Entity> enemyList);
}
