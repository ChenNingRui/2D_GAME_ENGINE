package basic.component;
import basic.component.Component;

public class SizeComponent  extends Component {
	private int high;
	private int width;
	
	public int getHigh() {
		return this.high;
	}
	public int getWidth() {
		return this.high;
	}
	public int[] getCenter() {
		return new int[]{this.high/2, this.width/2};
	}
	
	public void setHigh(int high) {
		this.high = high;
	}
	public void setWidth(int width) {
		this.width = width;
	}
}
