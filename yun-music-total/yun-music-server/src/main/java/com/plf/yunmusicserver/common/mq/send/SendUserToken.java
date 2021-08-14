package com.plf.yunmusicserver.common.mq.send;

import com.plf.yunmusicentity.commonhttp.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @author mr_peng
 * @since 2021/08/13
 * <p>
 *     用于推送token接口
 * </p>
 */

@Component
@Slf4j
public class SendUserToken extends AbstractSendService{

    @Value("${exchange.user.token.routingKey}")
    private String exchangeRoutingKey;

    @Value("${queue.user.token}")
    private String exchange;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Resource
    private AmqpTemplate amqpTemplate;

    @Override
    protected String getQueueName() {
        return exchange;
    }

    @Override
    public ResponseResult doSend(String context) {
        //rabbitTemplate.convertAndSend(getQueueName(),exchangeRoutingKey,context);
        amqpTemplate.convertAndSend(this.getQueueName(),context);
        log.info("发送mq消息:{}",context);
        return ResponseResult.success();
    }
}
