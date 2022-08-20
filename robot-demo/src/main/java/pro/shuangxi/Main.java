package pro.shuangxi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author ：mengshx
 * @date ：Created in 2022/8/6 17:01
 * @description：测试
 */
@SpringBootApplication(exclude = DataSourceAutoConfiguration.class)
public class Main {
    public static void main(String[] args) {
        SpringApplication.run(Main.class);
    }

}
