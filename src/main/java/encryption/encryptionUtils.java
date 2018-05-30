package encryption;
import java.math.BigInteger;
import java.security.*;

public class encryptionUtils {
	
	private static final String ENCRYPTION_TYPE = "MD5";
	private static final String SECRET_STRING = "potsXiweiSorigueVila";
	
	public encryptionUtils(){}
	
	public String encrypt(String password){
		System.out.println(password);
		password += SECRET_STRING;
		
		try{
			MessageDigest message = MessageDigest.getInstance(ENCRYPTION_TYPE);
			message.reset();
			message.update(password.getBytes());
			byte[] digest = message.digest();
			BigInteger bigInt = new BigInteger(1, digest);
			String md5 = bigInt.toString(16);
			
			while(md5.length() < 32){
				md5 = "0"+ md5;
			}
			System.out.println("MD5 returned: " + md5);
			return md5;
			
		}catch(Exception e){
			e.printStackTrace();
			System.out.println("Error: " + e.getMessage());
		}
		return null;
	}
}
