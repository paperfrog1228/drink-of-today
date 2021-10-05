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
    int iteration;
    public String encrypt(String text) throws NoSuchAlgorithmException {
        MessageDigest md=MessageDigest.getInstance("sha-256");
        md.update(text.getBytes());
        md=addSalt(md,salt);
        md=iteratorDigest(md,iteration);
        return bytesToHex(md.digest());
    }
    private String bytesToHex(byte[] bytes) {
        StringBuilder builder = new StringBuilder();
        for (byte b : bytes) {
            builder.append(String.format("%02x", b));
        }
        return builder.toString();
    }
    private MessageDigest addSalt(MessageDigest md,String salt){
        md.update(salt.getBytes());
        return md;
    }
    private MessageDigest iteratorDigest(MessageDigest md,int iteration) {
        byte[] iterateDigest;
        for(int i=1;i<iteration;i++) {
            iterateDigest = md.digest();
            md.update(iterateDigest);
        }
        return md;
    }
}
