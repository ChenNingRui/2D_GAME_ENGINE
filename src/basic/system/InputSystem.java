package basic.system;

import basic.component.InputComponent;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.world.World;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class InputSystem implements System{
	private Entity player;
	private InputComponent inputcomponent;
	
	private EventHandler<MouseEvent> mouseEventHandler = new EventHandler<MouseEvent>() {

		@Override
		public void handle(MouseEvent event) {
			// TODO Auto-generated method stub
			if(event.getEventType() == MouseEvent.MOUSE_PRESSED && !inputcomponent.isKeyPress()) {
				inputcomponent.setMouseX(event.getX());
				inputcomponent.setMouseY(event.getY());
				inputcomponent.setMousePress(true);
				
				java.lang.System.out.print(event.getX() + "  "+ event.getY());
			} 
			else if(event.getEventType() == MouseEvent.MOUSE_RELEASED) {
				inputcomponent.setMousePress(false);
			}
		}
	};
	
	public InputSystem(World world, Stage primaryStage) {
		
		player = world.getEntityByName("player");
		inputcomponent = (InputComponent) world.getComponentByEntity(ComponentType.input, player);
		
		
		primaryStage.getScene().setOnMousePressed(mouseEventHandler);
		primaryStage.getScene().setOnMouseReleased(mouseEventHandler);
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

}

