package dev.hulk.leave.form;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
public class AddLeaveRequestForm {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDateTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDateTime;
    private String reasonTextArea;
}
