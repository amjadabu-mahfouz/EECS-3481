package classical;

import util.CryptoTools;

public class Ceasar_Encrypt {

	public static void main (String[] args) throws Exception {
		
		byte[] raw = CryptoTools.fileToBytes("data/MSG1.pt");
		byte[] pt = CryptoTools.clean(raw);
		CryptoTools.bytesToFile(pt, "data/MSG1.clean");
		
		//1) make a new byte array for encrypted bytes
		byte[] ct = new byte[pt.length];
		//2) loop through pt and encrpyt with key(key = 19 as sepecified by professor)
		for(int i = 0; i < pt.length; i++) {
			ct[i] = (byte) ((pt[i] - 'A' + 19) % 26 + 'A');
		}
		
		//store CT in file
		CryptoTools.bytesToFile(ct, "data/MSG1.ct");
		
		
		String MD5Hash = CryptoTools.getMD5(ct);
		
		Double ic = CryptoTools.getIC(ct);
		
		
		System.out.println(ic + "");
		
		
	}
	
}
