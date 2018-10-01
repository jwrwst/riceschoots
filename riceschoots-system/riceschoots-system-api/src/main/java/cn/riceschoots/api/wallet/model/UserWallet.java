package cn.riceschoots.api.wallet.model;

import java.io.Serializable;

/**
 * @Description
 * @Author
 * @Date
 */
public class UserWallet implements Serializable{
    private static final long serialVersionUID = 1L;
    /**
     * 用户ID
     */
    private Long userId;
    /**
     * 可用资金
     */
    private double useMoney;


}
