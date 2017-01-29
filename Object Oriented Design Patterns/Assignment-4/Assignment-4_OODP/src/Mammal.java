public abstract class Mammal implements Animal{
	String mammal_Name;
	int mammal_id;
	int mammal_age;
	String mammal_type;
	String mammal_breed;
	String mammal_ready_for_adoption;
	String mammal_medical_condition;
	String mammal_notes;
	
	public Mammal(String mammal_Name, int mammal_id, int mammal_age,
			String mammal_type, String mammal_breed,
			String mammal_ready_for_adoption, String mammal_medical_condition,
			String mammal_notes){
		
		this.mammal_Name = mammal_Name;
		this.mammal_id = mammal_id;
		this.mammal_age = mammal_age;
		this.mammal_type = mammal_type;
		this.mammal_breed = mammal_breed;
		this.mammal_ready_for_adoption = mammal_ready_for_adoption;
		this.mammal_medical_condition = mammal_medical_condition;
		this.mammal_notes = mammal_notes;
	}

}
