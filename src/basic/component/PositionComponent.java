package basic.component;

import basic.component.manager.ComponentType;

public class PositionComponent extends ComponentBase {
	private double locationX;
	private double locationY;
	private double angle;
	private boolean rotate;
	
	public PositionComponent(double locationX, double locationY, double angle) {
		this.locationX = locationX;
		this.locationY = locationY;
		this.angle = angle;
		this.rotate = true;
		
		type = ComponentType.position;
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
