import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanSocietyCenter {
	
	public static void main(String[] args) throws IOException {
	
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		while(true){			 
			System.out.println("Enter the choice to select the type of Animal");
			System.out.println("1.Poodle");
			System.out.println("2.Dalmatian");
			System.out.println("3.Cat");
			System.out.println("4.Chicken");
			System.out.println("5.Sparrow");		
			
			int ch = Integer.parseInt(br.readLine());
		
			System.out.println("\n");
		
			switch (ch)
			{
			
				case 1:
					Animal a=new Poodle("",1,1,"","","");
					a.makeSound(new File("d2.wav"));			
					break;
					
				case 2: 
					Animal a1 = new Dalmatian("",1,1,"","","");
					a1.makeSound(new File("d1.wav"));
					break;
					
				case 3: 
					Animal a2 = new Cat("",1,1,"","","");
					a2.makeSound(new File("cat.wav"));
					break;
					
				case 4:
					
					Animal a3 = new Chicken("",1,1,"","","");
					a3.makeSound(new File("chicken.wav"));
					break;
					
				case 5:
					Animal a4 = new Sparrow("",1,1,"","","");
					a4.makeSound(new File("sparrow.wav"));
					break;
				
				
				default: System.out.println("Invalid input");
					
			}
		}
		
		
	}
		
		
}

