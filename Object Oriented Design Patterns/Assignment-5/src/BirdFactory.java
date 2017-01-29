import java.util.ArrayList;
import java.util.List;


public class BirdFactory extends AnimalCreatorFactory {
	
	@Override
	Animal createAnimal(int i, FeedingBehavior fb) {
		Animal a=null;
		if(i==1)
			a = Chicken.getInstance(fb);
		if(i==2)
			a = Sparrow.getInstance(fb);
		
		return a;
	}

	@Override
	FeedingBehavior getImg(int i) {
		// TODO Auto-generated method stub
		FeedingBehavior fb=null;
		if(i==1)
			fb = new ChickenFeed();
		if(i==2)
			fb = new SparrowFeed();
		
		return fb;
	}
}