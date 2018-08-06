package basic.component;
import java.awt.*;

public class RectComponent extends Component {
	private Point topLeft;
	private Point botRight;
	
//	public RectComponent() {
//		topLeft = new Point();
//		botRight = new Point();
//	}
	
	public void setTopLeft(int x, int y) {
		topLeft.x = x;
		topLeft.y = y;
	}
	
	public void setBotRight(int x, int y) {
		botRight.x = x;
		botRight.y = y;
	}
	
	public Point getTopLeft() {
		return this.topLeft;
	}
	
	public Point getBotRight() {
		return this.botRight;
	}
}
