package Bean;

public class Drinks {
	String id;
	String Name;
	String Price;
	public Drinks(String id, String name, String price) {
		super();
		this.id = id;
		Name = name;
		Price = price;
	}
	public Drinks() {
		super();
		
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPrice() {
		return Price;
	}
	public void setPrice(String price) {
		Price = price;
	}
	
	public Drinks toStringDrink() {
		// TODO Auto-generated method stub
		return this;
	}
	
}
