package mainbot;

import java.awt.AWTException;
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
		System.out.print("123456");
		
		Robot robot = new Robot();
		
		robot.mouseMove(300,300);
		
		BufferedImage img = null;
		
		try {
			System.out.println("111");
		    img = ImageIO.read(new File("c:/automata/img/ocr_1.png"));
		    System.out.println("222");
		    System.out.println(img.getHeight());
		    byte[] pixels = ((DataBufferByte) img.getRaster().getDataBuffer()).getData();
		    System.out.println(pixels.length);
		} catch (IOException e) {
		}
	}

}
