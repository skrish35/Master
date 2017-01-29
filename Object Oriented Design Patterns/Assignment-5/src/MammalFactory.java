import java.util.ArrayList;
import java.util.List;


public class MammalFactory extends AnimalCreatorFactory{

	@Override
	Animal createAnimal(int i, FeedingBehavior fb) {
		Animal a=null;
		if(i==1)
			a = Poodle.getInstance(fb);
		if(i==2)
			a = Dalmatian.getInstance(fb);
		if(i==3)
			a = Cat.getInstance(fb);
		
		return a;
	}

	@Override
	FeedingBehavior getImg(int i) {
		// TODO Auto-generated method stub
		FeedingBehavior fb=null;
		if(i==1)
			fb = new PoodleFeed();
		if(i==2)
			fb = new DalmatianFeed();
		if(i==3)
			fb = new CatFeed();
		
		return fb;
	}
}