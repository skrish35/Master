import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class HumanSocietyCenter {
	
	public static void main(String[] args) throws IOException, InterruptedException {
	
		MammalFactory m = new MammalFactory();
		BirdFactory b = new BirdFactory();
		
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));	
		while(true){			 
			System.out.println("Enter the choice to select the type of Animal");
			System.out.println("1.Poodle");
			System.out.println("2.Dalmatian");
			System.out.println("3.Cat");
			System.out.println("4.Chicken");
			System.out.println("5.Sparrow");		
			
			int ch = Integer.parseInt(br.readLine());
			
			Subject s = new Subject();
			
			System.out.println("\n");
			displayFeedingChoice();
			int fc = Integer.parseInt(br.readLine());
			FeedingBehavior fb = selectFoodChoice(fc, m, b);
			switch (ch)
			{
			
				case 1:					
					Animal a=m.createAnimal(1, fb);
					new AnimalObservor(a, "d2.wav", s);
					s.setState(1);
					//a.makeSound(new File("d2.wav"));			
					break;
					
				case 2:
					Animal a1 = m.createAnimal(2, fb);
					new AnimalObservor(a1, "d1.wav", s);
					s.setState(1);
					//a1.makeSound(new File("d1.wav"));
					break;
					
				case 3:
					Animal a2 = m.createAnimal(3, fb);
					new AnimalObservor(a2, "cat.wav", s);
					s.setState(1);
					//a2.makeSound(new File("cat.wav"));
					break;
					
				case 4:
					Animal a3 = b.createAnimal(1, fb);
					new AnimalObservor(a3, "chicken.wav", s);
					s.setState(1);
					//a3.makeSound(new File("chicken.wav"));
					break;
					
				case 5:
					Animal a4 = b.createAnimal(2, fb);
					new AnimalObservor(a4, "sparrow.wav", s);
					s.setState(1);
					//a4.makeSound(new File("sparrow.wav"));
					break;
				
				
				default: System.out.println("Invalid input");
					
			}
		}		
	}
	
	static void displayFeedingChoice(){
		
		System.out.println("Enter the type of food to feed:");
		System.out.println("1.Bone");
		System.out.println("2.Meat");
		System.out.println("3.Milk");
		System.out.println("4.Grass");
		System.out.println("5.Nuts");
	}
	
	static FeedingBehavior selectFoodChoice(int choice, MammalFactory m, BirdFactory b){
		
		FeedingBehavior fb=null;
		
		switch(choice)
		{
		case 1:fb=m.getImg(1);
		       break;
		       
		case 2:fb=m.getImg(2);
		       break;
		       
		case 3:fb=m.getImg(3);
		       break;
		       
		case 4:fb=b.getImg(1);
		       break;
		       
		case 5:fb=b.getImg(2);
	       break;
		}
		return fb;
	}		
}