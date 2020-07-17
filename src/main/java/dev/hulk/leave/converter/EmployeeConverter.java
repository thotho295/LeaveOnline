package dev.hulk.leave.converter;

import dev.hulk.leave.dto.EmployeeDTO;
import dev.hulk.leave.entity.Employee;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {
    public EmployeeDTO toDTO(Employee employee){
        EmployeeDTO dto = new EmployeeDTO();

        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setOnWork(employee.isOnWork());
        dto.setPassword(employee.getPassword());

        if(employee.getId() != 0){
            dto.setId(employee.getId());
        }

        return dto;
    }

    public Employee toEntity(EmployeeDTO dto){
        Employee entity = new Employee();

        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setOnWork(dto.isOnWork());

        return entity;
    }
}
