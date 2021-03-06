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


public class Dalmatian extends Dog{
	
	private static Dalmatian dalmatian = null;
	
	private  Dalmatian(String dog_Name, int dog_id, int dog_age,
			String dog_ready_for_adoption, String dog_medical_condition,
			String dog_notes, final FeedingBehavior fb) 
	{

		super(dog_Name,dog_id,dog_age,"Dalmatian",dog_ready_for_adoption,dog_medical_condition,dog_notes);
		
		 EventQueue.invokeLater(new Runnable() {
	            @Override
	            public void run() {
	                try {
	                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	                } catch (Exception e) {
	                }

	                JFrame frame = new JFrame("Test");
	                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	                frame.setLayout(new BorderLayout());
	                frame.add(new AnimationPane("dalmatian.jpg", 200, 200, fb.getImg()));
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
	    window.setBounds(3, 3, 600, 500);
	    window.setVisible(true);
	
		System.out.println("Dalmatian Drawn!");
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
	
	
	public static synchronized Dalmatian getInstance(FeedingBehavior fb){
		if(dalmatian == null)
			dalmatian = new Dalmatian("",1,1,"","",null, fb);
		return dalmatian;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}


}
