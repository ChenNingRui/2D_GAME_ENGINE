package basic.component;

import basic.component.ComponentBase;
import basic.component.manager.ComponentType;
import javafx.scene.image.Image;

public class TextureComponent extends ComponentBase {
	private String textureName;
	private Image image;
	private int zoomX;
	private int zoomY;
	private int width;
	private int height;
	
	public TextureComponent(String textureName,
			int zoomX, int zoomY) {
		type = ComponentType.texture;
		this.textureName = textureName;
		this.zoomX = zoomX;
		this.zoomY = zoomY;
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
}
