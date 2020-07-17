package dev.hulk.leave.form;

public class AddEmployeeForm {
    private String inputName;
    private String inputEmail;
    private int[] selectManagers;

    public String getInputName() {
        return inputName;
    }

    public void setInputName(String inputName) {
        this.inputName = inputName;
    }

    public String getInputEmail() {
        return inputEmail;
    }

    public void setInputEmail(String inputEmail) {
        this.inputEmail = inputEmail;
    }

    public int[] getSelectManagers() {
        return selectManagers;
    }

    public void setSelectManagers(int[] selectManagers) {
        this.selectManagers = selectManagers;
    }
}
