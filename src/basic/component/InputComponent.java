package basic.component;

import basic.component.manager.ComponentType;
import javafx.scene.input.KeyCode;

public class InputComponent extends Component {
	private KeyCode curKeyCode;
	private double mouseX;
	private double mouseY;
	private boolean keyPress;
	private boolean mousePress;
	
	public InputComponent() {
		type = ComponentType.input;
	}
	
	public KeyCode getCurKeyCode() {
		return curKeyCode;
	}

	public void setCurKeyCode(KeyCode curKeyCode) {
		this.curKeyCode = curKeyCode;
	}

	public double getMouseX() {
		return mouseX;
	}

	public void setMouseX(double mouseX) {
		this.mouseX = mouseX;
	}

	public double getMouseY() {
		return mouseY;
	}

	public void setMouseY(double mouseY) {
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
