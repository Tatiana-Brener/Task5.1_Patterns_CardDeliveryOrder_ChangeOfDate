package ru.netology.data;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;

@Data
@RequiredArgsConstructor

public class DataForRegistration {
    private final String city;
    private final String dataOfMeeting;
    private final String anotherDataOfMeeting;
    private final String name;
    private final String phone;


}
