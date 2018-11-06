package basic.component;
import basic.component.ComponentBase;
import basic.component.manager.ComponentType;

public class MoveComponent  extends ComponentBase {
	
	public enum ORIENTATION{
		EAST,
		SOUTH,
		WEST,
		NORTH,
		EAST_NORTH,
		EAST_SOUTH,
		WEST_NORTH,
		WEST_SOUTH
	}
	
	private int velocity;
	private int acceleration;
	private ORIENTATION direction;
	
	public MoveComponent(int acceleration, int velocity, ORIENTATION direction) {
		type = ComponentType.move;
		this.acceleration = acceleration;
		this.velocity = velocity;
		this.direction = direction;
	}
	
	public int getVelocity() {
		return this.velocity;
	}
	public ORIENTATION getDirection() {
		return this.direction;
	}
	public int getAcceleration() {
		return acceleration;
	}
	
	public void setVelocity(int value){
		this.velocity = value;
	}
	public void setDirection(ORIENTATION direction) {
		this.direction = direction;
	}
	public void setAcceleration(int value){
		this.acceleration = value;
	}
}
