package cn.riceschoots.provider.user.model;

import cn.riceschoots.api.user.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * @Description
 * @Author
 * @Date
 */
@Mapper
public interface UserMapper {
    /**
     * 根据用户ID获取用户信息
     * @param userId
     * @return
     */
    User getUserInfoById(@Param("userId") Long userId);

    /**
     * 根据用户ID更新用户名称
     * @param userId
     * @param name
     */
    void updateUserName(@Param("userId") Long userId, @Param("name") String name);

}
