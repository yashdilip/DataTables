package com.example.datatables;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataInitializer {
    final EmployeeRepository repository;

    @PostConstruct
    void init() {
        int NUM_TO_GENERATE = 5_000;
        log.info("generating {} random employees", NUM_TO_GENERATE);

        Faker faker = new Faker();
        Random randomGenerator = new Random();

        for (int i = 0; i< NUM_TO_GENERATE; i++) {
            Employee employee = Employee.builder()
                    .id(i)
                    .firstName(faker.name().firstName())
                    .lastName(faker.name().lastName())
                    .position(faker.job().position())
                    .gender(faker.gender().types())
                    .salary(randomGenerator.nextInt(20000) * 50)
                    .build();
            repository.save(employee);
        }
    }
}
