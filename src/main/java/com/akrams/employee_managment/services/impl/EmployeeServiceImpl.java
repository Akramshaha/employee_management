package com.akrams.employee_managment.services.impl;

import com.akrams.employee_managment.dto.EmployeeDTO;
import com.akrams.employee_managment.dto.EmployeeFormDTO;
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
import org.springframework.util.StringUtils;

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
        employeeDTOList.stream().forEach((e)-> System.out.println(e.getId()));
        return employeeDTOList;
    }

    @Override
    public EmployeeDTO addNewEmployee(EmployeeDTO employeeDTO) {
        log.info("employeeDTO :: {}",employeeDTO);
        Employee employee;
        if (employeeDTO.getId() !=null){
            employee = setEmployeeUpdateValidation(employeeDTO);
        }else {
            employee = modelMapper.map(employeeDTO, Employee.class);
        }
/*

        int sequenceValue = getNextIntValueFromEmpTable();
        String nextId=String.format("%s%08d", "P", sequenceValue++);
        employee.setId(nextId);
        log.info("employee ::  {}",employee);*/
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

    @Override
    public List<Department> getAllDept() {
        return departmentRepository.findAll();
    }

    @Override
    public EmployeeFormDTO getAllEmployeeFormData() {
        EmployeeFormDTO employeeFormDTO = new EmployeeFormDTO();
        employeeFormDTO.setDepartmentList(departmentRepository.findAll());
        return employeeFormDTO;
    }

    @Override
    public EmployeeDTO getEditEmployee(String id) {
        return modelMapper.map(employeeRepository.findById(id).get(), EmployeeDTO.class);
    }

    public Employee setEmployeeUpdateValidation(EmployeeDTO employeeDTO){
        Employee employee = employeeRepository.findById(employeeDTO.getId()).get();

        if(!StringUtils.hasLength(employeeDTO.getAddress()) && !employee.getAddress().equalsIgnoreCase(employeeDTO.getAddress())){
            employee.setAddress(employeeDTO.getAddress());
        }
        if(employeeDTO.getDesignation().name() != null
                && !employee.getDesignation().name().equalsIgnoreCase(employeeDTO.getDesignation().name())){
            employee.setDesignation(employeeDTO.getDesignation());
        }
        if(employeeDTO.getMaritalStatus().name() != null
                && !employee.getMaritalStatus().name().equalsIgnoreCase(employeeDTO.getMaritalStatus().name())){
            employee.setMaritalStatus(employeeDTO.getMaritalStatus());
        }
        if(employeeDTO.getDepartmentId() != employee.getDepartment().getId() ){
            Department dept = departmentRepository.findById(employeeDTO.getDepartmentId()).get();
            employee.setDepartment(dept);
        }
        if(employee.getGender().name().equalsIgnoreCase(Constants.female)){
            if(!StringUtils.hasLength(employeeDTO.getFirstName())){
                employee.setFirstName(employeeDTO.getFirstName());
            }
            if(!StringUtils.hasLength(employeeDTO.getLastName())){
                employee.setLastName(employeeDTO.getLastName());
            }
        }
        return employee;
    }

    /*public int getNextIntValueFromEmpTable(){
        Employee emp = employeeRepository.findOneOrderByIdDesc().get();
        System.out.println(emp.getId());
        String str = emp.getId();

        int INITIAL_VALUE = Integer.parseInt(str.substring(1,str.length()));
        System.out.println(INITIAL_VALUE);
        return INITIAL_VALUE;
    }*/

}
