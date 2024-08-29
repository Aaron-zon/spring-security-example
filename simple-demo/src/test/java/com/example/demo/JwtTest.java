package com.example.demo;

import com.example.demo.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtTest {

    // 测试创建 JWT
    @Test
    public void createJWTTest() {
        String jwt = JwtUtil.createJWT("subject", "issue", "这是要传递的数据", 36000);
        System.out.println(jwt);
    }

    // 测试解析 JWT
    @Test
    public void pareseJWTTest() {
        String jwt = "eyJhbGciOiJIUzI1NiIsInppcCI6IkRFRiJ9.eNpUkEFvwjAMhf-L1SOa0oIE7a1ssK2aduqAq2ktlpCGqGlGEOK_z13LtEk55NN7tp99Be-ohewKsoYsnvzgOzYEGSDCBIysjiPnPVt07nxq2QxRglEsovnzTtHx7UGkO7exy_IcnooZvarHVAUV8qk-LDaVKqqifdHbbSzN6iNPuJPrsPOO-8QM1KDUvzM-T4aMb_Z9MoiTKb--gAKj4B9-YYesGa_1ELm8WBrFqiXsaHm5ywOXst9hLLD1P8fAfx016bXGA2TixnP9fkwpnbvfhYLle82TWSqEWIjbNwAAAP__.SZiN2LYbnDMtuhKxBdoUU8p3OW4juLaHRx2movzV5ok";
        Jws<Claims> jws =  JwtUtil.pareseJWT(jwt);
        System.out.println(jws);
    }
}
