package classical;

import util.CryptoTools;

public class Caesar_Analysis {
	
	public static void main(String[] args) throws Exception {
		
		byte[] ct = CryptoTools.fileToBytes("data/MSG2.ct");
		byte[] pt = new byte[ct.length];
		String res = "";
		
		//get frequencies
		int[] frequencies = CryptoTools.getFrequencies(ct);
		//get % of occurence from the # of occurences
		double[] freqDouble = new double[26];
		for(int i = 0; i < 26; i++) {
			freqDouble[i] = frequencies[i] / 26.00 ;
			res += "letter " + i + " : " + freqDouble[i] + "\n";
		}
		
		
		System.out.println(res); //** the most frequent letter in CT is "A" ...and in pt(ENGLISH) its "E" so... the key must be +4 or -26
		
		//decipher using found key from freuency analysis
		for(int i = 0; i < ct.length; i++) {
			pt[i] = (byte)((ct[i] - 'A' + 4) % 26 + 'A');
		}
		CryptoTools.bytesToFile(pt, "data/MSG2.pt");
		
		
		
		
		
	}

}



