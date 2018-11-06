package basic.event;

import java.util.ArrayList;

import basic.entity.Entity;

public interface CreateBulletEvent {
	void onCreateBulletEvent(ArrayList<Entity> bulletList);
}
