package dev.hulk.leave.form;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class RequestForm {
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date startDateTime;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date endDateTime;
    private String reasonTextArea;
    
    private int[] selectApprovers;

    public Date getStartDateTime() {
        return startDateTime;
    }

    public void setStartDateTime(Date startDateTime) {
        this.startDateTime = startDateTime;
    }

    public Date getEndDateTime() {
        return endDateTime;
    }

    public void setEndDateTime(Date endDateTime) {
        this.endDateTime = endDateTime;
    }

    public String getReasonTextArea() {
        return reasonTextArea;
    }

    public void setReasonTextArea(String reasonTextArea) {
        this.reasonTextArea = reasonTextArea;
    }

    public int[] getSelectApprovers() {
        return selectApprovers;
    }

    public void setSelectApprovers(int[] selectApprovers) {
        this.selectApprovers = selectApprovers;
    }
}
