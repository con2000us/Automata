package mainbot;

import java.awt.AWTException;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Robot;
import java.awt.event.InputEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferByte;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import org.apache.pdfbox.rendering.ImageType;

import MyRobot.MyRobot;
import net.sourceforge.tess4j.*;
import net.sourceforge.tess4j.ITessAPI.TessBaseAPI;

public class Auto {
	

	public static void main(String[] args) throws AWTException, IOException {
		// TODO Auto-generated method stub
		//File imageFile = new File("eurotext.png");
		File imageFile = new File("ocr_2.png");
		

		
		ITesseract instance = new Tesseract();  // JNA Interface Mapping
		instance.setLanguage("eng");
		Robot robot = new Robot();
		Color color;
		
		robot.mouseMove(300,300);
		
		BufferedImage img = null;
		
		try {
			System.out.println("Working Directory = " + System.getProperty("user.dir"));
		    img = ImageIO.read(new File("ocr_1.png"));
		    //System.out.println(img.getHeight());
		    byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		    //System.out.println(pixels.length);
		    

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
//	    	robot.mouseMove(2720,185);
//	    	robot.mouseMove(2760,197);
//	    	robot.mousePress(InputEvent.BUTTON1_MASK);
//	        robot.mouseRelease(InputEvent.BUTTON1_MASK);
		} catch (IOException e) {
		}
		
        try {
    		BufferedImage in = ImageIO.read(imageFile);
    		BufferedImage in3 = resize(in,720,210);
        	instance.setTessVariable("tessedit_char_whitelist", "1234567890.");
            String result = instance.doOCR(in3);
            System.out.println(result);
            
            File outputfile = new File("output.png");
            ImageIO.write(in3, "png", outputfile);
            
            

        } catch (TesseractException e) {
            System.err.println(e.getMessage());
        }
	}
    private static BufferedImage resize(BufferedImage img, int width, int height) {
        Image tmp = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        BufferedImage resized = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2d = resized.createGraphics();
        g2d.drawImage(tmp, 0, 0, null);
        g2d.dispose();
        return resized;
    }
	
}


