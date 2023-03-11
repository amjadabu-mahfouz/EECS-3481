package hash;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SignatureException;
import java.util.Formatter;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class HASH_Q_HMAC {

	public static void main(String[] args) throws SignatureException, NoSuchAlgorithmException, InvalidKeyException 
	{
		String msg = "The quick brown fox jumps over the lazy dog";
		String key = "mySecretKey";
		
		SecretKeySpec secret = new SecretKeySpec(key.getBytes(), "HmacSHA1");
		
		Mac mac = Mac.getInstance("HmacSHA1");
		mac.init(secret);
		byte[] macP2 = mac.doFinal(msg.getBytes());
		
		String macHex = CryptoTools.bytesToHex(macP2);
		System.out.println(macHex);
	}
	
}
