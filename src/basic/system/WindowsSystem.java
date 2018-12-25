package basic.system;

import java.util.ArrayList;

import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import basic.component.*;
import basic.component.manager.ComponentType;
import basic.entity.Entity;
import basic.world.World;

public class WindowsSystem implements SystemBase{
	
	private WindowsComponent component;
	private GraphicsContext graphics;
	private Group root;
	private World world;
	
	private Canvas canvas;
	
	private ArrayList<TextureComponent> renderList;
	
	public WindowsSystem(World world) {
		this.world = world;
		
		instantiation();
		init();
	}
	
	private void init() {
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
	
	private Image imageRotation(Image image, double angle) {
		ImageView imageView = new ImageView(image);
		imageView.setRotate(angle);
		SnapshotParameters params = new SnapshotParameters();
		params.setFill(Color.TRANSPARENT);
		Image rotatedImage = imageView.snapshot(params, null);
		return rotatedImage;
	}
	
	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
		component = new WindowsComponent();
	}
	
	public void render() {
		// TODO Auto-generated method stub
		graphics.clearRect(0, 0, component.getBoundWidth(), component.getBoundHeight());
		
		for(int i = 0; i < renderList.size(); i++) {
			TextureComponent texture = (TextureComponent) renderList.get(i);
			Entity entity = world.getEntityByComponent(texture);
			PositionComponent position = (PositionComponent) world.getComponentByEntity(ComponentType.position, entity);
			
			if(position.isRotate()) {
				position.setRotate(false);
				texture.setImage(imageRotation(texture.getImage(), position.getAngle()));
			}
			
			graphics.drawImage(texture.getImage(), 
					position.getLocationX(), position.getLocationY());
		}
	}
	
	public void update() {
		// TODO Auto-generated method stub
	}
	
	public void addToStage(TextureComponent component) {
		renderList.add(component);
	}
	
	public void removeFromStage(TextureComponent component) {
		renderList.remove(component);
	}
	
	public int existInStage(TextureComponent component) {
		return renderList.indexOf(component);
	}
	
	public void showWindow(Stage primaryStage) throws Exception {
		primaryStage.setWidth(component.getBoundWidth());
		primaryStage.setHeight(component.getBoundHeight());
		primaryStage.setResizable(false);
        primaryStage.setTitle("ECS");
        primaryStage.setScene(new Scene(root));
	}
	
	public WindowsComponent getWindowsComponent() {
		return component;
	}
}
