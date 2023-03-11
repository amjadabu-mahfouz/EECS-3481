package asymmetric;

import util.CryptoTools;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;

import javax.crypto.Cipher;


public class RSA_Q2 {


	public static void main(String[] args) throws Exception {
		//find pt/m using the following known variables ....
		
		//Bob's info:
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
		
		BigInteger e = new BigInteger("74327");
		
		//Alice's info:
		//(ct to Bob: encrypted with HIS public key)
		BigInteger ct = new BigInteger("10870101966939556606443697147757930290262227730644958783498257036423105365610629529910525828464329792615002602782366786531253275463358840412867833406256467153345139501952173409955322129689670345445632775574301781800376545448990332608558103266831217073027652061091790342124418143422318965525239492387183438956");
		
		//Hacker's info:
		//you find out the "p" value used in making Bob's key pair
		BigInteger p = new BigInteger("10358344307803887695931304169230543785620607743682421994532795393937342395753127888522373061586445417642355843316524942445924294144921649080401518286829171");
	

		//Task: find out the PT message that Alice sent to Bob
		//[d = (inverse of e) mod phi]  ==> [(1/e) % phi] ==> [ (e^-1) % ((p-1) *(q-1)) ]  

 		BigInteger one = new BigInteger("1");
		
		BigInteger q = n.divide(p);
		
		BigInteger phi = (p.subtract(one)).multiply((q.subtract(one)));
		
		BigInteger d = e.modInverse(phi) ;
		
		BigInteger m = ct.modPow(d, n);
		
		System.out.println("the pt is: " + new String(m.toByteArray()));
	}
	
	
	
}
