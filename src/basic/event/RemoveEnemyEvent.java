package basic.event;

import basic.entity.Entity;

public interface RemoveEnemyEvent {
	void onRemoveEnemyEvent(Entity enemy);
}
