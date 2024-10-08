package edu.wgu.d387_sample_code;

import edu.wgu.d387_sample_code.conTim;
import edu.wgu.d387_sample_code.conTimCon;
import edu.wgu.d387_sample_code.locale.DisplayMessage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.core.io.ClassPathResource;

import java.io.InputStream;
import java.util.Locale;
import java.util.Properties;

@SpringBootApplication
public class D387SampleCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(D387SampleCodeApplication.class, args);

		DisplayMessage engMessage = new DisplayMessage(Locale.US);
		DisplayMessage freMessage = new DisplayMessage(Locale.CANADA_FRENCH);

		Thread engThread = new Thread(engMessage);
		Thread freThread = new Thread(freMessage);

		engThread.start();
		freThread.start();



		System.out.println("Testing time conversion: " + conTim.grabZone());
	}

}