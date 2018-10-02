package cn.riceschoots.provider.wallet.impl;

import cn.riceschoots.api.user.model.User;
import cn.riceschoots.api.wallet.model.UserWallet;
import cn.riceschoots.api.wallet.service.IUserWalletSerivce;
import cn.riceschoots.provider.framework.datasource.DynamicDataSourceGlobal;
import cn.riceschoots.provider.framework.datasource.TargetDataSource;
import cn.riceschoots.provider.user.model.UserMapper;
import cn.riceschoots.provider.wallet.mapper.UserWalletMapper;
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
    @Resource
    private UserMapper userMapper;

    @Resource
    private UserWalletMapper userWalletMapper;


    @Override
    @TargetDataSource(name = "write")
    public User getUserInfoById(Long userId) {
        User userinfo = userMapper.getUserInfoById(userId);
        System.out.println("================"+userinfo.toString());
        return null;
    }

    @Override
    @TargetDataSource(name = "read")
    public void getUserWallet(Long userId) {
        UserWallet userWallet = userWalletMapper.getUserWalletById(userId);
        System.out.println("===================="+userWallet.toString());
    }
}
