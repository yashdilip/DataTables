package com.example.datatables;

import org.springframework.data.jpa.datatables.repository.DataTablesRepository;

public interface EmployeeRepository extends DataTablesRepository<Employee, Integer> {}