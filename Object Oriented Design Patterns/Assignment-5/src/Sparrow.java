import javax.swing.JFrame;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.UIManager;


public class Sparrow extends Bird{
	private static Sparrow sparrow=null;
	int myId = 2;
	int time;
	int stateCheck = 0;
	
	
	private Sparrow(String bird_Name, int bird_id, int bird_age,
			
			String bird_ready_for_adoption, String bird_medical_condition,
			String bird_notes, final FeedingBehavior fb) 
	{
		
		super(bird_Name, bird_id, bird_age, "Sparrow", "N/A",
				bird_ready_for_adoption, bird_medical_condition, bird_notes);
		
		EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                }

                JFrame frame = new JFrame("Sparrow Decorative Design");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setLayout(new BorderLayout());
                frame.add(new AnimationPane("sparrow.jpg", 200, 1000, fb.getImg()));
                frame.pack();
                frame.setLocationRelativeTo(null);
                frame.setVisible(true);
                frame.setSize(1000, 800);
            }

        });
		
	}
	
	@Override
	public void draw() {
		
		JFrame window = new JFrame();
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    window.setBounds(3, 3, 800, 700);
	    window.setVisible(true);
		
		System.out.println("Sparrow Drawn!");
		
	}

	@Override
	public void makeSound(File f) {
		if(f.length()>0){
		AudioInputStream audio;
		try {
			audio = AudioSystem.getAudioInputStream(f);		
			Clip clip = AudioSystem.getClip();
            clip.open(audio);
            clip.start();
            
		} catch (Exception e) {			
			e.printStackTrace();
		}
		}
	}
	
	public static synchronized Sparrow getInstance(FeedingBehavior fb){
		if(sparrow == null)
			sparrow = new Sparrow("",1,1,"","",null, fb);
		return sparrow;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}