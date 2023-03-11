package classical;

import java.math.BigInteger;

import util.CryptoTools;

public class A_Exhaustive {

	public static void main(String[] args) throws Exception {
		
		byte[] ct = CryptoTools.fileToBytes("data/MSGx1.ct");
		byte[] temp = new byte[ct.length];
		
		double[] eng = CryptoTools.ENGLISH;
		
		int[] frq = new int[26];
	
		
		BigInteger bullshit = BigInteger.valueOf(26);
		
		String res = "";
		
		String max = "";
		int[] maxx = new int[3];
		maxx[0] = 0;
		
		//Values of a : 1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, and 25 hav to be coprime with 26
		int[] a = {1, 3, 5, 7, 9, 11, 15, 17, 19, 21, 23, 25};
		
		
		for(int i = 0; i < a.length; i++) {
			for(int j = 1; j < 26; j++) {
				
				for(int k = 0; k < ct.length; k++) {
					//convert so you can reverse mod
					BigInteger x = BigInteger.valueOf(a[i]) ;
					x.modInverse(bullshit);
					
					//Bs you have to do in order to decode
					int hold = (((ct[k] - 'A' - j + 'A') * x.intValue()));
					temp[k] = (byte) (hold % 26 + 'A');
				}
				
				frq = CryptoTools.getFrequencies(temp);
			
				int dot = 0;
				for(int f = 0; f < 26; f++) {
					dot += frq[f] * eng[f];
				}
				
				if(dot > maxx[0]) {
					maxx[0] = dot;
					maxx[1] = a[i];
					maxx[2] = j;
				}
				
				res += "Dot product : " + dot + " || a : " + a[i] + " || b : " + j + "\n";
				
				
			}
		}
		
		System.out.println(res + "MAX!! dot, a, b : " + maxx[0] + ", " + maxx[1] + ", " + maxx[2]);
		
		
		
		//decrypt
		temp = new byte[ct.length];
		
		BigInteger x = BigInteger.valueOf(maxx[1]) ;
		x.modInverse(bullshit);
		
		for(int i = 0; i < ct.length; i++) {
			
			int hold = (((ct[i] - 'A' - maxx[2] + 'A') * x.intValue()));
			temp[i] = (byte) (hold % 26 + 'A');
		}
		
		
		CryptoTools.bytesToFile(temp, "data/MSG3.pt");
	}
	
	
}




