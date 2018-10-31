package basic.event;

import java.util.ArrayList;

import basic.entity.Entity;

public interface CreateBulletEventListener {
	public void onCreateBulletEvent(ArrayList<Entity> bulletList);
}
