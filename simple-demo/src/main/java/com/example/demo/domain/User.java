package com.example.demo.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

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
}
