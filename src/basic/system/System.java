package basic.system;

public class System {
	protected static System instance;
	
	public System() {
		init();
	}

	protected void init() {
	}
	
	public void update() {}
	
	public void render() {}

}


//UseEvent use = new UseEvent();
//use.addMyEventListener(new MyEventListener(){
//
//	@Override
//	public void HandleMyName(MyEvent ea) {
//		// TODO Auto-generated method stub
//		System.out.println("This is a listener" + ea.getName());
//	}
//	
//});
//
//use.generateEvent();

