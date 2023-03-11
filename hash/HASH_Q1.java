package hash;

import java.security.MessageDigest;
import java.util.Arrays;
import java.math.BigInteger;

import util.CryptoTools;

public class HASH_Q1 {

	public static void main(String[] args) throws Exception {
		
		//using these known variables (known to sender)...
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
		
		BigInteger e = new BigInteger("74327");
		

		String pt = "Meet me at 5 pm tomorrow";
		
		
		//simulate message integrity check via hash ...
		
		//on sender side.... 
		//step 1): turn pt into BigInteger then encrypt
		BigInteger m = new BigInteger(pt.getBytes());
		
		BigInteger ct = m.modPow(e, n);
		
		//step 2): put pt through a 1 way hash function
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		byte[] hash = md.digest(pt.getBytes());
		
		
		
		System.out.println("the " + integrityCheck(ct, hash));
		
		
		
	}
	

	public static String integrityCheck(BigInteger ct, byte[] hash) throws Exception {
		//private key "d" is the reciever's private key & "n" is public 
		BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
		
		//on the recieving side...
		//step 1): decrypt the ct
		BigInteger m = ct.modPow(d, n);
		
		//step 2): hash the m/pt using the same hash algo and compare to the hashes to ensure integrity 
		MessageDigest md = MessageDigest.getInstance("MD5");
		
		byte[] hash2 = md.digest(m.toByteArray());
		
		
		//TO COMPARE ARRAYS USE THE .equals() METHOD OF THE ARRAYS CLASS
		if(Arrays.equals(hash, hash2)) {
			return "integrity = good";
		}
		else {
		return "integrity = bad";	
		}
		
	}
	
	
}
