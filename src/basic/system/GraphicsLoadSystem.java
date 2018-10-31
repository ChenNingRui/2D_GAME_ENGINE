package basic.system;
import javax.imageio.ImageIO;

import org.json.simple.JSONObject;

import basic.component.GraphicsCompoment;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;

import java.awt.image.BufferedImage;
import java.io.FileInputStream;
import java.io.IOException;

public class GraphicsLoadSystem implements System{
	private GraphicsCompoment compoment;
	
	public GraphicsLoadSystem() {
		instantiation();
	}

    public void loadGraphics(String path) {
    	compoment.setPath(path);
    	try {
			BufferedImage loadedImage = ImageIO.read(new FileInputStream(compoment.getPath()));
			compoment.setImage(new BufferedImage(loadedImage.getWidth(), 
					loadedImage.getHeight(), BufferedImage.TYPE_INT_ARGB_PRE));
			compoment.getImage().getGraphics().drawImage(loadedImage, 0, 0, null);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public Image getImgageByJson(JSONObject jsonObj) {
    	int width = Integer.parseInt(jsonObj.get("width").toString());
    	int height = Integer.parseInt(jsonObj.get("height").toString());
    	int x = Integer.parseInt(jsonObj.get("x").toString());
    	int y = Integer.parseInt(jsonObj.get("y").toString());
    	
    	BufferedImage image = new BufferedImage(width, 
    			height, BufferedImage.TYPE_INT_ARGB_PRE);
    	image.getGraphics().drawImage(compoment.getImage(), -x, -y, null);
    	//image.getGraphics().setColor(Color.white);
    	//image.getGraphics().fillRect(0, 0, compoment.getImage().getWidth(), compoment.getImage().getHeight());
    	
    	Image fxImage = SwingFXUtils.toFXImage(image, null);
    	return fxImage;
    }

	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
		compoment = new GraphicsCompoment();
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