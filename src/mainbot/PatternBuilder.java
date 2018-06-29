package mainbot;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class PatternBuilder {
	ArrayList pal = new ArrayList<Object>();
	
	
	public void add_pattern(BufferedImage in,String name) {
		int img_w = in.getWidth();
		int img_h = in.getHeight();
		int[][] imgData = new int[img_w][img_h];
		int[][] featureMap = new int[img_w][img_h];
		int feature_sum = (img_h-2)*(img_w-2);
		int[] feature_hist = new int[512];
		ArrayList[] feature_hist_loc = new ArrayList[512];
		int[] feature_index = new int[feature_sum+1];
		
		for(int i = 0;i<img_h;i++) {
			for(int j = 0;j<img_w;j++) {
				int r = (in.getRGB(j, i) >> 16) & 0x000000FF;
				if(r > 70) {
					System.out.print(" ");				
					imgData[j][i] = 0;
				}else {
					System.out.print("@");
					imgData[j][i] = 1;
				}	
			}
			System.out.println();
		}
		
		for(int i=0;i<512;i++) {
			feature_hist[i] = 0;
		}
		for(int i=0;i<feature_sum;i++) {
			feature_index[i] = 0;
		}
		
		for(int i = 1;i<img_h-1;i++) {
			for(int j = 1;j<img_w-1;j++) {
				
				featureMap[j][i] = imgData[j-1][i-1] << 8 | imgData[j][i-1] << 7 | imgData[j+1][i-1] << 8 | imgData[j-1][i] << 5 |
						imgData[j][i] << 4 | imgData[j+1][i] << 3 | imgData[j-1][i+1] << 2 | imgData[j][i+1] << 1 |
						imgData[j+1][i+1];
				if(feature_hist[featureMap[j][i]] == 0) {
					feature_hist_loc[featureMap[j][i]] = new ArrayList();
				}
				feature_hist[featureMap[j][i]]++;
				feature_hist_loc[featureMap[j][i]].add(j);
				feature_hist_loc[featureMap[j][i]].add(i);
			}
		}
		

		
		//sort feature cloud
		int fLimit = 8;
		int counter = 0;
		for(int heap=1;heap<fLimit;heap++) {
			for(int idx=0;idx<512;idx++) {
				if(feature_hist[idx] == heap) {
					feature_index[counter] = idx;
					counter++;
				}
			}
		}
		feature_index[counter] = -1;
		
		counter = 0;

		while(feature_index[counter] > -1) {
			System.out.println(feature_index[counter] + " -> " + feature_hist[feature_index[counter]]);
			
			for(int i=0;i<feature_hist_loc[feature_index[counter]].size();i+=2) {
				System.out.println("@" + feature_hist_loc[feature_index[counter]].get(i) + "," + feature_hist_loc[feature_index[counter]].get(i+1));
			}
			
			counter++;
		}

	}
}
