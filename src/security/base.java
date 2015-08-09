package security;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

public class base {
	byte[] input;
	byte[] keyBytes = "20142015".getBytes();
	byte[] ivBytes = "no41s03a".getBytes();
//	byte[] keyBytes = "2014#2015DI3MD3r".getBytes();
//	byte[] ivBytes = "no41s03a5Y2s3tal".getBytes();
	
	SecretKeySpec key = new SecretKeySpec(keyBytes, "DES");
	IvParameterSpec ivSpec = new IvParameterSpec(ivBytes);
	
	Cipher cipher;
	byte[] cipherText, plainText;
	int ctLength, ptLength;
}
