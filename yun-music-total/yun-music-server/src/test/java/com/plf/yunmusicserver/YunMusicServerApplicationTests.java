package com.plf.yunmusicserver;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@SpringBootTest
@Slf4j
class YunMusicServerApplicationTests {

    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    void contextLoads() {
        Object s = null;
        List<TestA> testAS = new ArrayList<>();
        testAS.add(new TestA("type","code"));
        TestA testA = testAS.stream().filter(value -> "type".equals(value.getValue())).findFirst().orElse(null);
        log.debug("is {}" , testA);
    }

    @Data
    public static class TestA {
        private String value ;

        private String code ;

        TestA(String value , String code){
            this.value = value;
            this.code = code;
        }
    }

    @Test
    public void testRabbitTemplate() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("name", "zhangsan");
        map.put("age", 22);
        rabbitTemplate.convertAndSend("amq.direct","1QAZ2WSX",map);
    }
}
