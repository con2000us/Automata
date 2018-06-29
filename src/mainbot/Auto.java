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
import java.lang.reflect.Array;
import java.util.ArrayList;

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
		instance.setTessVariable("tessedit_char_whitelist", "1234567890.");
//		instance.setTessVariable(ITesseract. "!?@#$%&*()<>_-+=/:;'\"ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz");
//		instance.setTessVariable(TessBaseAPI.VAR_CHAR_WHITELIST, ".,012345G789");
//		instance.setTessVariable("classify_bln_numeric_mode", "1");
		
//		Robot robot = new Robot();
//		robot.mouseMove(off_x+849,124);
//    	System.out.println(0b0000000001 | 0b0000001000);
    	
    	
    	PatternBuilder pb = new PatternBuilder();
    	String[] inn = {"0","1","2","3","4","5","6","7","8","9","0","."};
    	for(int i=0;i<11;i++) {
    		pb.add_pattern(ImageIO.read(new File("pattern/p_"+i+".png")),inn[i]);
    	}
    	
    	
//    	
//        try {
//        	instance.setTessVariable("tessedit_char_whitelist", "1234567890.");
//        	instance.setLanguage("ms");	
//        	BufferedImage in = ImageIO.read(imageFile);
//        	
//       	
//        	int su = 3;
//    		for(int id=0;id<25;id++) {
//	        	BufferedImage ins = in.getSubimage(ILD.Locs[id].mon_x, ILD.Locs[id].mon_y, ILD.Locs[id].mon_width, ILD.Locs[id].mon_height);
//	        	
//	    		int w = Color.white.getRGB();
//	    		int b = Color.black.getRGB();
//	    		//int g = Color.green.getRGB();
//	    		
//	    		int[][] back = new int[ins.getWidth()][ins.getHeight()];
//	    		int[][] temp = new int[ins.getWidth()][ins.getHeight()];
//	    		
//	    		for(int i = 0;i<ins.getHeight();i++) {
//	    			for(int j = 0;j<ins.getWidth();j++) {
//	    				int r = (ins.getRGB(j, i) >> 16) & 0x000000FF;
//	    				if(r > 70) {
//	    					//System.out.print(" ");
//	    					ins.setRGB(j, i, w);
//	    					back[j][i] = 0;
//	    				}else {
//	    					//System.out.print("@");
//	    					ins.setRGB(j, i, b);
//	    					back[j][i] = 1;
//	    				}
//	    				if(i>0 && j>0 && back[j][i-1] > 0 && back[j-1][i] > 0 && back[j-1][i-1] > 0) {
//	    					temp[j][i] = 1;
//	    					//System.out.println(j + "," + i);
//	    				}else {
//	    					temp[j][i] = 0;
//	    				}
//
//	    			}
//
//	    			//System.out.println();
//	    		}
//
//	    		for(int i = 0;i<ins.getHeight();i++) {
//	    			for(int j = 0;j<ins.getWidth();j++) {
//	    				if(temp[j][i] == 1) {
//	    					ins.setRGB(j, i, b);
//	    				}
//	    			}
//	    		}
//	        	
//	        	BufferedImage inB = resize(ins,ins.getWidth()*su,ins.getHeight()*su);
//	        	String result = instance.doOCR(inB);
//	            System.out.println(result);
//	            File outputfile = new File("output"+id+".png");
//	            ImageIO.write(inB, "png", outputfile);
//    		}
//         
//
//        } catch (TesseractException e) {
//            System.err.println(e.getMessage());
//        }

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


