import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public interface Animal {
	String toString();
	void draw();
	void makeSound(File f);
	public static void makeSounds() {
		AudioInputStream audio;
		try {
			audio = AudioSystem.getAudioInputStream(new File("d1.wav"));		
			Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            
		} catch (Exception e) {			
			e.printStackTrace();
		}
	}
}
