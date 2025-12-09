package edu.wgu.d387_sample_code;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@SpringBootApplication
public class D387SampleCodeApplication {

    public static void main(String[] args) {
        SpringApplication.run(D387SampleCodeApplication.class, args);
    }

    // B3b: print the online presentation time in ET, MT, and UTC
    @Bean
    public CommandLineRunner timeZoneMessageRunner() {
        return args -> {
            ZoneId ET = ZoneId.of("America/New_York");
            ZoneId MT = ZoneId.of("America/Denver");
            ZoneId UTC = ZoneId.of("UTC");

            // You can choose any date/time you want; rubric cares about correct conversion + display
            LocalDate date = LocalDate.now();
            LocalTime presentationET = LocalTime.of(13, 0); // 1:00 PM ET

            ZonedDateTime etTime = convertTime(date, presentationET, ET, ET);
            ZonedDateTime mtTime = convertTime(date, presentationET, ET, MT);
            ZonedDateTime utcTime = convertTime(date, presentationET, ET, UTC);

            DateTimeFormatter fmt = DateTimeFormatter.ofPattern("HH:mm");

            System.out.println("Online live presentation time:");
            System.out.println("ET:  " + etTime.format(fmt));
            System.out.println("MT:  " + mtTime.format(fmt));
            System.out.println("UTC: " + utcTime.format(fmt));
        };
    }

    // B3a: method to convert between time zones (DST-safe)
    private static ZonedDateTime convertTime(LocalDate date, LocalTime time, ZoneId fromZone, ZoneId toZone) {
        ZonedDateTime from = ZonedDateTime.of(date, time, fromZone);
        return from.withZoneSameInstant(toZone);
    }
}

