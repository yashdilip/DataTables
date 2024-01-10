package com.example.datatables;

import jakarta.persistence.EntityManager;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Expression;
import jakarta.persistence.criteria.Root;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.datatables.mapping.DataTablesInput;
import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;
import org.springframework.data.jpa.datatables.mapping.SearchPanes;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
public class EmployeeController {
    private final EmployeeRepository repository;
    private final EntityManager entityManager;

    @GetMapping("/employees")
    public DataTablesOutput<Employee> list(@Valid DataTablesInput input, @RequestParam Map<String, String> queryParameters) {
        input.parseSearchPanesFromQueryParams(queryParameters, Arrays.asList("position", "gender"));

        DataTablesOutput<Employee> output = repository.findAll(input);

        output.getSearchPanes().getOptions().put("city", computeCityFilters());

        return output;
    }

    private List<SearchPanes.Item> computeCityFilters() {
        CriteriaBuilder criteriaBuilder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Object[]> query = criteriaBuilder.createQuery(Object[].class);

        Root<Employee> root = query.from(Employee.class);
        Expression<String> city = root.get("address").get("city").as(String.class);

        query.multiselect(city, criteriaBuilder.count(root));
        query.groupBy(city);

        List<SearchPanes.Item> items = new ArrayList<>();

        this.entityManager.createQuery(query).getResultList().forEach(objects -> {
            String value = String.valueOf(objects[0]);
            long count = (long) objects[1];
            items.add(new SearchPanes.Item(value, value, count, count));
        });

        return items;
    }
}