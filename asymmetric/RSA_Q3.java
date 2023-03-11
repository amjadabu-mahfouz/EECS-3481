package asymmetric;

import java.math.BigInteger;

public class RSA_Q3 {

	public static void main(String[] args) throws Exception {
		//find pt/m using the following known variables ....
		
		BigInteger phi = new BigInteger("8584037913642434144111279062847405921823163865842701785008602377400681495147541519557274092429073976252689387304835782258785521935078205581766754116919200");
		BigInteger q = new BigInteger("87020952829623092932322362936864583897972618059974315662422560067745889600571");
		BigInteger e = new BigInteger("65537");
		BigInteger ct = new BigInteger("1817487313698347891034157970684926175211834109573277793102901854482611726141560963120214926234448852417078321539316776648109260519063106558303669880226359");

	

		//Task: find out the PT message
		//the unkown variables we need to decrypt are...
		//p: dont know p) ... through simple algebra ... [phi = (p-1)*(q-1)] ==> (p-1) = phi / (q-1)
		//d: [d = (inverse of e) mod phi]  ==> [(1/e) % phi] ==> [ (e^-1) % ((p-1) *(q-1)) ]  
		//N: N = p*q

 		BigInteger one = new BigInteger("1");
		
 		BigInteger pMinusOne = phi.divide((q.subtract(one)));
		
 		BigInteger p = pMinusOne.add(one);
 		
 		BigInteger d = e.modInverse(phi);
 		
 		BigInteger n = q.multiply(p);
		
		BigInteger m = ct.modPow(d, n);
		
		System.out.println("the pt is: " + new String(m.toByteArray()));
	}
	
}
