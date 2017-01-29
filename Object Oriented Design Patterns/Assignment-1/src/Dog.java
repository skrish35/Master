public  class Dog extends Mammal {

	public Dog(String name, String adopt,
			String condition, String notes, String id, String type, int age, String breed) {
		super(name, adopt,condition, notes, id, "Dog", age, breed);
	}

	@Override
	public void draw(){
		 System.out.println("Dog Drawn!");
	}
	

	public String toString() {
		System.out.println("-------------------------------------------------");
		System.out.println("name: " + this.name);
		System.out.println("ID: " + this.id);
		System.out.println("Type: " + this.type);
		System.out.println("Age: " + this.age);
		System.out.println("Breed: " + this.breed);
		System.out.println("Ready for Adoption?: "+ this.adopt);
		System.out.println("Medical condition: "+ this.condition);
		System.out.println("notes: " + this.notes);		
		draw();
		System.out.println("--------------------------------------------------");
		return "";
	}

}
