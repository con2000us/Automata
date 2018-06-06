package mainbot;

public class DataLoc {
	String name;
	int set_x, set_y, set_width, set_height;
	int mon_x, mon_y, mon_width, mon_height;
	
	public DataLoc(String n, int sx, int sy, int sw, int sh, int mx, int my, int mw, int mh) {
		this.name = n;
		this.set_x = sx;
		this.set_y = sy;
		this.set_width = sw;
		this.set_height = sh;
		this.mon_x = mx;
		this.mon_y = my;
		this.mon_width = mw;
		this.mon_height = mh;
	}
}



//for(int i=185;i<=197;i++) {
//	for(int j=2720;j<=2760;j++){
//    	color = robot.getPixelColor(j, i);
//    	if(color.getRed()+color.getGreen()+color.getBlue() > 200){
//	    	System.out.print(" ");
//    	}else {
//	    	System.out.print("@");
//    	}
//
//	}
//	System.out.println("|");
//
//}
//robot.mouseMove(2720,185);
//robot.mouseMove(2760,197);
//robot.mousePress(InputEvent.BUTTON1_MASK);
//robot.mouseRelease(InputEvent.BUTTON1_MASK);
//for(int i=0;i<=350;i++){
//
//color = robot.getPixelColor(off_x+ImgLocData.Loc[0]+i, off_y+111);
//
//color_bri = color.getBlue()+color.getGreen()+color.getRed();
//if(color_bri > 700) {
//	System.out.println(ImgLocData.Loc[0]+i+"   :  "+color_bri);
//}
//}