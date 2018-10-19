package basic.component;

import java.awt.image.BufferedImage;

import basic.component.manager.ComponentType;

public class GraphicsCompoment extends Component {
	private String path;
	private BufferedImage image;
	
	public GraphicsCompoment() {
		type = ComponentType.graphics;
	}
	
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	
	public BufferedImage getImage() {
		return image;
	}
	public void setImage(BufferedImage image) {
		this.image = image;
	}

}
