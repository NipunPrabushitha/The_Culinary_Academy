package org.example.model;

public class CoordinatorDTO {
    private String userId;
    private String userName;
    private String password;
    private String forgetPassword;

    public CoordinatorDTO(String userId, String userName, String password, String forgetPassword) {
        this.userId = userId;
        this.userName = userName;
        this.password = password;
        this.forgetPassword = forgetPassword;
    }

    public CoordinatorDTO() {
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
