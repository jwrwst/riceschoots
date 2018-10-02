package cn.riceschoots.provider;

import cn.riceschoots.api.wallet.service.IUserWalletSerivce;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RiceschootsProviderTests {

    @Resource
    IUserWalletSerivce userWalletSerivce;

	@Test
	public void contextLoads() {
	    userWalletSerivce.getUserInfoById(1L);

	    userWalletSerivce.getUserWallet(1L);
	}



}
