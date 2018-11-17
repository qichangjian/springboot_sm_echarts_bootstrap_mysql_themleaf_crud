package com.baizhan;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** 启动类
 *
 *           遇到的问题：
 *  1.idea编译项目的时候没有将 xml 文件编译输出到classes目录下面。所以自然是扫描不到绑定的定义的方法。
 * 解决方案
 *          在pom.xml文件中添加如下代码
 *
 * 2.连接数据库报错ssl？ 这个可以不用设置了但是会报 WARN: Establishing SSL connection without
 * application文件中设置：
 * spring.datasource.url=jdbc:mysql://localhost:3306/ssm?&useSSL=false
 *
 * 3.service 层中的 usermapp上加入 @autowired 后报错？
 * 修改：
 *      @Autowired(required=false)
 *      private UsersMapper usersMapper;
 *
*/

@SpringBootApplication
@MapperScan("com.baizhan.mapper") //用于扫描mybatis的mapper接口,根据扫描的结构生成代理对象。告诉springboot整合mybaties的时候，在扫描的时候去哪里扫描
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class,args);
    }
}
