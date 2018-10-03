package cn.riceschoots.provider.wallet.impl;

import cn.riceschoots.api.user.model.User;
import cn.riceschoots.api.wallet.model.UserWallet;
import cn.riceschoots.api.wallet.service.IUserWalletSerivce;
import cn.riceschoots.provider.SystemProviderApplication;
import cn.riceschoots.provider.framework.datasource.DynamicDataSourceGlobal;
import cn.riceschoots.provider.framework.datasource.TargetDataSource;
import cn.riceschoots.provider.user.model.UserMapper;
import cn.riceschoots.provider.wallet.mapper.UserWalletMapper;
import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @Description
 * @Author
 * @Date
 */
@Service
public class UserWalletSerivceImpl implements IUserWalletSerivce {
    Logger log = LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserWalletMapper userWalletMapper;

//    public interface cons{
//        String name = "write";
//    }
    @Override
    @TargetDataSource(name = "write")
    public User getUserInfoById(Long userId) {
        return userMapper.getUserInfoById(userId);
    }

    @Override
    @TargetDataSource(name = "read")
    public UserWallet getUserWallet(Long userId) {
        return userWalletMapper.getUserWalletById(userId);
    }
}
