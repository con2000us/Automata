package mainbot;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import MyRobot.MyRobot;
import net.sourceforge.tess4j.*;

public class Auto {
	

	public static void main(String[] args) throws AWTException {
		// TODO Auto-generated method stub
		File imageFile = new File("eurotext.png");
		
		ITesseract instance = new Tesseract();  // JNA Interface Mapping
		instance.setLanguage("eng");
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
		    

//		    for(int i=185;i<=197;i++) {
//		    	for(int j=2720;j<=2760;j++){
//			    	color = robot.getPixelColor(j, i);
//			    	if(color.getRed()+color.getGreen()+color.getBlue() > 200){
//				    	System.out.print(" ");
//			    	}else {
//				    	System.out.print("@");
//			    	}
//
//		    	}
//		    	System.out.println("|");
//	
//		    }
	    	robot.mouseMove(2720,185);
	    	robot.mouseMove(2760,197);
	    	robot.mousePress(InputEvent.BUTTON1_MASK);
	        robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (IOException e) {
		}
		
        try {
            String result = instance.doOCR(imageFile);
            System.out.println(result);
        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
	}

}
