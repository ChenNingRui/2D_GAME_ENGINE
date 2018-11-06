package basic.component;

import basic.component.manager.ComponentType;

public class WindowsComponent extends ComponentBase{
	private int boundX;
	private int boundY;
	private int boundWidth;
	private int boundHeight;
	
	private String title;
	private boolean visable;
	private int closeOperation;
	
	public WindowsComponent() {
		type = ComponentType.windows;
	}
	
	public int getBoundX() {
		return boundX;
	}
	public void setBoundX(int boundX) {
		this.boundX = boundX;
	}
	public int getBoundY() {
		return boundY;
	}
	public void setBoundY(int boundY) {
		this.boundY = boundY;
	}
	public int getBoundWidth() {
		return boundWidth;
	}
	public void setBoundWidth(int boundWidth) {
		this.boundWidth = boundWidth;
	}
	public int getBoundHeight() {
		return boundHeight;
	}
	public void setBoundHeight(int boundHeight) {
		this.boundHeight = boundHeight;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public boolean isVisable() {
		return visable;
	}
	public void setVisable(boolean visable) {
		this.visable = visable;
	}
	public int getCloseOperation() {
		return closeOperation;
	}
	public void setCloseOperation(int closeOperation) {
		this.closeOperation = closeOperation;
	}
	
	
}
