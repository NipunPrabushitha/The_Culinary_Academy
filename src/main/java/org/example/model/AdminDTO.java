package org.example.model;

public class AdminDTO {
    private String userId;
    private String userName;
    private String password;
    private String forgetPassword;

    public AdminDTO(String userId, String userName, String password, String forgetPassword) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.forgetPassword = forgetPassword;
    }

    public AdminDTO(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }


    public AdminDTO(){

    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getForgetPassword() {
        return forgetPassword;
    }

    public void setForgetPassword(String forgetPassword) {
        this.forgetPassword = forgetPassword;
    }
}
