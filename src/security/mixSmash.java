package security;

import java.security.Security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

import org.bouncycastle.util.encoders.Base64;

public class mixSmash {
	
	public mixSmash(){
		
	}
	
	public byte[] encrypt(String password) {
		base Base = new base();
		
		try{
			Security.addProvider(new org.bouncycastle.jce.provider.BouncyCastleProvider());
			Base.input = password.getBytes();
			Base.cipher = Cipher.getInstance("DES/OFB/NoPadding");
			
			Base.cipher.init(Cipher.ENCRYPT_MODE, Base.key, Base.ivSpec);
			
			Base.cipherText = new byte[Base.cipher.getOutputSize(Base.input.length)];
			
			Base.ctLength = Base.cipher.update(Base.input, 0, Base.input.length, Base.cipherText, 0);
			
			Base.ctLength += Base.cipher.doFinal(Base.cipherText, Base.ctLength);
			//System.out.println(Base.ctLength);
			return Base.cipherText;
		}catch(Exception e){
			System.out.println("Error: " + e);
			return null;
		}
	}

	public String decrypt(byte[] password) {
		base Base = new base();
		
		try {
			//Base.input = password.getBytes();
			Base.cipher = Cipher.getInstance("DES/OFB/NoPadding");
			Base.cipher.init(Cipher.DECRYPT_MODE, Base.key, Base.ivSpec);
			Base.plainText = new byte[Base.cipher.getOutputSize(password.length)];
			
			Base.ptLength = Base.cipher.update(password, 0, password.length, Base.plainText, 0);

			Base.ptLength += Base.cipher.doFinal(Base.plainText, Base.ptLength);
			
			return new String(Base.plainText);
	    } catch (Exception e) {
	    	System.out.println("Error: " + e);
	        return null;
	    }
	}
	
	
}
