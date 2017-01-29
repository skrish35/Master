public class Poodle extends Dog {

	public Poodle(String name, String adopt,
			String condition, String notes, String id, int age) {

		super(name, adopt, condition,notes, id, "Dog", age, "Poodle");

	}

	@Override
	public void draw() {
		System.out.println("Poodle Drawn!");
	}

}
