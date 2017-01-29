import java.util.ArrayList;
import java.util.List;

public class Subject{
	List<AnimalObservor> observors = new ArrayList<AnimalObservor>();
	int state = 0;
	
	int getState(){
		return state;
	}
	
	void setState(int i) throws InterruptedException{
		state = i;
		Thread.sleep(2000);
		notifyAllObservors();
	}
	
	void attach(AnimalObservor a){
		observors.add(a);
	}
	
	void notifyAllObservors(){
		for(AnimalObservor ao:observors){
			ao.update();
		}
	}
}