package asymmetric;

import util.CryptoTools;
import java.math.BigInteger;

//RSA libs
import java.security.KeyFactory;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import javax.crypto.Cipher;


public class RSA_Q1 {

	
	public static void main(String[] args) throws Exception {
		//find pt/m using the following known variables
		BigInteger n = new BigInteger("94587468335128982981605019776781234618384857805657005686084562260910788622013722070926491690843853690071248130134427832324966728582532832363221542231787068203763027067400082835394459857525017707284768411819006776211493735326500782954621660256501187035611332577696332459049538105669711385995976912007767106063");
		
		BigInteger e = new BigInteger("74327");
		
		BigInteger d = new BigInteger("7289370196881601766768920490284861650464951706793000236386405648425161747775298344104658393385359209126267833888223695609366844098655240542152017354442883676634193191857568369042999854440242050353181703706753485749165295123694487676952198090537385200990850805837963871485320168470788328336240930212290450023");
		
		BigInteger ct = new BigInteger("87014856975716299121085087309577038316883175412853820115551293556230488405826385706604303724175236985573832006395540199066061101502996745421485579743246846982636317440505885092956723199407403632041108913018671613508572002898008615700858579079601105011909417884801902333329415712320494308682279897714456370814");
	
		
		
		// MANUALLY: [ m/pt = (ct ^ d) % n]  **************************************
		BigInteger m = ct.modPow(d, n);
		
		System.out.println("pt (from manual) is: " + new String(m.toByteArray()));
		// MANUALLY ***************************************************************
	
		
		
		
		
		
		
		
		
		// Using Java Lib: *******************************************************
		  KeyFactory keyFactory = KeyFactory.getInstance("RSA");
	      
		  RSAPublicKeySpec pubKeySpec = new RSAPublicKeySpec(n, e);
		  RSAPrivateKeySpec privKeySpec = new RSAPrivateKeySpec(n, d);
		  
	      RSAPublicKey pubKey = (RSAPublicKey) keyFactory.generatePublic(pubKeySpec);
	      RSAPrivateKey privKey = (RSAPrivateKey) keyFactory.generatePrivate(privKeySpec);
	      
	      
	      Cipher cipher = Cipher.getInstance("RSA/ECB/NoPadding");
	      cipher.init(Cipher.DECRYPT_MODE, privKey);
	      byte[] pt = cipher.doFinal(ct.toByteArray());
	      
	      System.out.println("pt (from java lib) is: " + new String(pt).trim());
		// Using Java Lib: *******************************************************
		
	}
	
	
}
