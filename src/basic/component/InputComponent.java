package basic.component;

import basic.component.manager.ComponentType;

public class InputComponent extends Component {
	private int curKeyCode;
	private boolean press;
	
	public InputComponent() {
		type = ComponentType.input;
	}
	
	public int getCurKeyCode() {
		return curKeyCode;
	}

	public void setCurKeyCode(int curKeyCode) {
		this.curKeyCode = curKeyCode;
	}

	public boolean isPress() {
		return press;
	}

	public void setPress(boolean press) {
		this.press = press;
	}

}
