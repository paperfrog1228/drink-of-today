package paperfrog.dot;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@SpringBootApplication
public class DrinkOfTodayApplication {
	public static void main(String[] args) {
		SpringApplication.run(DrinkOfTodayApplication.class, args);
	}
}
