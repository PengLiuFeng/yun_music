package com.plf.yunmusicserver.common.mq.receive;

import com.plf.yunmusicentity.commonhttp.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;

/**
 * @author mr_peng
 * @since 2021/08/13
 * <p>
 *     进行user token的mq推送
 * </p>
 */
@Component
@Slf4j
public class ReceiveUserToken extends AbstractReceiveService {

    @Value("${queue.user.token}")
    private String queueName;

    @Override
    protected String getQueueName() {
        return queueName;
    }

    @Override
    @RabbitListener(queues = "${queue.user.token}")
    protected void doReceive(Message message) {
        handleMes(message, StandardCharsets.UTF_8);
    }

    @Override
    protected ResponseResult doHandle(String message) {
        log.info("接收到mq消息：{}",message);
        return ResponseResult.success();
    }
}
