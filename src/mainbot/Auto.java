package mainbot;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import MyRobot.MyRobot;

public class Auto {
	

	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
		
		Robot robot = new Robot();
		Color color;
		
		robot.mouseMove(300,300);
		
		BufferedImage img = null;
		
		try {
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
		    img = ImageIO.read(new File("ocr_1.png"));
		    System.out.println(img.getHeight());
		    byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		    System.out.println(pixels.length);
		    
		    for(int i=0;i<100;i+=5) {
		    	color = robot.getPixelColor(0, i);
		    	robot.mouseMove(0,i);
		    	System.out.print((color.getRed()+color.getGreen()+color.getBlue())/3+ " ");
		    }
		} catch (IOException e) {
		}
	}

}
