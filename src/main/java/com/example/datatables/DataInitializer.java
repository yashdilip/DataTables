package com.example.datatables;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.datafaker.Faker;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
@Slf4j
@RequiredArgsConstructor
public class DataInitializer {
    final EmployeeRepository employeeRepository;

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
                    .address(addresses().get(randomGenerator.nextInt(addresses().size())))
                    .build();
            employeeRepository.save(employee);
        }
    }

    static List<Address> addresses() {
        Faker faker = new Faker();

        return IntStream.range(0, 7)
                .mapToObj(i -> Address.builder()
                        .city(faker.address().cityName())
                        .state(faker.address().state())
                        .zip(faker.address().zipCode())
                        .street(faker.address().streetName())
                        .country(faker.address().country())
                        .build()
                ).collect(Collectors.toList());
    }
}
