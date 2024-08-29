package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Data
@TableName(value="sys_user")
public class User implements Serializable  {
    private Long id;
    private String userName;
    private String nickName;
    private String password;
    private String status;
    private String email;
    private String phonenumber;
    private String sex;
    private String avatar;
    private String userType;
    private Long createBy;
    private LocalDateTime createTime;
    private Long updateBy;
    private LocalDateTime updateTime;
    private Integer delFlag;

    public User() {}

    public User(Map<String, Object> userMap) {
        this.id = Long.parseLong(userMap.get("id").toString());
        this.userName = (String) userMap.get("userName");
        this.nickName = (String) userMap.get("nickName");
        this.password = (String) userMap.get("password");
        this.status = (String) userMap.get("status");
        this.email = (String) userMap.get("Aa");
        this.phonenumber = (String) userMap.get("phonenumber");
        this.sex = (String) userMap.get("sex");
        this.userType = (String) userMap.get("userType");
    }
}
