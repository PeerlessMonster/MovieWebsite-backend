package cn.edu.scnu.DTO;

public class VerifyUserRequest {
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
