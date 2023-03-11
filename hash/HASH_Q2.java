package hash;

import java.math.BigInteger;
import java.util.Arrays;

public class HASH_Q2 {

	
	public static void main(String[] args) throws Exception {
		//find pt/m using the following known variables ....
		
		//Alice's info:
		BigInteger nA = new BigInteger("171024704183616109700818066925197841516671277");
		
		BigInteger eA = new BigInteger("1571");
		
		//Bob's info:
		BigInteger pB = new BigInteger("98763457697834568934613");
		
		BigInteger qB = new BigInteger("8495789457893457345793");
		
		BigInteger eB = new BigInteger("87697");
		
		BigInteger nB = pB.multiply(qB);
		
		// Alice signs "m" using her "d" ==> "s"
		// Alice signs "s" and "m" using bob's public key ==> "ss" & "mm"
		BigInteger ss = new BigInteger("749142649641548101520133634736865752883277237");
		BigInteger mm = new BigInteger("418726553997094258577980055061305150940547956");

		//Task: find out the PT message that Alice sent to Bob
		
		//step 1) get Bob's private key ==> d = (1/e) % phi
 		BigInteger one = new BigInteger("1");
		
		BigInteger phiB = (pB.subtract(one)).multiply(qB.subtract(one));
		
		BigInteger dB = eB.modInverse(phiB);
		
		//step 2): decrypt ss and mm using Bob's private key "d"
		BigInteger s = ss.modPow(dB, nB);
		
		BigInteger m = mm.modPow(dB, nB);
		
		//step 3): decrypt "s" using Alice's public key "e"
		BigInteger m2 = s.modPow(eA, nA);
		
		String match = "";
		
		if(Arrays.equals(m.toByteArray(), m2.toByteArray())) {
			match = "match";
		}
		else match = "DO NOT match";
		
		System.out.println("msg 1: " + new String(m2.toByteArray()) + "\n" + "ms2g: " + new String(m.toByteArray()) + "\n" + "msg1 and msg2 ... " + match );
		
		
		
	}
	
	
}
