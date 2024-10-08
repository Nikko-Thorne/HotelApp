package edu.wgu.d387_sample_code;

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
