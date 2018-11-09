import basic.world.World;
import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * 
 */

/**
 * @author chenningrui
 *
 */
public class Main extends Application{
	private static World world;
	
	public static void main(String[] arg) throws Exception {
		world = new World();
		Application.launch(arg);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		world.addSystem();
		world.initComponentMnager();
		world.init();
		world.gameLayer(primaryStage);
		
		//final long startNanoTime = System.nanoTime();
		new AnimationTimer()
	    {
			long lastUpdate = 0;
	        public void handle(long currentTime)
	        {
	        	 if (currentTime - lastUpdate >= 3800000) {
	        		 world.render();
	        		 lastUpdate = currentTime ;
                 }
	            //double t = (currentNanoTime - startNanoTime) / 1000000000.0; 
	        	world.update();;
	        }
	    }.start();
	    
	    
	    
	    primaryStage.show();
	}
}
