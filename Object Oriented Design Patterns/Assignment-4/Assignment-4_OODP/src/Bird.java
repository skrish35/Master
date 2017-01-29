public abstract class Bird implements Animal {
	String bird_Name;
	int bird_Id;
	int bird_Age;
	String bird_Type;
	String bird_Breed;
	String bird_ready_for_adoption;
	String bird_medical_condition;
	String bird_Notes;
	
	
	public Bird(String bird_Name, int bird_id, int bird_age,
			String bird_type, String bird_Breed,
			String bird_ready_for_adoption, String bird_medical_condition,
			String bird_notes) {
		
		this.bird_Name = bird_Name;
		this.bird_Id = bird_id;
		this.bird_Age = bird_age;
		this.bird_Type = bird_type;
		this.bird_Breed = bird_Breed;
		this.bird_ready_for_adoption = bird_ready_for_adoption;
		this.bird_medical_condition = bird_medical_condition;
		this.bird_Notes = bird_notes;
	}
}
