package com.akrams.employee_managment.repository;

import com.akrams.employee_managment.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, String> {

    @Query(value = "SELECT * FROM Employee e WHERE 1=1 :?1",nativeQuery = true)
    List<Employee> findEmployeeByCriteria(String query);

    List<Employee> findAllByFirstNameContainsAndDateOfBirthGreaterThanEqualAndDateOfBirthLessThanEqualAndAgeGreaterThanEqualAndAgeLessThanEqual(String name, LocalDate queryStartDate, LocalDate queryEndDate, Integer ageStart, Integer ageEnd);
}
