import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

public class AnimationPane extends JPanel{

			int flag = 0;
			private static final long serialVersionUID = 1L;
			private BufferedImage image,image2;
	        private int pos = 0;
	        private int dir = 1;
	        int slt = 0;
	        int rnt = 0;
	        
	        public AnimationPane(String s, int st, int rt, String Img) {
	        	slt = st;
	        	rnt = rt;
	            
	        	try {
	                image = ImageIO.read(new File(s));
	                image2= ImageIO.read(new File(Img));
	                Timer timer = new Timer(5, new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        pos += dir;
	                        if (pos + image.getWidth() > getWidth()) {
	                            pos = getWidth() - image.getWidth();
	                            dir *= -1;
	                        } else if (pos < 0) {
	                            pos = 0;
	                            dir *= 1;
	                        }
	                        repaint();
	                    }

	                });
	                timer.setRepeats(true);
	                timer.setCoalesce(true);
	                timer.start();
	            } catch (IOException ex) {
	                ex.printStackTrace();
	            }
	        }

	        @Override
	        public Dimension getPreferredSize() {
	            if(image == null) 
	           		return super.getPreferredSize();
	            else
	            	return new Dimension(image.getWidth() * 4, image.getHeight());
	        }

	        @Override
	        protected void paintComponent(Graphics g) {
	            super.paintComponent(g);
	            ThreadClass tc = new ThreadClass();
	            if (flag == 0) {
	            
		            int y = getHeight() - image.getHeight();
		            g.drawImage(image, pos, y, this);
		            g.drawImage(image2, pos+700, y, this);
		            g.setColor(Color.yellow);
	                g.fillOval(pos+130, y+70, 100, 100);		         
					flag = 1;
					tc = new ThreadClass();
	            	tc.sleepThread(this.rnt);
	            }
	            
	            else if(flag==1) {

	            	flag=2;
	            	tc.sleepThread(this.slt);
	            	int y = getHeight() - image.getHeight();
	                g.drawImage(image, pos, y, this);
	                g.drawImage(image2, pos+700, y, this);
	                g.setColor(Color.yellow);
	                g.fillOval(pos+130, y+70, 100, 100);
	                
	                tc = new ThreadClass();
	            	tc.sleepThread(this.rnt);
	            }
	            
	            else if(flag==2) {

	            	flag=1;
	            	tc.sleepThread(this.slt);
	            	int y = getHeight() - image.getHeight();
	                g.drawImage(image, pos, y, this); 
	                g.drawImage(image2, pos+700, y, this);
	                g.setColor(Color.green);
	                g.fillOval(pos+120, y+120, 100, 100);
	                tc = new ThreadClass();
	            	tc.sleepThread(this.rnt);
	            }
	        }

}