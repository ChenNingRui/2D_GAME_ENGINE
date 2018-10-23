package basic.system;

import basic.component.*;
import basic.component.MoveComponent.ORIENTATION;
import basic.entity.EntityHandle;
import basic.world.World;

public class PlayinglayerSystem implements System {

	private World world;
	private EntityHandle player;
	
	public PlayinglayerSystem(World world) {
		this.world = world;
	}
	
	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
		player = world.createEntity("player");
		player.addComponent(new InputComponent());
		player.addComponent(new MoveComponent(0, 10, ORIENTATION.SOUTH));
		player.addComponent(new TextureComponent("enemyRed5.png", 200, 200, 180, 1, 1));
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub

	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}
}
