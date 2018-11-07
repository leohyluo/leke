package com.sugar.leke.service.impl;

import com.sugar.leke.enums.AccountStatus;
import com.sugar.leke.framework.exception.ServiceException;
import com.sugar.leke.framework.web.ResponseStatus;
import com.sugar.leke.mapper.OrderTaskMapper;
import com.sugar.leke.mapper.UserAccountMapper;
import com.sugar.leke.pojo.OrderTask;
import com.sugar.leke.pojo.UserAccount;
import com.sugar.leke.pojo.vo.AccountStatusVo;
import com.sugar.leke.service.LekeService;
import com.sugar.leke.service.UserAccountService;
import com.sugar.leke.util.CollectionUtils;
import com.sugar.leke.util.DateUtils;
import com.sugar.leke.util.OrderUtils;
import com.sugar.leke.util.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserAccountServiceImpl implements UserAccountService {

    @Resource
    private UserAccountMapper userAccountMapper;
    @Resource
    private OrderTaskMapper orderTaskMapper;
    @Resource
    private LekeService lekeService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public UserAccount getByUserName(String userName) {
        UserAccount param = new UserAccount();
        param.setUserName(userName);
        List<UserAccount> userAccountList = userAccountMapper.select(param);
        UserAccount userAccount = null;
        if (CollectionUtils.isNotEmpty(userAccountList)) {
            userAccount = userAccountList.get(0);
        }
        return userAccount;
    }

    @Override
    public AccountStatusVo getAccountStatus(String mobile) {
        OrderTask orderParam = new OrderTask();
        orderParam.setMobile(mobile);
        orderParam.setToday(DateUtils.today());
        List<OrderTask> orderTaskList = orderTaskMapper.listUserOrderByStatus(orderParam);
        ResponseStatus responseStatus = null;
        AccountStatusVo resultVo;
        if(orderTaskList.isEmpty()) {
            resultVo = new AccountStatusVo(mobile, ResponseStatus.ORDER_CAN_BE_START);
            return resultVo;
        }
        //60分钟内未完成的订单
        List<OrderTask> unFinishOrderList = orderTaskList.stream().filter(OrderUtils::hasUnFinishOrder).collect(toList());
        if (CollectionUtils.isNotEmpty(unFinishOrderList)) {
            responseStatus = ResponseStatus.ORDER_UNFINISH;
        }
        //最近一次接单情况
        OrderTask orderTask = orderTaskList.get(0);
        Integer status = orderTask.getStatus();

        if (status == AccountStatus.接单中.getCode()) {
            responseStatus = ResponseStatus.ORDER_RECEIPTING;
        } else if (status == AccountStatus.接单成功.getCode()) {
            responseStatus = ResponseStatus.ORDER_RECEIPT_SUCCESS;
        } else if (status == AccountStatus.接单失败.getCode()) {
            responseStatus = ResponseStatus.OREDER_RECEIPT_FAIL;
        } else if (status == AccountStatus.已上限.getCode()) {
            responseStatus = ResponseStatus.ORDER_FULL;
        }
        resultVo = new AccountStatusVo(mobile, responseStatus);
        return resultVo;
    }

    @Override
    public void updateAccoutActive(UserAccount userAccount, Integer isActive) {
        userAccount.setIsActive(isActive);
        userAccountMapper.updateByPrimaryKey(userAccount);
    }

    @Override
    public String login(String userName, String password) throws ServiceException {
        UserAccount userAccount = this.getByUserName(userName);
        if (userAccount == null) {
            throw new ServiceException(ResponseStatus.USER_NOT_FOUND);
        }
        String sessionId = lekeService.getSessionId();
        logger.info("sessionId is {}", sessionId);
        String result = lekeService.login(userName, password, sessionId);
        if (result.contains("成功")) {
            String userPwd = StringUtils.isNotEmpty(userAccount.getPassword()) ? userAccount.getPassword() : "";
            if (!userPwd.equals(password)) {
                userAccount.setPassword(password);
            }
            userAccount.setSessionId(sessionId);
            userAccount.setStatus(AccountStatus.已登录.getCode());
            userAccountMapper.updateByPrimaryKey(userAccount);
        } else if (result.contains("用户名密码有误")) {
            userAccount.setStatus(AccountStatus.登录失败.getCode());
            userAccountMapper.updateByPrimaryKey(userAccount);
            throw new ServiceException(ResponseStatus.INVALID_USERNAME_PASSWORD);
        } else {
            userAccount.setStatus(AccountStatus.登录失败.getCode());
            userAccountMapper.updateByPrimaryKey(userAccount);
            throw new ServiceException(ResponseStatus.USER_LOGIN_FAILED);
        }
        return sessionId;
    }
}
