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
	
	public static int off_x;
	public static int off_y;

	public static void main(String[] args) throws AWTException, IOException {
		// TODO Auto-generated method stub
		

		File imageFile = new File("ocr_1.png");
		ImgLocData ILD = new ImgLocData();
		
		off_x = 1920;
		off_y = 0;
		
		ITesseract instance = new Tesseract();  // JNA Interface Mapping
		instance.setLanguage("eng");
		Robot robot = new Robot();
		
		BufferedImage img = null;
		
//		try {
//			System.out.println("Working Directory = " + System.getProperty("user.dir"));
//		    img = ImageIO.read(new File("ocr_1.png"));
// 
//
//		} catch (IOException e) {
//		}
		
        try {
        	//instance.setTessVariable("tessedit_char_whitelist", "1234567890.");
        	instance.setLanguage("ms");	
        	BufferedImage in = ImageIO.read(imageFile);
    		

        	

        	
        	int su = 2;
    		for(int id=0;id<25;id++) {
	        	BufferedImage ins = in.getSubimage(ILD.Locs[id].mon_x, ILD.Locs[id].mon_y, ILD.Locs[id].mon_width, ILD.Locs[id].mon_height);
	        	
	    		int w = Color.white.getRGB();
	    		int b = Color.black.getRGB();
	    		
	    		for(int i = 0;i<in.getHeight();i++) {
	    			for(int j = 0;j<in.getWidth();j++) {
	    				int r = (in.getRGB(j, i) >> 16) & 0x000000FF;
	    				if(r > 70) {
	    					//System.out.print(" ");
	    					in.setRGB(j, i, w);
	    				}else {
	    					//System.out.print("@");
	    					in.setRGB(j, i, b);
	    				}
	    			}
	    			//System.out.println();
	    		}
	        	
	        	BufferedImage inB = resize(ins,ins.getWidth()*su,ins.getHeight()*su);
	        	String result = instance.doOCR(inB);
	            System.out.println(result);
	            File outputfile = new File("output"+id+".png");
	            ImageIO.write(inB, "png", outputfile);
    		}

            
            
            
            //robot.mouseMove(off_x+849,124);


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


