package paperfrog.dot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import paperfrog.dot.web.EncryptManager;

import java.security.NoSuchAlgorithmException;

@SpringBootApplication
public class DrinkOfTodayApplication {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		SpringApplication.run(DrinkOfTodayApplication.class, args);
	}
}
