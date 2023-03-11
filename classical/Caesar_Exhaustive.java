package classical;

import util.CryptoTools;

public class Caesar_Exhaustive {
	
	
	public static void main(String[] args) throws Exception {
		
		byte[] ct = CryptoTools.fileToBytes("data/MSG2.ct");
		byte[] test = new byte[ct.length];
		
		int[][] freq = new int[26][];
		
		for(int i = 0; i < 26; i ++) {
			
			for(int j = 0; j < ct.length; j++) {
				test[j] = (byte)((ct[j] - 'A' + i) % 26 + 'A');
			}
			
			freq[i] = CryptoTools.getFrequencies(test);
			
		}
		
		//compute the dot product 
		double[] engFreq = CryptoTools.ENGLISH; 
		double[] dotProd = new double[26];
		
		double x = 0.0;
		
		for(int i = 0; i < 26; i++) {
			x = 0;
			
			for(int j = 0; j < 26; j++) {
				x += freq[i][j] * engFreq[j];
			}
			
			dotProd[i] = x; 
		}
		
		//get maximum dot product inorder to find the correct shift
		double max = 0.0;
		int rightKey = 0;
		for(int i = 0; i < 26; i++) {
			if(dotProd[i] > max) {
				max = dotProd[i];
				rightKey = i;
			}
		}
	
		
		
		//decipher txt using the right key (found in previous array)
		byte[] pt = new byte[ct.length];
		
		for(int i = 0; i < ct.length; i++) {
			pt[i] = (byte)((ct[i] - 'A' + rightKey) % 26 + 'A');
		}
		CryptoTools.bytesToFile(pt, "data/MSG2.pt");
		
		
		System.out.println("");
		//find max dot Product
		
		
		
		
		
		
		
		
		
		
	}
	

}
