import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.security.*;
import java.nio.charset.StandardCharsets;

/**
 * 
 */
public class iByte {
    
    
    
    public iByte(String target, String algorithm, String mode){
        
        switch(algorithm){
            case "SHA":
                new SHAkrAttack(target,mode);
                break;
            case "DES":
                new DEStiny(target,mode);
                break;
            case "AES":
                new AEShole(target,mode);
                break;
            case "MD5":
                
                break;
            default:
                break;
        }
        
    }   
    
    public static void main(String[]args){
        if(args.length<2){System.out.println("!? Incorrect Usage ?! ");
            System.exit(0);}
        else{new iByte(args[1],args[2],args[0]);}
        
    }
    
    /** <SHAkrAttack!>
     * 
     */
    private static class SHAkrAttack implements Runnable{
        
        private static String TARGET;
        private static String MODE;
        private static boolean ENC = false;
        private static boolean DEC = false;
        public static String message = null;
        public static String encryptedMessage;
        private SHAkrAttack(String target, String mode){
            System.out.println("<((SHAkrATTACK!))>");
            System.out.println("<MMMMMMMMMMMMMMMM>");
            System.out.println("<XXXXXXXXXXXXXXXX>");
            System.out.println("<WWWWWWWWWWWWWWWW>");
            this.TARGET = target;
            this.MODE = mode;
            
            int op = -1;
            switch(this.MODE){
                case "encrypt":
                    op = 1;
                    break;
                case "decrypt":
                    op = 0;
                    break;
                default:
                    op=-1;
                    break;
            }
            if(op==1){this.ENC=true;}
            if(op==0){this.DEC=true;}
            if(op==-1){System.out.println("OPERATION ERROR!");System.exit(1);}
            
            run();
        }
        
        /** <GetFileContents> */
        protected Vector<String> getContents(){
            Vector<String>ans = new Vector<>();
            BufferedReader br = null;
            // Grab the string information from the target file
            File f = Paths.get("/projects/Krypto/src/InputFiles/",this.TARGET).toFile();
            try{
                String ln;
                br = new BufferedReader(new FileReader(f));
                while((ln = br.readLine()) != null){ans.add(ln);}
            }catch(IOException e){e.printStackTrace();}
            return ans;
        }
        
        
        public void run(){
            String hash = null;
            if(this.ENC){
                String decr = "";
                Vector <String> msg = getContents();
                for(String m : msg){decr+=m+"\n";}
                this.message = decr;
                try{
                    
                MessageDigest md = MessageDigest.getInstance("SHA-256");
                byte[]b = this.message.getBytes(StandardCharsets.UTF_8);
                StringBuffer sb = new StringBuffer();
                byte [] bs = md.digest(b);
                for(byte byt : bs){
                    String hex = Integer.toHexString(0xff & byt);
                    if(sb.length()==1)
                    sb.append('0');
                    sb.append(hex);
                }
                hash = sb.toString();
                }catch(NoSuchAlgorithmException e){System.out.println("Algorithm Error!");}
            }else{
                // Work on this later... lol 
            }
            
            System.out.println("Encrypted Message:\n"+hash);
            this.encryptedMessage = hash;
        }
        
    }/** <EndOf:__SHAkrAttack__>*/
    
    private static class DEStiny implements Runnable{
        
        private static String TARGET;
        private static String MODE;
        
        private DEStiny(String target, String mode){
            System.out.println("* * * * * * * * * * * * * * * * * *");
            System.out.println("* * = * = * = * = * = * = * = * = *");
            System.out.println("*-{##}{}))<:_DES/tiny_:>((}{}{##}-*");
            System.out.println("* * = * = * = * = * = * = * = * = *");
            System.out.println("* * * * * * * * * * * * * * * * * *");
            
            this.TARGET = target;
            this.MODE = mode;
            run();
        }
        
        public void run(){
            
        }
        
    }/** <EndOf:__DEStiny__> */
   
   /** 
    * 
    */
   private static class AEShole implements Runnable{
       
       private AEShole(String target,String mode){
           
       }
       
       public void run(){
           
       }
   }/** <EndOf:__AEShole__> */
  
  
  private static class DOC5 implements Runnable{
      
      private DOC5(String target, String mode){
          
      }
      public void run(){
          
      }
      
  }/** <EndOf:__DOC5__> */
    
    
}/** <EndOf:__iByte__>*/