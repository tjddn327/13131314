package WeatherPick.weatherpick.domain.user.dto;

import WeatherPick.weatherpick.domain.user.entity.UserRoleType;


import java.util.Date;



public class UserResponseDto {
    private String username;
    private String nickname;
    private String email;
    private String role;
    private String name;
    private String phonenumber;
    private Date createdate;


    public void setCreatedate(Date createdate) {
        this.createdate = createdate;
    }


    public void setName(String name) {
        this.name = name;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }


    public void setUsername(String username) {
        this.username = username;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }
}