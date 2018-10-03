package cn.riceschoots.api.wallet.service;

import cn.riceschoots.api.user.model.User;
import cn.riceschoots.api.wallet.model.UserWallet;

/**
 * @Description
 * @Author
 * @Date
 */
public interface IUserWalletSerivce {

    User getUserInfoById(Long userId);

    UserWallet getUserWallet(Long userId);
}
