package basic.system;

import java.awt.image.BufferedImage;

import basic.component.InputComponent;
import basic.component.MoveComponent;
import basic.component.TextureComponent;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.world.World;

public class TextureSystem implements System {
	private World world;
	private Entity player;
	private TextureComponent playerTexture;
	private InputComponent playerInput;
	private MoveComponent playerMove;
	
	public TextureSystem(World world) {
		this.world = world;
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

	@SuppressWarnings("incomplete-switch")
	@Override
	public void render() {
		// TODO Auto-generated method stub
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		if(playerInput.isPress()) {
			int x = playerTexture.getLocationX();
			int y = playerTexture.getLocationY();
			switch(playerMove.getDirection()){
			case NORTH:
				playerTexture.setLocationY(y - playerMove.getVelocity());
				break;
			case SOUTH:
				playerTexture.setLocationY(y + playerMove.getVelocity());
				break;
			case WEST:
				playerTexture.setLocationX(x - playerMove.getVelocity());
				break;
			case EAST:
				playerTexture.setLocationX(x + playerMove.getVelocity());
				break;
			}
		}
	}

}
