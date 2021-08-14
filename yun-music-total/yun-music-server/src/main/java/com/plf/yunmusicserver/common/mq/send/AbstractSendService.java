package com.plf.yunmusicserver.common.mq.send;

import com.plf.yunmusicentity.commonhttp.ResponseResult;

/**
 * @author mr_peng
 * @since 2021/08/13
 * <p>
 *     抽象接口用于推送mq消息
 * </p>
 */
public abstract class AbstractSendService {

    protected abstract String getQueueName();

    public abstract ResponseResult doSend(String context);
}
