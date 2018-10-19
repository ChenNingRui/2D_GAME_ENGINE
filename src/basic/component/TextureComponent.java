package basic.component;
import java.awt.image.BufferedImage;

import basic.component.Component;
import basic.component.manager.ComponentType;

public class TextureComponent extends Component {
	private String textureName;
	private BufferedImage image;
	private int locationX;
	private int locationY;
	private int zoomX;
	private int zoomY;
	private int width;
	private int height;
	
	public TextureComponent(String textureName, 
			int locationX, int locationY,
			int zoomX, int zoomY) {
		
		type = ComponentType.texture;
		this.textureName = textureName;
		this.locationX = locationX;
		this.locationY = locationY;
		this.zoomX = zoomX;
		this.zoomY = zoomY;
	}
	
	public BufferedImage getImage() {
		return this.image;
	}
	
	public void setImage(BufferedImage image) {
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

	public int getLocationX() {
		return locationX;
	}

	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public void setLocationY(int locationY) {
		this.locationY = locationY;
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

}
