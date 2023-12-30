package cn.edu.scnu.model;

public class LoginRequest {
    private String username;
    private String password;
    private boolean remember;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRemember() {
        return remember;
    }
}
