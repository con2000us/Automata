package mainbot;

import java.awt.Point;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public class PatternBuilder {
	ArrayList<HashMap> pal = new ArrayList<HashMap>();
	ArrayList<HashMap>[] mesh = new ArrayList[512];
	
	public void add_pattern(BufferedImage in,String name) {
		int img_w = in.getWidth();
		int img_h = in.getHeight();
		int[][] imgData = new int[img_w][img_h];
		int[][] featureMap = new int[img_w][img_h];
		int feature_sum = (img_h-2)*(img_w-2);
		int[] feature_hist = new int[512];
		
		HashMap<String, Object> inMap = new HashMap<String, Object>();
		@SuppressWarnings("unchecked")
		ArrayList<Point>[] feature_hist_loc = new ArrayList[512];
		
		int[] feature_index = new int[feature_sum+1];
		@SuppressWarnings("unchecked")
		ArrayList<Point>[] feature_index_loc = new ArrayList[512];
		
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
		inMap.put("name", name);
		inMap.put("imgData", imgData);
		
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
					feature_hist_loc[featureMap[j][i]] = new ArrayList<Point>();
				}
				feature_hist[featureMap[j][i]]++;
				feature_hist_loc[featureMap[j][i]].add(new Point(j,i));
				
			}
		}
		
		
		//sort feature cloud
		int fLimit = 8;
		int counter = 0;
		for(int heap=1;heap<fLimit;heap++) {
			for(int idx=0;idx<512;idx++) {
				if(feature_hist[idx] == heap) {
					feature_index[counter] = idx;
					feature_index_loc[counter] = feature_hist_loc[feature_index[counter]];
					counter++;
				}
			}
		}
		inMap.put("featureID",feature_index);
		inMap.put("featureLoc",feature_index_loc);
		feature_index[counter] = -1;
		pal.add(inMap);
		
		counter = 0;

		while(feature_index[counter] > -1) {
			System.out.println(feature_index[counter] + " -> " + feature_hist[feature_index[counter]]);
			
			for(int i=0;i<feature_hist_loc[feature_index[counter]].size();i++) {
				System.out.println("@" + feature_hist_loc[feature_index[counter]].get(i).x + "," + feature_hist_loc[feature_index[counter]].get(i).y);
			}
			
			counter++;
		}

	}
	
	public static int[][] featureConverter(BufferedImage in) {
		int img_w = in.getWidth();
		int img_h = in.getHeight();
		int[][] imgData = new int[img_w][img_h];
		int[][] featureMap = new int[img_w][img_h];
		
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
		
		for(int i = 1;i<img_h-1;i++) {
			for(int j = 1;j<img_w-1;j++) {
				
				featureMap[j][i] = imgData[j-1][i-1] << 8 | imgData[j][i-1] << 7 | imgData[j+1][i-1] << 8 | imgData[j-1][i] << 5 |
						imgData[j][i] << 4 | imgData[j+1][i] << 3 | imgData[j-1][i+1] << 2 | imgData[j][i+1] << 1 |
						imgData[j+1][i+1];
				
			}
		}
		return featureMap;
	}
	
	@SuppressWarnings("unchecked")
	public void buildFeatureCloud(String name, int pairNum) {
		int i,FGsize;
		int counter = 0;
		Random ran = new Random();
		ArrayList<Point> PairedFeature = new ArrayList<Point>();
		ArrayList<Integer> PairedFeatureID = new ArrayList<Integer>();
		while(counter < pal.size()) {
			if( Objects.equals(pal.get(counter).get("name"), name)) {
				HashMap curObj = pal.get(counter); 
				System.out.println("name:"+pal.get(counter).get("name"));
				
				int[] featureID = (int [])pal.get(counter).get("featureID");
				for(i=0;i<featureID.length && featureID[i] >= 0;i++) {}
				FGsize = i;
				System.out.println("size:"+FGsize);
				
				i=0;
				while(i<pairNum) {
					int FG1 = ran.nextInt(FGsize);
					int FG2 = ran.nextInt(FGsize);					
					int FLoc1 = ran.nextInt(((ArrayList<Point>[])curObj.get("featureLoc"))[FG1].size());
					int FLoc2 = ran.nextInt(((ArrayList<Point>[])curObj.get("featureLoc"))[FG2].size());
					if(FG1 != FG2 || FLoc1 != FLoc2) {
						i++;
						System.out.println(((int[])curObj.get("featureID"))[FG1]+" -> "+((int[])curObj.get("featureID"))[FG2]);
						Point FP1 = ((ArrayList<Point>[])curObj.get("featureLoc"))[FG1].get(FLoc1);
						Point FP2 = ((ArrayList<Point>[])curObj.get("featureLoc"))[FG2].get(FLoc2);
						//System.out.println(FP1.getX()+","+FP1.getY()+" -> "+FP2.getX()+":"+FP2.getY());
						PairedFeatureID.add(((int[])curObj.get("featureID"))[FG1]);
						PairedFeature.add(FP1);
						PairedFeatureID.add(((int[])curObj.get("featureID"))[FG2]);
						PairedFeature.add(FP2);						
					}
				}
				curObj.put("pairedFP", PairedFeature);
			}
			counter++;
		}
	}
	
	public void cleanFeatureCloud() {
		for(int i=0;i<512;i++) {
			mesh[i] = new ArrayList<HashMap>();
		}
	}

	public ArrayList<HashMap> getPal() {
		return pal;
	}
}
