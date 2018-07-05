package com.wojiushiwo;

import com.wojiushiwo.dal.config.DataSourceConfig;
import com.wojiushiwo.dal.config.MyBatisConfig;
import com.wojiushiwo.dal.dao.User;
import com.wojiushiwo.dal.dao.UserMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Unit test for simple App.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {MyBatisConfig.class, DataSourceConfig.class})
public class AppTest {
    @Autowired
    private UserMapper userMapper;
    @Test
    public void findAll() {
//        UserExample userExample = new UserExample();
//        userExample.createCriteria().andNameEqualTo("wanyu");
//        List<User> users = userMapper.findAll();
//        for (User user : users) {
//            System.out.println(user.getName() + ";" + user.getAge());
//        }
        byte b=1;
        User users = userMapper.selectByPrimaryKey(b);
//        userMapper.sayHello();
    }

    @Test
    public void insert() {
       User user=new User();
       user.setName("love");
       Byte age=25;
       user.setAge(age);
        userMapper.insert(user);
    }
}
