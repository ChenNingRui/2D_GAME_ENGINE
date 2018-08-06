package basic.entity;

public class Entity {
	private int id;
	private String name;
	
	public Entity(String name, int id) {
		this.name = name;
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	public String getName() {
		return name;
	}
	
}
