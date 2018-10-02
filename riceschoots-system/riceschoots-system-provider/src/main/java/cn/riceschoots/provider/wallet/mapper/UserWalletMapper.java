package cn.riceschoots.provider.wallet.mapper;
import cn.riceschoots.api.wallet.model.UserWallet;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author
 * @Date
 */
@Mapper
public interface UserWalletMapper {
    /**
     * 根据用户ID获取用户信息
     * @param userId
     * @return
     */
    UserWallet getUserWalletById(@Param("userId") Long userId);

    /**
     * 根据用户ID更新用户名称
     * @param userId
     * @param money
     */
    void updateUserWallet(@Param("userId") Long userId, @Param("money") String money);




}
