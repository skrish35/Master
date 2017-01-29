public class GermanShepherd extends Dog {

	public GermanShepherd(String name, String adopt,
			String condition, String notes, String id, int age) {

		super(name, adopt, condition,notes, id, "Dog", age, "GermanShepherd");

	}

	@Override
	public void draw() {
		System.out.println("GermanShepherd Drawn!");
	}

}
