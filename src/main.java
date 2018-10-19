import basic.world.World;

/**
 * 
 */

/**
 * @author chenningrui
 *
 */
public class Main{
	
	public static void main(String[] arg) throws Exception {
		World world = new World();
		
		world.addSystem();
		world.initComponentMnager();
		world.init();
		world.gameLayer();
		world.run();
	}
}
