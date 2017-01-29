import java.util.Random;

import javax.swing.JPanel;


public class ThreadClass extends JPanel implements Runnable {
	Thread thread;
	int status=0;
	static int count = 0;
	
	public ThreadClass(){
		thread = new Thread(this, "AnimationPane");
		thread.start();
	}
	
	@Override
	public void run() {

		int random = new Random().nextInt(6) + 1;
		
		for (count=0;count<5;count++) {
			if(status==0){
			this.setLocation(10, -10);
			repaint();
			status=new Random().nextInt(2);
			}else if(status==1){
				this.setLocation(10, 10);
				repaint();
			status=new Random().nextInt(2);
			}else{
				this.setLocation(10,-10);
				repaint();
			status=new Random().nextInt(2);
			}
		}
		
		
	}
	
	void sleepThread(int t){
		try {
			thread.sleep(t);
		} catch (Exception e) {
		}
	}

}
