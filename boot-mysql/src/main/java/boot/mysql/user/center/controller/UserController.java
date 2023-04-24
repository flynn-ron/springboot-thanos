package boot.mysql.user.center.controller;

import boot.mysql.user.center.domain.UcUser;
import boot.mysql.user.center.service.UcUserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author ronghl
 * @date 2023/04/24
 */
@Slf4j
@RestController
public class UserController {

    @Resource
    private UcUserService ucUserService;

    @GetMapping("boot-mysql/user/center/getUser")
    public UcUser getUser(Integer id) {
        UcUser ucUser = ucUserService.getById(id);
        log.info("用户信息={}", ucUser);
        return ucUser;
    }

}
