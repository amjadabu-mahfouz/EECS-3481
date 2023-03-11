package symmetric;

import java.security.Key;
import java.security.spec.AlgorithmParameterSpec;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class BQ3 {

	//convert hex string into ascii string
	public static String hex2String(String hex) {
		StringBuilder sb = new StringBuilder();
		StringBuilder temp = new StringBuilder();
		
		//group into pairs of 2 hex chars (4 BITS EACH ... 8 BITS BOTH...OR 1 BYTE)
		for(int i = 0; i < hex.length()-1; i++ ) {
			
			String out =  hex.substring(i, (i+2));
			
			int decimal = Integer.parseInt(out, 16);
			
			sb.append((char)decimal);
			
			temp.append(decimal);
		}
		
		return sb.toString();
	}
	
	
	
	
	public static void main(String[] args) throws Exception {
	byte[] ky = "CSE@YORK".getBytes();
	byte[] iv = CryptoTools.hexToBytes("0123456701234567");  
	
    // 0000000000000000 FFFFFFFFFFFFFFFF 	
	//the CBC encrypts with 64bit blocks ... trnalated into hex str.. that would be 16 HEXchars/block
	//get the last 2 blocks (the first one is missing (all 0))
	//since you are missing the first block CBC wont work; ONLY the 3rd blocks pt can be found since you need the IV(the iv in the 3rd blocks case is the ct from block 2) and key to decrypt a given block  
	//byte[] ct = CryptoTools.hexToBytes("00000000000000004E51297B424F90D8B2ACD6ADF010DDC4");
	
	byte[] ct3 = CryptoTools.hexToBytes("B2ACD6ADF010DDC4");
	byte[] ct2  = CryptoTools.hexToBytes("4E51297B424F90D8");
	
	
	Key secret = new SecretKeySpec(ky, "DES");
	Cipher cipher = Cipher.getInstance("DES/CBC/PKCS5Padding");
	AlgorithmParameterSpec aps = new IvParameterSpec(ct2);
	
	cipher.init(Cipher.DECRYPT_MODE, secret, aps);
	byte[] bk = cipher.doFinal(ct3);
	System.out.println("BK = " + new String(bk) + "<");
	
	
	}
	
	
	
}
