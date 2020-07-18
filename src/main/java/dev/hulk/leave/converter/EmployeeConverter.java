package dev.hulk.leave.converter;

import dev.hulk.leave.dto.EmployeeDTO;
import org.springframework.stereotype.Component;

@Component
public class EmployeeConverter {
    public EmployeeDTO toDTO(dev.hulk.leave.entity.Employee employee){
        EmployeeDTO dto = new EmployeeDTO();

        dto.setName(employee.getName());
        dto.setEmail(employee.getEmail());
        dto.setOnWork(employee.isOnWork());
        dto.setPassword(employee.getPassword());
        dto.setManagers(employee.getManagers());
        dto.setEmployees(employee.getEmployees());

        if(employee.getId() != 0){
            dto.setId(employee.getId());
        }

        return dto;
    }

    public dev.hulk.leave.entity.Employee toEntity(EmployeeDTO dto){
        dev.hulk.leave.entity.Employee entity = new dev.hulk.leave.entity.Employee();

        entity.setEmail(dto.getEmail());
        entity.setName(dto.getName());
        entity.setPassword(dto.getPassword());
        entity.setOnWork(dto.isOnWork());

        return entity;
    }
}
