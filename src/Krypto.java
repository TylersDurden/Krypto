import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.security.*;
import java.nio.charset.StandardCharsets;

/**
 * <KRYPTO> 
 */
public class Krypto {
    
    public static final String [] MODES = {"SHA","DES","AES","MD5","alpha","alphaNum","custom"};
    public static final String [] OPRAH = {"encrypt","decrypt"};
    public boolean runnable = false;
    static Vector<String> oprash = new Vector<>();
    
    public Krypto(String mode, String style, String target_file){
        this.oprash.add(mode);
        this.oprash.add(style);
        this.oprash.add(target_file);
        if(this.oprash.size()==3){System.out.println("<KRYPTO> loaded. ");runnable=true;}
        if(runnable){new KRYPTO(oprash);}
    }
    
    public static void main(String[]args){
        if(args.length<2){usageStatement();System.exit(0);}
        else{
            String cipherOpt = args[0].split("-")[1];
            String modeOpt = args[1].split("-")[1];
            String target  = args[2].split("-")[1];
            //Make sure these opts are valid 
            if(validUserOptions(cipherOpt,modeOpt,target)){
                Krypto K = new Krypto(cipherOpt,modeOpt,target);
            }else{usageStatement();}
        }
        
    }
    
    /** check the user options for what the program is designed for */
    public static boolean validUserOptions(String ciph, String mode, String target){
        boolean areValid = false;
        boolean goodciph = false;
        boolean goodmode = false;
        boolean goodfile = false;
        
        for(String m : MODES){//Check if cipher type is valid
            if(m.compareTo(ciph)==0){goodciph=true;break;}
        }// Check the mode 
        if(mode.compareTo(OPRAH[0])==0 || mode.compareTo(OPRAH[1])==0){
            goodmode = true;
        }// is the file there to operate on
        File f = Paths.get(System.getProperty("user.dir")+"/InputFiles",target).toFile();
        if(f.isFile()){goodfile=true;}
        if(goodciph && goodmode && goodfile){areValid = true;}
        return areValid;
    }
    
    /** Display help on how to use input arguments */
    public static void usageStatement(){
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");
        System.out.println("* Incorrect Usage. Please re-enter command with following style:    *");
        System.out.println("* $ java Krypto -[CipherType] -[mode]                               *");
        System.out.println("* MODES: -SHA, -DES, -AES, -MD5, -custom, -alpha, -alphaNum         *");
        System.out.println("* TYPES: -encypt, -decrypt                                          *");
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * * *");   
    }
    
    /** <RunnabeClass:_Krypto.KRYPTO>*/
    private static class KRYPTO implements Runnable{
        private static int encdec = -1;
        static Vector<String> IV = new Vector<>();
        
        /**<KRYPTO:Constructor(Vec)>
            <MODE:cipher>
            <STYLE:enc/dec>
            <TARGET:file>
         */
        public KRYPTO(Vector<String>initialVect){
            this.IV = initialVect;
            if(initialVect.get(1).compareTo("encrypt")==0){this.encdec=0;}
            else{this.encdec=1;}
            run();
        }
        
        public void run(){
            switch(this.encdec){
                case 1:
                    System.out.println("Cracking is under development");
                    break;
                case 0:
                    System.out.println("Encrypting "+this.IV.get(2));
                    String path = Paths.get(System.getProperty("user.dir"),this.IV.get(2)).toString();
                    if(this.IV.get(0).compareTo("SHA")==0){
                        String cmd = this.IV.get(0)+" "+this.IV.get(2);
                        // First grab the SHA-256 checksum of the file 
                        // before encryption 
                        try{executioner("./sha.sh "+path);}
                        catch(Exception e){e.printStackTrace();}
                        // Now scramble the file 
                        
                    }
                    if(this.IV.get(0).compareTo("DES")==0){
                        String cmd = "./des.sh "+this.IV.get(0)+" "+this.IV.get(2);
                        try{executioner(cmd);}
                        catch(Exception e){e.printStackTrace();}
                    }
                    if(this.IV.get(0).compareTo("AES")==0){
                        String cmd = "./AES.sh" + this.IV.get(0)+" "+this.IV.get(2);
                        try{executioner(cmd);}
                        catch(Exception e){e.printStackTrace();}
                    }
                    if(this.IV.get(0).compareTo("MD5")==0){
                        String cmd = this.IV.get(0)+" "+this.IV.get(2);
                    }
                    if(this.IV.get(0).compareTo("A")==0){
                        
                    }
                    if(this.IV.get(0).compareTo("A9")==0){
                        
                    }
                    if(this.IV.get(0).compareTo("X")==0){
                        
                    }
                    break;
                case -1:
                    break;
                default:
                    break;
            }
            
               
        }
        
        
    /** <EXECUTIONER:_execute_shellScripts_from_inside_java_>
     * This class allows you to send in the name of a shell script
     * that is in the current directory, and execute it synchronously
     * from within the java program.
     */
    private static void executioner(String cmd) throws Exception{
        try{
           // Path load = Paths.get(System.getProperty("user.dir"),script);
           // String cmd = new String(load.toString());
            Runtime rt = Runtime.getRuntime();
            Process proc = rt.exec(cmd);
            proc.waitFor();
            StringBuffer out = new StringBuffer();
            BufferedReader br = new BufferedReader(new InputStreamReader(proc.getInputStream()));
            String line = "";
            while((line = br.readLine()) != null){out.append(line+"\n");}
            System.out.println(out);
        }catch(Throwable t){t.printStackTrace();}
    }
        
    }/** <EndOf:Krypto.KRYPTO>*/
   
}/** <EndOf:Krypto> */