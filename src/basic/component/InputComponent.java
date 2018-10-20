package basic.component;

import basic.component.manager.ComponentType;

public class InputComponent extends Component {
	private int curKeyCode;
	private int mouseX;
	private int mouseY;
	private boolean keyPress;
	private boolean mousePress;
	
	public InputComponent() {
		type = ComponentType.input;
	}
	
	public int getCurKeyCode() {
		return curKeyCode;
	}

	public void setCurKeyCode(int curKeyCode) {
		this.curKeyCode = curKeyCode;
	}

	public int getMouseX() {
		return mouseX;
	}

	public void setMouseX(int mouseX) {
		this.mouseX = mouseX;
	}

	public int getMouseY() {
		return mouseY;
	}

	public void setMouseY(int mouseY) {
		this.mouseY = mouseY;
	}

	public boolean isKeyPress() {
		return keyPress;
	}

	public void setKeyPress(boolean keyPress) {
		this.keyPress = keyPress;
	}

	public boolean isMousePress() {
		return mousePress;
	}

	public void setMousePress(boolean mousePress) {
		this.mousePress = mousePress;
	}

}
