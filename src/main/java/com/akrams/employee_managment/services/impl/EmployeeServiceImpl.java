package com.akrams.employee_managment.services.impl;

import com.akrams.employee_managment.dto.EmployeeDTO;
import com.akrams.employee_managment.model.Department;
import com.akrams.employee_managment.model.Employee;
import com.akrams.employee_managment.repository.DepartmentRepository;
import com.akrams.employee_managment.repository.EmployeeRepository;
import com.akrams.employee_managment.services.EmployeeService;
import com.akrams.employee_managment.utils.Constants;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private DepartmentRepository departmentRepository;

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<EmployeeDTO> employeeDTOList = employeeRepository.findAll().stream().map(
                (e) -> modelMapper.map(e, EmployeeDTO.class)
        ).collect(Collectors.toList());

        return employeeDTOList;
    }

    @Override
    public EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO) {
        Employee employee;
        if (employeeDTO.getId() !=null){
            employee = setEmployeeUpdateValidation(employeeDTO);
        }else {
            employee = modelMapper.map(employeeDTO, Employee.class);
        }
        return modelMapper.map(employeeRepository.save(employee), EmployeeDTO.class);
    }

    @Override
    public List<EmployeeDTO> getEmployeesByCriteria(String name, Integer year, Integer ageStartLimit, Integer ageEndLimit) {

        LocalDate queryStartDate = LocalDate.of(1000, 1, 1);
        LocalDate queryEndDate = LocalDate.of(4000, 12, 31);
        if( year != null) {
            queryStartDate = LocalDate.of(year, 1, 1);
            queryEndDate = LocalDate.of(year, 12, 31);
        }

        if (name == null) {
            name = "";
        }

        List<Employee> employeeList = employeeRepository.findAllByFirstNameContainsAndDateOfBirthGreaterThanEqualAndDateOfBirthLessThanEqualAndAgeGreaterThanEqualAndAgeLessThanEqual(name, queryStartDate, queryEndDate, ageStartLimit, ageEndLimit);


//        log.info("In getEmployee");
//        /*List<EmployeeDTO> employeeDTOList = employeeRepository.findAll().stream().map(
//                (e) -> modelMapper.map(e, EmployeeDTO.class)
//        ).collect(Collectors.toList());*/
//
//        StringBuffer sb = new StringBuffer("");
//        //sb.append("SELECT * FROM Employee e WHERE 1=1");
//
//        //Check EmployeeId Filter
//        if (StringUtils.hasLength(empId)) {
//            sb.append(" AND e.id=" + empId);
//        }
//
//        //Name Filter
//        if (StringUtils.hasLength(name)) {
//            sb.append(" AND e.firstName LIKE '%" + name + "%'");
//            sb.append(" AND e.lastName LIKE '%" + name + "%'");
//        }
//
//        //Age Filter
//        /*if(employeeSearchDTO.getFromRange() != null){
//            sb.append(" AND e.age >="+employeeSearchDTO.getFromRange());
//        }
//        if(employeeSearchDTO.getToRange() != null){
//            sb.append(" AND e.age <="+employeeSearchDTO.getToRange());
//        }*/
//
//        //Birth Year Filter
//        if (StringUtils.hasLength(year)) {
//            sb.append(" AND YEAR(e.dob) =" + year);
//        }
//        System.out.println(sb.toString());
//        List<EmployeeDTO> employeeDTOList = employeeRepository.findEmployeeByCriteria(sb.toString()).stream().map(
//                (e) -> modelMapper.map(e, EmployeeDTO.class)
//        ).collect(Collectors.toList());

        return employeeList.stream()
                .map((e) -> modelMapper.map(e, EmployeeDTO.class))
                .collect(Collectors.toList());
    }

    public Employee setEmployeeUpdateValidation(EmployeeDTO employeeDTO){
        Employee employee = employeeRepository.findById(employeeDTO.getId()).get();

        if(!employeeDTO.getAddress().isEmpty() && !employee.getAddress().equalsIgnoreCase(employeeDTO.getAddress())){
            employee.setAddress(employeeDTO.getAddress());
        }
        if(!employeeDTO.getDesignation().name().isEmpty()
                && !employee.getDesignation().name().equalsIgnoreCase(employeeDTO.getDesignation().name())){
            employee.setDesignation(employeeDTO.getDesignation());
        }
        if(!employeeDTO.getMaritalStatus().name().isEmpty()
                && !employee.getMaritalStatus().name().equalsIgnoreCase(employeeDTO.getMaritalStatus().name())){
            employee.setMaritalStatus(employeeDTO.getMaritalStatus());
        }
        if(employeeDTO.getDepartmentId() != employee.getDepartment().getId() ){
            Department dept = departmentRepository.findById(employeeDTO.getDepartmentId()).get();
            employee.setDepartment(dept);
        }
        if(employee.getGender().name().equalsIgnoreCase(Constants.female)){
            if(!employeeDTO.getFirstName().isEmpty()){
                employee.setFirstName(employeeDTO.getFirstName());
            }
            if(!employeeDTO.getLastName().isEmpty()){
                employee.setLastName(employeeDTO.getLastName());
            }
        }
        return employee;
    }

}
