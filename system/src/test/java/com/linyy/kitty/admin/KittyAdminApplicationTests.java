package com.linyy.kitty.admin;

import com.linyy.kitty.admin.entity.SysUser;
import com.linyy.kitty.admin.redis.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

@SpringBootTest
class KittyAdminApplicationTests {

    @Autowired
    RedisTemplate redisTemplate;

    @Autowired
    RedisUtil redisUtil;

    @Test
    void contextLoads() {
        SysUser user = new SysUser();
        user.setName("linyy");
        user.setPassword("123456");
        redisUtil.set("linyy",user);
    }

}
