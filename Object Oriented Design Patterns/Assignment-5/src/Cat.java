import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.UIManager;

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

public class Cat extends Mammal {
	private static Cat cat = null;

	private Cat(String mammal_Name, int mammal_id, int mammal_age,

	String mammal_ready_for_adoption, String mammal_medical_condition,
			String mammal_notes, final FeedingBehavior fb){
		super(mammal_Name, mammal_id, mammal_age, "Cat", "N/A",
				mammal_ready_for_adoption, mammal_medical_condition,
				mammal_notes);

		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					UIManager.setLookAndFeel(UIManager
							.getSystemLookAndFeelClassName());
				} catch (Exception e) {
				}

				JFrame frame = new JFrame("Cat Decorative Design");
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setLayout(new BorderLayout());
				frame.add(new AnimationPane("cat.jpg", 200, 1000,fb.getImg()));
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
		window.setBounds(3, 3, 900, 700);
		window.setVisible(true);

		System.out.println("Poodle Drawn!");

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
	
	public static synchronized  Cat getInstance(FeedingBehavior fb){
		if(cat == null)
			cat = new Cat("",1,1,"","",null, fb);
		return cat;
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		throw new CloneNotSupportedException();
	}
}
