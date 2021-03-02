package ru.netology.data;

import com.github.javafaker.Faker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class DataGenerator {
    public DataGenerator() {}

    public static class Registration {
        private Registration() {}

        public static DataForRegistration generateByCard(String local) {
            Faker faker = new Faker(new Locale("ru"));
            return new DataForRegistration(
                    faker.address().cityName(),
                    LocalDate.now().plusDays(3).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    LocalDate.now().plusDays(5).format(DateTimeFormatter.ofPattern("dd.MM.yyyy")),
                    faker.name().fullName(),
                    faker.phoneNumber().phoneNumber()
            );
        }
    }

}





