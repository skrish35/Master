public class Dalmatian extends Dog {

	public Dalmatian(String name, String adopt,
			String condition, String notes, String id, int age) {

		super(name, adopt, condition,notes, id, "Dog", age, "Dalmatian");

	}

	@Override
	public void draw() {
		System.out.println("Dalmatian Drawn!");

	}

}
