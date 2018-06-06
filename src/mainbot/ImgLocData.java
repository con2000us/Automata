package mainbot;



public class ImgLocData {
	DataLoc[] Locs = new DataLoc[25];

	public ImgLocData() {
		Locs[0] = new DataLoc("PRESSURE",			789,168,60,15,		859,168,60,15);
		Locs[1] = new DataLoc("APC ANGLE",			789,188,60,15,		859,188,60,15);
		Locs[2] = new DataLoc("TOP RF FWD",			789,208,60,15,		859,208,60,15);
		Locs[3] = new DataLoc("TOP RF REF",			789,228, 0, 0,		859,228,60,15);
		Locs[4] = new DataLoc("BTM RF FWD",			789,248,60,15,		859,248,60,15);
		Locs[5] = new DataLoc("BTM RF REF",			789,268, 0, 0,		859,268,60,15);
		Locs[6] = new DataLoc("GAS1",				789,288,60,15,		859,288,60,15);
		Locs[7] = new DataLoc("GAS2",				789,308,60,15,		859,308,60,15);
		Locs[8] = new DataLoc("GAS3",				789,328,60,15,		859,328,60,15);
		Locs[9] = new DataLoc("GAS4",				789,348,60,15,		859,348,60,15);
		Locs[10] = new DataLoc("GAS5",				789,368,60,15,		859,368,60,15);
		Locs[11] = new DataLoc("GAS6",				789,388,60,15,		859,388,60,15);
		Locs[12] = new DataLoc("GAS7",				789,408,60,15,		859,408,60,15);
		Locs[13] = new DataLoc("GAS8",				789,428,60,15,		859,428,60,15);
		Locs[14] = new DataLoc("CENT.B.He PRESS",	789,448,60,15,		859,448,60,15);
		Locs[15] = new DataLoc("CENT.B.He FLOW",	789,468, 0, 0,		859,468,60,15);
		Locs[16] = new DataLoc("TOP TEMP",			789,488,60,15,		859,488,60,15);
		Locs[17] = new DataLoc("WALL TEMP",			789,508,60,15,		859,508,60,15);
		Locs[18] = new DataLoc("BTM TEMP",			789,528,60,15,		859,528,60,15);
		Locs[19] = new DataLoc("CHUNK TEMP",		789,548, 0, 0,		859,548,60,15);
		Locs[20] = new DataLoc("WAFER SENSOR",		789,568, 0, 0,		859,568,60,15);
		Locs[21] = new DataLoc("CLAMP STATUS",		789,608, 0, 0,		859,608,60,15);
		Locs[22] = new DataLoc("PIN STATUS",		789,628, 0, 0,		859,628,60,15);
		Locs[23] = new DataLoc("BTM2(OUT)",			789,648,60,15,		859,648,25,15);
		Locs[24] = new DataLoc("CHUNK2",			789,648,60,15,		894,648,25,15);
	}
	
}
