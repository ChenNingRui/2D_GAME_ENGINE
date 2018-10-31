package basic.component;

import basic.component.Component;
import basic.component.manager.ComponentType;
import javafx.scene.image.Image;

public class TextureComponent extends Component {
	private String textureName;
	private Image image;
	private double locationX;
	private double locationY;
	private int zoomX;
	private int zoomY;
	private int width;
	private int height;
	private double angle;
	private boolean rotate;
	
	public TextureComponent(String textureName, double angle,
			double locationX, double locationY,
			int zoomX, int zoomY) {
		
		type = ComponentType.texture;
		this.textureName = textureName;
		this.locationX = locationX;
		this.locationY = locationY;
		this.angle = angle;
		this.zoomX = zoomX;
		this.zoomY = zoomY;
		this.rotate = true;
	}
	
	public Image getImage() {
		return this.image;
	}
	
	public void setImage(Image image) {
		this.image = image;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public String getTextureName() {
		return textureName;
	}

	public int getZoomX() {
		return zoomX;
	}

	public void setZoomX(int zoomX) {
		this.zoomX = zoomX;
	}

	public int getZoomY() {
		return zoomY;
	}

	public void setZoomY(int zoomY) {
		this.zoomY = zoomY;
	}

	public boolean isRotate() {
		return rotate;
	}

	public void setRotate(boolean rotate) {
		this.rotate = rotate;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getLocationX() {
		return locationX;
	}

	public void setLocationX(double locationX) {
		this.locationX = locationX;
	}

	public double getLocationY() {
  		return locationY;
	}

	public void setLocationY(double locationY) {
		this.locationY = locationY;
	}


}
