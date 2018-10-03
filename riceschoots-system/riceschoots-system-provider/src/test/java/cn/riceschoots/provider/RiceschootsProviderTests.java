package cn.riceschoots.provider;

import cn.riceschoots.api.user.model.User;
import cn.riceschoots.api.wallet.model.UserWallet;
import cn.riceschoots.api.wallet.service.IUserWalletSerivce;
import com.alibaba.fastjson.JSON;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiceschootsProviderTests {
    Logger log = LoggerFactory.getLogger(this.getClass());

    @Resource
    IUserWalletSerivce userWalletSerivce;

	@Test
	public void contextLoads() {
        User user = userWalletSerivce.getUserInfoById(1L);
        log.info("=========contextLoads JSON1==========="+ JSON.toJSONString(user));

        UserWallet userWallet = userWalletSerivce.getUserWallet(1L);
        log.info("=========contextLoads JSON2==========="+JSON.toJSONString(userWallet));
	}



}
