package boot.mysql.user.center.service.impl;

import boot.mysql.user.center.domain.UcUser;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import boot.mysql.user.center.service.UcUserService;
import boot.mysql.user.center.mapper.UcUserMapper;
import com.mysql.cj.jdbc.Driver;
import org.springframework.stereotype.Service;

/**
 *
 */
@Service
public class UcUserServiceImpl extends ServiceImpl<UcUserMapper, UcUser>
    implements UcUserService{

}




