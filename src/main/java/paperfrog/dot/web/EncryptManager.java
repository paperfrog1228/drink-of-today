package paperfrog.dot.web;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
@Component
public class EncryptManager {
    @Value("${encrypt.salt}")
    String salt="";
    @Value("${encrypt.iteration}")
    int iteration=;
    public String encrypt(String text) throws NoSuchAlgorithmException {
        byte[] plain=addSalt(text,salt);
        return bytesToHex(iteratorDigest(plain));
    }
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
    private byte[] addSalt(String text,String salt){
        byte[] saltPlain=new byte[text.length()+salt.length()];
        System.arraycopy(text.getBytes(),0,saltPlain,0,text.getBytes().length);
        System.arraycopy(salt.getBytes(),0,saltPlain,text.getBytes().length,salt.getBytes().length);
        return saltPlain;
    }
    private byte[] iteratorDigest(byte[] plain) throws NoSuchAlgorithmException {
        System.out.println("iter  : "+iteration);
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] inputDigest = md.digest(plain);
        for(int i=0;i<iteration;i++){
            md.reset();
            inputDigest = md.digest(inputDigest);
        }
        return inputDigest;
    }
}
