package cn.riceschoots.api.wallet.service;

import cn.riceschoots.api.user.model.User;

/**
 * @Description
 * @Author
 * @Date
 */
public interface IUserWalletSerivce {

    User getUserInfoById(Long userId);

    void getUserWallet(Long userId);
}
