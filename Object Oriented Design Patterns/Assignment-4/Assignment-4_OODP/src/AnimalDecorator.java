import java.io.File;
import javax.swing.JPanel;

public class AnimalDecorator extends JPanel implements Animal {

	private static final long serialVersionUID = 1L;
	static AnimalDecorator animalDecorator = null;

	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}

	@Override
	public void draw() {
		repaint();
	}
	
	@Override
	public void makeSound(File f) {
		
	}
}
