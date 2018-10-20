package basic.system;

import java.awt.Canvas;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import basic.component.InputComponent;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.world.World;

public class InputSystem implements System, KeyListener, MouseListener {
	//private Canvas canvas;
	//private World world;
	private Entity player;
	private InputComponent inputcomponent;
	
	public InputSystem(World world,Canvas canvas) {
		//this.world = world;
		
		canvas.addKeyListener(this);
		canvas.addMouseListener(this);
		
		
		player = world.getEntityByName("player");
		inputcomponent = (InputComponent) world.getComponentByEntity(ComponentType.input, player);
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
		inputcomponent.setCurKeyCode(e.getKeyCode());
		inputcomponent.setKeyPress(true);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		inputcomponent.setKeyPress(false);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		inputcomponent.setMousePress(true);
		// TODO Auto-generated method stub
		if(e.getButton() == MouseEvent.BUTTON1) {
			inputcomponent.setMouseX(e.getX());
			inputcomponent.setMouseY(e.getY());
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		inputcomponent.setMousePress(false);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}


}
