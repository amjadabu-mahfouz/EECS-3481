package hash;

import java.security.Key;
import java.security.MessageDigest;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import util.CryptoTools;

public class HASH_Q_MAC {

	public static void main(String[] args) throws Exception
	{
		String message = "No one can make you feel inferior without your consent.";
		
		MessageDigest md = MessageDigest.getInstance("MD5");
	    byte[] hash = md.digest(message.getBytes());
		
		byte[] ky = "themselvesforwar".getBytes();
		Key secret = new SecretKeySpec(ky, "AES");
		Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
		
		cipher.init(Cipher.ENCRYPT_MODE, secret);
		byte[] ct = cipher.doFinal(hash);
		
		System.out.println("The MAC is: " + CryptoTools.bytesToHex(ct));
		
	}
	
}
