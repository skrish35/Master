import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class HumanSocietyCenter {

	static int poodlecount;
	static int dalmatiancount;
	static int catcount;
	static int chickencount;
	static int sparrowcount;
	static int germanshepherdcount;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Animal a1,a2,a3,a4,a5,a6;
		String name="", adopt="", condition="", notes="", id=""; 
		int age=0;
		while (true) {
			System.out.println("Please enter your choice");
			
			System.out.println("1. Add a Poodle");
			System.out.println("2. Add a Dalmatian");
			System.out.println("3. Add a GermanShepherd");
			System.out.println("4. Add a Cat");
			System.out.println("5. Add a Chicken");
			System.out.println("6. Add a Sparrow");
			System.out.println("7. Animal Report");
			
			int choice = Integer.parseInt(br.readLine());
			
			if(choice!=7){
			
			System.out.println("Please enter the following information");

			System.out.println("Animal name:");
			name = br.readLine();

			System.out.println("ID");
			id = br.readLine();

			System.out.println("Age");
			age = br.read();
			br.readLine();

			System.out.println("Medical condition");
		    condition = br.readLine();
		    
			System.out.println("Ready for Adoption? Yes or No");
			adopt = br.readLine();

			System.out.println("Notes about your animal");
			notes = br.readLine();
			}

			switch (choice) {

			case 1:
				poodlecount++;
				a1 = new Poodle(name, adopt, condition, notes, id, age);
				a1.toString();
				break;

			case 2:
				dalmatiancount++;
				a2 = new Dalmatian(name, adopt, condition, notes, id, age);
				a2.toString();
				break;

			case 3:
				germanshepherdcount++;
				a3 = new GermanShepherd(name, adopt, condition, notes, id, age);
				a3.toString();
				break;
				
			case 4:
				catcount++;
				a4 = new Cat(name, adopt, condition, notes, id, age);
				a4.toString();
				break;

			case 5:
				chickencount++;
				a5 = new Chicken(name, adopt, condition, notes, id, age);
				a5.toString();
				break;

			case 6:
				sparrowcount++;
				a6 = new Sparrow(name, adopt, condition, notes, id, age);
				a6.toString();
				break;
				
			case 7:
				 animalReport();
				 break;

			default:
				System.out.println("Please enter inputs between 1 and 7");

			}
			
		}

	}
	
	public static void animalReport(){
		System.out.println("-------------------------------------------------");
		System.out.println("Poodle (Dog)   : " + poodlecount);
		System.out.println("Dalmatian (Dog):  " + dalmatiancount);
		System.out.println("GermanShepherd (Dog):  " + germanshepherdcount);
		System.out.println("Cat  (Cat)     :  " + catcount);
		System.out.println("Chicken (Bird)  :  " + chickencount);
		System.out.println("Sparrow  (Bird) :  " + sparrowcount);
		System.out.println("-------------------------------------------------");
	
	}

}
