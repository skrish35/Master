public abstract class Dog extends Mammal{

	
	public Dog (String mammal_Name, int mammal_id, int mammal_age,
			 String mammal_breed,
			String mammal_ready_for_adoption, String mammal_medical_condition,
			String mammal_notes)
	{
	super(mammal_Name,mammal_id,mammal_age,"Dog",mammal_breed,mammal_ready_for_adoption,mammal_medical_condition,mammal_notes);
	}

	
	@Override
	abstract public void draw();		

}
