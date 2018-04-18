import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.IvParameterSpec;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.Scanner;
import javax.crypto.Cipher;
/***************************************************************** 
 * Compilation: DES.java
 * No Dependencies 
 * @author  Scott Robbins
 * @info
 * This is a very very simple DES encryption interface which I will
 * use to handle the encrypting of information to pass between the
 * BankAccount and SimpleBank classes in this java project. 
 ******************************************************************/
  public class DEStiny{
	/** Initialization Vector is required for DES algorithm! */
	static byte [] iv = { 0x0a, 0x01, 0x02, 0x03, 0x04, 0x0b, 0x0c, 0x0d }; 
	/**Encryption in DES only requires the bytes to be encrypted 
	 * A pre-configured Secret Key and the Padding instance held
	 * with the String xform. */
	static byte[] encrypt(byte[] inpBytes, SecretKey key, String xform) throws Exception {
	    Cipher cipher = Cipher.getInstance(xform);
	    IvParameterSpec ips = new IvParameterSpec(iv);
	    cipher.init(Cipher.ENCRYPT_MODE, key, ips);
	    return cipher.doFinal(inpBytes);
	  }
	/** Decryption with DES is requires the bytes to be decrypted, 
	 * a pre-configured SecretKey and a Padding instance (xform) */
	static byte[] decrypt(byte[] inpBytes,SecretKey key, String xform) throws Exception {
		Cipher cipher = Cipher.getInstance(xform);//error
		IvParameterSpec ips = new IvParameterSpec(iv);
		cipher.init(Cipher.DECRYPT_MODE, key, ips);
		return cipher.doFinal(inpBytes);
	  }
	/** This method converts an array of characters (configured for UTF-8) to an array of bytes*/
	static byte[] toBytes(char[] chars) {
	    CharBuffer charBuffer = CharBuffer.wrap(chars);
	    ByteBuffer byteBuffer = Charset.forName("UTF-8").encode(charBuffer);
	    byte[] bytes = Arrays.copyOfRange(byteBuffer.array(),
	            byteBuffer.position(), byteBuffer.limit());
	    Arrays.fill(charBuffer.array(), '\u0000'); // clear sensitive data
	    Arrays.fill(byteBuffer.array(), (byte) 0); // clear sensitive data
	    return bytes;
	}
	/** This method converts and array of bytes to characters. The only tricky thing
	 * being done is this method is the 0xFF offset. This is because we know the 
	 * bytes being converted are going to end up being characters used in plaintext. */
	static char [] B2C(byte[]bytes){
		char[] chars = new char[bytes.length];
		for(int i=0;i<bytes.length;i++){
			byte b;
			b = bytes[i];
			char c = (char) (b & 0xFF);
			chars[i]=c;
		}
		return chars;
	}
	/**Break the text into characters, then bytes and then encode bytes. After encoding, 
	 * check to make sure that the decrypted bytes match the unencrypted bytes to ensure 
	 * that no information was lost in the process.*/
	public static void main(String[]args) throws Exception {
			//Create the padding and the keygenerator 
		    String xform = "DES/CBC/PKCS5Padding";
		    KeyGenerator kg = KeyGenerator.getInstance("DES"); 
		    kg.init(56); // 56 is fixed key size for DES
		    SecretKey key = kg.generateKey();//Generate a secret key
		    //Ask for input to be encrypted 
		    System.out.println("Enter the text that you would like to encrypt:");
		    //Use a scanner to get the user input
		    Scanner sc = new Scanner(System.in);
		    String text = sc.nextLine(); 
		    sc.close();
		    //Encode the text
		    System.out.println("\n*** ENCRYPTING ***");
		    char[] lets = text.toCharArray();
		    byte[] dataBytes = toBytes(lets);
		    byte[] encBytes = encrypt(dataBytes, key, xform);
		    byte[] decBytes = decrypt(encBytes, key, xform);
		    boolean expected = Arrays.equals(dataBytes, decBytes);
		    if(expected==true){System.out.println("Successful");}else{System.out.println("FAILED");}
		    //Validate the encryption process and create a string from decoded characters
		    char [] fin = B2C(decBytes);
		    String b = new String(fin);
		    /**Display encrypted and decrypted data before clearing it from memory */
		    System.out.println("Encrypted data:  "+encBytes);
		    System.out.println("");
		    System.out.println("*** DECRYPTING ***");
		    System.out.println(b);
		    Arrays.fill(dataBytes, (byte) 0); // clear sensitive data
		 
		  }
}
