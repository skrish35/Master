import java.io.File;


public class AnimalObservor {
	Animal animal;
	String filename;
	Subject sub;
	
	
	public AnimalObservor(Animal a, String str, Subject s){
		animal = a;
		filename=str;
		sub=s;
		this.sub.attach(this);
	}
	
	public void update(){
		this.animal.makeSound(new File(filename));
	}
}
