package com.plf.yunmusicserver.common.mq.receive;

import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicentity.enums.ResponseStatusEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;

import java.nio.charset.Charset;

/**
 * @author mr_peng
 * @since 2021/08/13
 * <p>
 *     抽象接口用于接受mq消息
 * </p>
 */
@Slf4j
public abstract class AbstractReceiveService {

    protected abstract String getQueueName();

    protected abstract void doReceive(Message message);

    protected abstract ResponseResult doHandle(String message);

    protected ResponseResult handleMes(Message message , Charset charset){
        String receiveMessage = new String(message.getBody() , charset);
        String queueName = this.getQueueName();
        log.debug("the queue {} receive Message is {}",queueName , receiveMessage);
        try {
            ResponseResult responseResult = doHandle(receiveMessage);
            return responseResult;
        }catch (Exception e){
            log.error("receive queue {} message {} error {}" , queueName, receiveMessage , e);
        }
        return ResponseResult.error(ResponseStatusEnum.MQ_RECEIVE_ERROR.getCode(),ResponseStatusEnum.MQ_RECEIVE_ERROR.getMessage());
    }
}
