package boot.mysql;

import com.flynn.boot.starter.MyService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * @author ronghl
 * @date 2023/04/24
 */
@SpringBootApplication
public class BootMysqlApplication {

    public static void main(String[] args) {

        SpringApplication.run(BootMysqlApplication.class, args);
    }

    @Resource
    private MyService myService;



}
