public abstract class Bird implements Animal {

	String name, adopt, condition, notes, id, type, breed;
	int age;
	
	public Bird(String nm, String ad, String cond, String nts, String id, String type, int a, String breed){
		this.name=nm;
		this.adopt=ad;
		this.condition=cond;
		this.notes=nts;
		this.id=id;
		this.type=type;
		this.age=a;
		this.breed=breed;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAdopt() {
		return adopt;
	}
	public void setAdopt(String adopt) {
		this.adopt = adopt;
	}
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getNotes() {
		return notes;
	}
	public void setNotes(String notes) {
		this.notes = notes;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}	
	public String getBreed() {
		return breed;
	}
	public void setBreed(String breed) {
		this.breed = breed;
	}


}
