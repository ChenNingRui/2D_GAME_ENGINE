package basic.system;

import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.util.ArrayList;

import javax.swing.JFrame;

import basic.component.*;

public class WindowsSystem extends JFrame implements System{
	
	public static int alpha = 0xFFFF00DC;
	
	private WindowsComponent component;
	
	private Canvas canvas;
	private Rectangle camera;
	private BufferedImage view;
	private int[] pixels;
	
	private ArrayList<TextureComponent> renderList;


	private static final long serialVersionUID = 1L;
	
	public WindowsSystem() {
		instantiation();
		init();
	}
	
	public void init() {
		component.setBoundX(0);
		component.setBoundY(0);
		component.setBoundWidth(600);
		component.setBoundHeight(600);
		component.setTitle("gameTest");
		component.setVisable(true);
		component.setCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		renderList = new ArrayList<TextureComponent>();
		
		canvas = new Canvas();
		
		//Create a BufferedImage that will represent our view.
		view = new BufferedImage(component.getBoundWidth(), component.getBoundWidth(), BufferedImage.TYPE_INT_RGB);
		//view.setRGB(0, 0, 0x000000);
		//Create an array for pixels
		pixels = ((DataBufferInt) view.getRaster().getDataBuffer()).getData();
		camera = new Rectangle(0, 0, component.getBoundWidth(), component.getBoundWidth());
	}
	
	@Override
	public void instantiation() {
		// TODO Auto-generated method stub
		component = new WindowsComponent();
	}

	public void render() {
		// TODO Auto-generated method stub
		BufferStrategy bufferStragegy = canvas.getBufferStrategy();
		Graphics graphics = bufferStragegy.getDrawGraphics();
		super.repaint();
		
		for(int i = 0; i < renderList.size(); i++) {
			TextureComponent texture = renderList.get(i);
			if(texture.isRotate()) {
				texture.setImage(rotateImage(texture.getImage(), texture.getAngle()));
				texture.setWidth(texture.getImage().getWidth());
				texture.setHeight(texture.getImage().getHeight());
				texture.setRotate(false);
			}
			renderImage(texture.getImage(),
					texture.getLocationX(), texture.getLocationY(),
					texture.getZoomX(), texture.getZoomY());
		}
		
		
		graphics.drawImage(view, 0, 0, null);
		graphics.dispose();
		bufferStragegy.show();
		clear();
	}
	
	public void update() {
		// TODO Auto-generated method stub
		
		//nothing need to do
	}
	
	public Canvas getCanvas()
	{
		return canvas;
	}	
	
	public void showWindows() {
		setBounds(component.getBoundX(), component.getBoundY(), 
				component.getBoundWidth(), component.getBoundHeight());//设置窗口的大小及位置。
		setLocationRelativeTo(null);
		add(canvas);
        setTitle(component.getTitle());//设置窗口标题
        setVisible(component.isVisable());//显示窗口
        canvas.createBufferStrategy(3);
        setDefaultCloseOperation(component.getCloseOperation());
	}
	
	public void addToStage(TextureComponent textureComponent) {
		renderList.add(textureComponent);
	}
	
	public void removeFromStage(TextureComponent textureComponent) {
		renderList.remove(textureComponent);
	}
	
	private void clear()
	{
		for(int i = 0; i < pixels.length; i++)
			pixels[i] = 0;
	}
	
	private BufferedImage rotateImage(BufferedImage image, double angle) {
	    int width = image.getWidth();    
	    int height = image.getHeight();
		
		AffineTransform tx = new AffineTransform();
	    tx.rotate(Math.toRadians(angle), width / 2, height / 2);

	    AffineTransformOp op = new AffineTransformOp(tx,
	        AffineTransformOp.TYPE_BILINEAR);
	    image = op.filter(image, null);

		
	    return image;
	}
		
	//Render our image to our array of pixels.
	private void renderImage(BufferedImage image, int locationX, int locationY, int zoomX, int zoomY)
	{
		int[] imagePixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();
		renderArray(imagePixels, image.getWidth(), image.getHeight(), locationX, locationY, zoomX, zoomY);
	}
	
	private void renderArray(int[] renderPixels, int renderWidth, int renderHeight, int xPosition, int yPosition, int xZoom, int yZoom) 
	{
		for(int y = 0; y < renderHeight; y++)
			for(int x = 0; x < renderWidth; x++)
				for(int yZoomPosition = 0; yZoomPosition < yZoom; yZoomPosition++)
					for(int xZoomPosition = 0; xZoomPosition < xZoom; xZoomPosition++)
						setPixel(renderPixels[x + y * renderWidth], (x * xZoom) + xPosition + xZoomPosition, ((y * yZoom) + yPosition + yZoomPosition));
	}

	private void setPixel(int pixel, int x, int y) 
	{
		if(x >= camera.x && y >= camera.y && x <= camera.x + camera.width && y <= camera.y + camera.height)
		{
			int pixelIndex = (x - camera.x) + (y - camera.y) * view.getWidth();
			if(pixels.length > pixelIndex && pixel != alpha)
				pixels[pixelIndex] = pixel;
		}
	}
}
