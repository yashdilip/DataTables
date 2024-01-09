package com.example.datatables;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;

    @GetMapping("/employees")
    public DataTablesOutput<Employee> list(@Valid DataTablesInput input, @RequestParam Map<String, String> queryParameters) {
        input.parseSearchPanesFromQueryParams(queryParameters, Arrays.asList("position", "gender"));

        return repository.findAll(input);
    }
}