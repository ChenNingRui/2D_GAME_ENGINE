package basic.component;
import basic.component.Component;

public class LifeCompont  extends Component {

	private boolean death;
	
	public boolean getDeath() {
		return this.death;
	}
	
	public void setDeath(boolean status) {
		this.death = status;
	}
}
