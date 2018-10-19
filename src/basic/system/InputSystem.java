package basic.system;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import basic.component.InputComponent;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.world.World;

public class InputSystem implements System, KeyListener {
	private Canvas canvas;
	private World world;
	private Entity player;
	
	public InputSystem(World world,Canvas canvas) {
		this.world = world;
		
		this.canvas = canvas;
		this.canvas.addKeyListener(this);
		
		player = world.getEntityByName("player");
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
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		InputComponent inputcomponent = (InputComponent) world.getComponentByEntity(ComponentType.input, player);
		inputcomponent.setCurKeyCode(e.getKeyCode());
		inputcomponent.setPress(true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		InputComponent inputcomponent = (InputComponent) world.getComponentByEntity(ComponentType.input, player);
		inputcomponent.setPress(false);
	}


}
