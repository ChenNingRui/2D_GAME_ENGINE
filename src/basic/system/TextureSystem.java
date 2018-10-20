package basic.system;

import java.awt.image.BufferedImage;

import basic.component.InputComponent;
import basic.component.MoveComponent;
import basic.component.TextureComponent;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.world.World;

public class TextureSystem implements System {
	//private World world;
	private Entity player;
	private TextureComponent playerTexture;
	private InputComponent playerInput;
	private MoveComponent playerMove;
	
	public TextureSystem(World world) {
		//this.world = world;
		player = world.getEntityByName("player");
		playerMove = (MoveComponent) world.getComponentByEntity(ComponentType.move, player);
		playerInput = (InputComponent) world.getComponentByEntity(ComponentType.input, player);
		playerTexture = (TextureComponent) world.getComponentByEntity(ComponentType.texture, player);
		BufferedImage playerImage = world.getImageByTextureComponent(playerTexture);
		playerTexture.setImage(playerImage);
		world.AddToStage(playerTexture);
	}

	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render() {
		// TODO Auto-generated method stub
		
		//mouse control
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
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub

	}

}
