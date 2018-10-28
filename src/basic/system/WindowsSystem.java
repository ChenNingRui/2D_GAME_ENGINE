package basic.system;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import basic.component.*;

public class WindowsSystem implements System{
	
	public static int alpha = 0xFFFF00DC;
	
	private WindowsComponent component;
	private GraphicsContext graphics;
	private Group root;
	
	private Canvas canvas;
	
	private ArrayList<TextureComponent> renderList;
	
	public WindowsSystem() {
		instantiation();
		init();
	}
	
	public void showWindow(Stage primaryStage) throws Exception {
		primaryStage.setWidth(component.getBoundWidth());
		primaryStage.setHeight(component.getBoundHeight());
		primaryStage.setResizable(false);
        primaryStage.setTitle("ECS");
        primaryStage.setScene(new Scene(root));
	}
	
	public void init() {
		component.setBoundX(0);
		component.setBoundY(0);
		component.setBoundWidth(600);
		component.setBoundHeight(600);
		component.setTitle("gameTest");
		component.setVisable(true);
		
		renderList = new ArrayList<TextureComponent>();
		
		root = new Group();
		canvas = new Canvas(component.getBoundWidth(), component.getBoundHeight());
		graphics = canvas.getGraphicsContext2D();
		root.getChildren().add(canvas);
	}
	
	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
		component = new WindowsComponent();
	}
	
	public void render() {
		// TODO Auto-generated method stub
		for(int i = 0; i < renderList.size(); i++) {
			TextureComponent texture = renderList.get(i);
			graphics.clearRect(0, 0, component.getBoundWidth(), component.getBoundHeight());
			graphics.drawImage(texture.getImage(), 
					texture.getLocationX(), texture.getLocationY());
		}
	}
	
	public void update() {
		// TODO Auto-generated method stub
	}
	
	public Canvas getCanvas()
	{
		return canvas;
	}	
	
	public void addToStage(TextureComponent textureComponent) {
		renderList.add(textureComponent);
	}
	
	public void removeFromStage(TextureComponent textureComponent) {
		renderList.remove(textureComponent);
	}
}
