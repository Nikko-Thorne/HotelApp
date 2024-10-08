
 
#Multithreaded Hotel Scheduling Application



Description

This repository contains a modified version of the Hotel scheduling application, featuring a Java Spring Boot backend and an Angular frontend. The application has been enhanced to support localization, internationalization, and several key functionalities, including:

Multithreaded Language Translation: The application displays welcome messages in both English and French using separate threads, ensuring responsive and efficient language handling and compliance with Canadian language requirements.

Currency Exchange Display: Reservations now show pricing in multiple currencies, including U.S. dollars ($), Canadian dollars (C$), and euros (€), allowing users to view and understand pricing more effectively.

Time Zone Conversion: A new feature that converts and displays event times across multiple time zones (Eastern Time, Mountain Time, and Coordinated Universal Time) for live presentations at the Hotel, providing clarity for users in different regions.

Containerization: The application is containerized using Docker, allowing for easy deployment and scalability. A Dockerfile is included to facilitate the creation of a single image encompassing all modifications made during development.

Project Structure
Backend: Implemented using Java Spring Boot, focusing on RESTful services and multithreading capabilities.
Frontend: Developed with Angular to ensure a seamless user experience and dynamic content presentation.
Docker: Configuration for containerization is provided with a Dockerfile.
Getting Started
To get started with the application, clone the repository and follow the instructions in the supporting documents to set up your development environment in IntelliJ IDEA (Ultimate Edition).

Prerequisites
Java 11 or higher
Node.js and Angular CLI
Docker
Installation
Clone the repository:
bash
```git clone https://github.com/your-username/hotel-app.git
Navigate to the project directory:
bash
cd hotel-app
Install the backend dependencies:
bash
cd backend
./mvnw install
Install the frontend dependencies:
bash
cd ../frontend
npm install
Running the Application
Start the backend:
bash
cd backend
./mvnw spring-boot:run
Start the frontend:
bash
cd frontend
ng serve
Docker Deployment
To build and run the Docker image:

Build the Docker image:
bash
docker build -t hotel-app .
Run the Docker container:
bash
docker run --name hotel-app -p 8080:8080 hotel-app
```

C. Modify the Hotel scheduling application for localization and internationalization by doing the following:
1. Install the Hotel scheduling application in your integrated development environment (IDE). Modify the Java classes of application to display a welcome message by doing the following:
a. Build resource bundles for both English and French (languages required by Canadian law). Include a welcome message in the language resource bundles.

```Create: Resource Bundle 'hotelRB'```
``` Resource Bundle 'hotelRB'

            hotelRB_en_us.properties
                hello=Hello!
                welcome=Welcome to the Hotel!

            hotelRB_fr_ca.properties
                hello=Bonjour!
                welcome=Bienvenue à l'hôtel
```

B. Display the welcome message in both English and French by applying the resource bundles using a different thread for each language.

```Create: HotelController.java```
```
packagesample_code.controller;

import sample_code.locale.DisplayMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Locale;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
public class LandonController {

    @GetMapping("/welcome")

    public ResponseEntity displayMessage (@RequestParam("lang") String lang) {
        Locale locale = Locale.forLanguageTag(lang);
        DisplayMessage displayMessage = new DisplayMessage(locale);
        return new ResponseEntity<String> (DisplayMessage.getDisplayMessage(), HttpStatus.OK);
    }
}

```

```Create: DisplayMessage.java```
```package sample_code.locale;

import java.util.Locale;
import java.util.ResourceBundle;

public class DisplayMessage implements Runnable {

    static Locale locale;


    public DisplayMessage(Locale locale) {
        this.locale = locale;
    }

    public static String getDisplayMessage() {
        ResourceBundle bundle = ResourceBundle.getBundle("welcomeRB",locale);
        return bundle.getString("welcome");
    }

    @Override
    public void run() {
        System.out.println( "Thread verification: " + getDisplayMessage() + ", ThreadID: " + Thread.currentThread().getId() );
    }
}
```

```Modify: SampleCodeApplication.java``` ```LINES 20-27```
```MODIFY:

@SpringBootApplication
public class SampleCodeApplication {

	public static void main(String[] args) {
		SpringApplication.run(SampleCodeApplication.class, args);

		DisplayMessage engMessage = new DisplayMessage(Locale.US);
		DisplayMessage freMessage = new DisplayMessage(Locale.CANADA_FRENCH);

		Thread engThread = new Thread(engMessage);
		Thread freThread = new Thread(freMessage);

		engThread.start();
		freThread.start();



		System.out.println("Testing time conversion: " + conTim.grabZone());
	}
}
```

Modify the front end to display the price for a reservation in currency rates for U.S. dollars ($), Canadian dollars (C$), and euros (€) on different lines.
Note: It is not necessary to convert the prices' values.

```Modify: app.component.ts``` ```Lines 70-71```
      
```
this.rooms.forEach( room => { room.priceCAD = room.price; room.priceEUR = room.price})
```
    
```Modify: app.component.html``` ```Lines 120-122```
```
                  <strong>Price: C${{room.priceCAD}}</strong><br/>
                  <strong>Price: €{{room.priceEUR}}</strong>
```

```Modify: app.component.html``` ```Lines 87-90```

```
< strong > Price: CA${{room.priceCAD}} < /strong > < br > 
< strong > Price: EUR€{{room.priceEUR}} < /strong > < br >
```
 Display the time for an online live presentation held at the Landon Hotel by doing the following: a. Write a Java method to convert times between eastern time (ET), mountain time (MT), and coordinated universal time (UTC) zones.


```Create: ConvertTime.java```
```package sample_code;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.CrossOrigin;
import java.time.*;
import java.time.format.DateTimeFormatter;

@CrossOrigin(origins = "http://localhost:4200")
public class conTim {

    public static String grabZone() {
        ZonedDateTime time = ZonedDateTime.now();
        DateTimeFormatter timForm = DateTimeFormatter.ofPattern("HH:mm:");

        ZonedDateTime east = time.withZoneSameInstant(ZoneId.of("America/New_York"));
        ZonedDateTime mountain = time.withZoneSameInstant(ZoneId.of("America/Denver"));
        ZonedDateTime Universal = time.withZoneSameInstant(ZoneId.of("UTC"));

        String clocks = east.format(timForm) + "ET, " + mountain.format(timForm) + "MT, " + Universal.format(timForm) + "UTC";

        return clocks;
    }
}
```

Use the time zone conversion method from part C3a to display a message stating the time in all three times zones in hours and minutes for an online, live presentation held at the Landon Hotel. The times should be displayed as ET, MT, and UTC. Note: Remember to update your README file after every requirement.


```Create: ConvertTimeController```
```package sample_code;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class conTimCon {

    @GetMapping("/presentation")
    public ResponseEntity<String> announcePresentation() {
        String announcement = "There will be a presentation starting at:\n " + conTim.grabZone();
        return new ResponseEntity<String> (announcement, HttpStatus.OK);
    }
}
```

D. Explain how you would deploy the Spring application with a Java back end and an Angular front end to cloud services and create a Dockerfile using the attached supporting document “How to Create a Docker Account” by doing the following: 1. Build the Dockerfile to create a single image that includes all code, including modifications made in parts C1 to C3. Commit and push the final Dockerfile to GitLab.


```
Create: Dockerfile
```
```
FROM openjdk:17-jdk-slim
ARG JAR_FILE=target/*.jar
COPY ./target/D387_sample_code-0.0.2-SNAPSHOT.jar app.jar
WORKDIR /usr/src/app
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "/app.jar"]
```
