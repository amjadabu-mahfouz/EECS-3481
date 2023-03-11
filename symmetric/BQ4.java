package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;


public class BQ4 {

	public static void main(String[] args)throws Exception {
		//(ignore this)since its the same as cbc BUT with xor in a diff place.... we can emulate the CBC by using the watered down version on cbc : "ECB"
		//since this is a modified version of CBC.... best to do 1 16 hex char block at a time ... (437DBAB5607137A5CFC1031114634087)
		byte[] ct1 = CryptoTools.hexToBytes("437DBAB5607137A5");
		byte[] ct2 = CryptoTools.hexToBytes("CFC1031114634087");
		
		byte[] ky = CryptoTools.hexToBytes("6B79466F724D4F50");
		//byte[] iv = CryptoTools.hexToBytes("6976466F724D4F50");
		
		Key secret = new SecretKeySpec(ky, "DES");
		Cipher cipher = Cipher.getInstance("DES/CBC/NoPadding");
		
		
		
		String ogIV = CryptoTools.bytesToBin(ct1);
		String negIV = CryptoTools.bitwise(ogIV);
		String hexIV = CryptoTools.bin2Hex(negIV);
		byte[] negatedIV = CryptoTools.hexToBytes(hexIV);
		
		
		
		AlgorithmParameterSpec aps = new IvParameterSpec(negatedIV);
		
		cipher.init(Cipher.DECRYPT_MODE, secret, aps);
		byte[] bk = cipher.doFinal(ct2);
		System.out.println("BK = " + new String(bk) + "<");
		
	
	
	}
	
}
