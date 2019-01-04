package com.bis.springboot;

import com.bis.dao.UserInfoMapper;
import com.bis.entity.UserInfo;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 *
 */
//开启整个工程基于springboot的自动化的配置注解
@SpringBootApplication(scanBasePackages = {"com.bis"})
@RestController
@MapperScan("com.bis.dao")
public class App
{
    private static Log log = LogFactory.getLog(App.class);
    @Autowired
    private UserInfoMapper userInfoMapper;

    @RequestMapping("/getuser")
    public String home(){
        UserInfo userInfo = userInfoMapper.selectByPrimaryKey(1);
        if(userInfo == null){
//            System.out.println();
            log.info("用户不存在！");
            return "用户不存在";
        }else{
            return userInfo.getName();
        }
    }

    public static void main( String[] args )
    {
//        System.out.println( "Hello World!" );
        SpringApplication.run(App.class,args);//启动了一个web容器，内嵌的tomcat服务
    }
}
