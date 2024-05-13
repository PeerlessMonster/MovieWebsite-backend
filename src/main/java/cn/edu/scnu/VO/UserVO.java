package cn.edu.scnu.VO;

import cn.edu.scnu.DTO.UserResponse;

public class UserVO {
    private int id;
    private String email;
    private String name;
    private int vip;
    private long sessionExpire;

    public UserVO(UserResponse user, long sessionExpire) {
        this.id = user.getId();
        this.email = user.getEmail();
        this.name = user.getName();
        this.vip = user.getVip();
        this.sessionExpire = sessionExpire;
    }

    public int getId() {
        return id;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public int getVip() {
        return vip;
    }

    public long getSessionExpire() {
        return sessionExpire;
    }
}
