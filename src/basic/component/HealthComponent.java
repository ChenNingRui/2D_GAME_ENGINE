package basic.component;
import basic.component.Component;

public class HealthComponent  extends Component {
	private int currentHealth;
	private int maxHealth;
	
	public int getCurrentHealth() {
		return this.currentHealth;
	}
	public int getMaxHealth() {
		return this.maxHealth;
	}
	
	public void setCurrentHealth(int value) {
		this.currentHealth = value;
	}
	public void setMaxHealth(int value) {
		this.maxHealth = value;
	}
}
