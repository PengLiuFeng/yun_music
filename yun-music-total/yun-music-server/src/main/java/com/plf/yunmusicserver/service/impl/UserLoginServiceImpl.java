package com.plf.yunmusicserver.service.impl;

import com.google.common.base.Strings;
import com.plf.yunmusiccommon.util.JacksonUtils;
import com.plf.yunmusiccommon.util.JavaObjectExchangeUtils;
import com.plf.yunmusicentity.commonhttp.ResponseResult;
import com.plf.yunmusicentity.dto.user.UserDTO;
import com.plf.yunmusicentity.enums.ResponseStatusEnum;
import com.plf.yunmusicserver.common.mq.send.SendUserToken;
import com.plf.yunmusicserver.config.threadpool.ForkJoinPoolExtend;
import com.plf.yunmusicserver.config.threadpool.ThreadPoolExtend;
import com.plf.yunmusicserver.dao.UserMapper;
import com.plf.yunmusicserver.entity.User;
import com.plf.yunmusicserver.exception.TokenException;
import com.plf.yunmusicserver.service.TokenService;
import com.plf.yunmusicserver.service.UserLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@Service
@Slf4j
public class UserLoginServiceImpl implements UserLoginService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private SendUserToken sendUserToken;

    @Qualifier("redisTemplate")
    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public ResponseResult<UserDTO> loginIn(String recode, String password) {
        if (Strings.isNullOrEmpty(recode) || Strings.isNullOrEmpty(password)) {
            log.error("【登录】当前用户的登录号和密码均为空！recode = {} ; password = {}", recode, password);
            return ResponseResult.error(ResponseStatusEnum.LOGIN_ERROR.getCode(), ResponseStatusEnum.LOGIN_ERROR.getMessage());
        }
        User user = new User();
        user.setUserCode(recode);
        user.setUserIphoneNumber(recode);
        user.setUserEmail(recode);
        user.setUserPassword(password);
        user = userMapper.selectByUserLoginIn(user);
        log.debug("【登录】当前登录用户{}", JacksonUtils.javaBeanToString(user));
        if (Objects.nonNull(user)) {
            UserDTO userDTO = JavaObjectExchangeUtils.objectExchange(user,UserDTO.class);
            try{
                userDTO.setToken(tokenService.getToken(user));
                CompletableFuture.runAsync(() ->{
                    redisTemplate.opsForValue().set(userDTO.getToken(),userDTO,100, TimeUnit.SECONDS);
                    sendUserToken.doSend(JacksonUtils.javaBeanToString(userDTO));
                });
                return ResponseResult.success(userDTO);
            }catch (TokenException e){
                log.error("用户{}获取token报错：{}",user.getUserNickName() , JacksonUtils.javaBeanToString(e));
                return ResponseResult.error(ResponseStatusEnum.ERROR002.getCode(),ResponseStatusEnum.ERROR002.getMessage());
            }
        }
        return ResponseResult.error(ResponseStatusEnum.ERROR001.getCode(),ResponseStatusEnum.ERROR001.getMessage());
    }
    static volatile int s = 0;
    static AtomicInteger integer = new AtomicInteger(0);
    public static void main(String[] arg) {
        //Async();
        Executor threadPool = ThreadPoolExtend.getThreadPool();
        Executor forkJoinPool = ForkJoinPoolExtend.getInstance();
        int i = 0;
        //CompletableFuture<Void> test = CompletableFuture.supplyAsync(() -> async());

        while (i<100){
            final int  s = i;
//            try {
//                Thread.sleep(10);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
            CompletableFuture.supplyAsync(() -> test(s),threadPool);
//            CompletableFuture<Integer> doubleCompletableFuture = CompletableFuture.supplyAsync(() -> test(s)).exceptionally(throwable -> {
//                log.error("throwable is {}",throwable.toString());
//                return null;
//            });
//            CompletableFuture<Integer> two = CompletableFuture.supplyAsync(() -> test(s)).thenApply(value -> {
//                log.error(" two throwable is {}",value);
//                return null;
//            });
//
//            CompletableFuture.allOf(doubleCompletableFuture).join();
//            try {
//                log.info("test {},{},{},{}" ,doubleCompletableFuture.get());
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            } catch (ExecutionException e) {
//                e.printStackTrace();
//            }
            i++;
        }


//        log.info("····················");
//        long time = TimeUnit.SECONDS.toNanos(5);
//        while (true){
//
//        }

//        try {
//            Thread.sleep(9000);
//            int j = 10;
//            while(j > 0){
//                final int  s = j;
//                CompletableFuture<Integer> doubleCompletableFuture = CompletableFuture.supplyAsync(() -> test(s),threadPool);
//                j--;
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        ReentrantLock reentrantLock = new ReentrantLock();
//        Condition condition = reentrantLock.newCondition();
//        while(true){
//            if(time <= 0){
//                System.out.println("main is over = " + i);
//                return;
//            }
//            try {
//                reentrantLock.lockInterruptibly();
//                time = condition.awaitNanos(time);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }finally {
//                reentrantLock.unlock();
//            }
//        }
//        List<long[]> longs = Arrays.asList();
//        Arrays.stream(LongStream.rangeClosed(1, 10000).toArray()).parallel().forEach(e-> test(e));
//        try {
//            Thread.sleep(9000);
//            int j = 10;
//            while(j > 0){
//                final int  s = j;
//                CompletableFuture<Integer> doubleCompletableFuture = CompletableFuture.supplyAsync(() -> test(s),threadPool);
//                j--;
//            }
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    private static <U> U async() {
        int i = 100000;
        int size = 0;
        while (true){
            size++;
            CompletableFuture<Integer> doubleCompletableFuture = CompletableFuture.supplyAsync(() -> test(-1));
            i--;
            if (i < 0){
                try {
                    log.info("----------------------------{}",size);
                    Thread.sleep(2000);
                    i = 100000;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static int test(int n){
        log.info(" n = {} ", n);
        try{
            Thread.sleep(3000);
        }catch (Exception e){
            log.info(e.toString());
        }
            s = n;
            integer.set(n);
        return n;
    }

    public static void Async(){
        CompletableFuture<Integer> studentCompletableFuture = CompletableFuture.supplyAsync(() -> {

            try {
                Thread t = Thread.currentThread();
                System.out.printf("Thread_Name: %s, Daemon: %s%n", t.getName(), t.isDaemon());
                System.out.println("Inside first then main");
                Thread.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            }
            return 3;
        }).thenApply(i -> {

                    for (int j = 0; j <= i; j++) {
                        System.out.println("Inside first then apply");
                    }
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("First then apply is finished");
                    return ++i;
                })
                .thenApply(i -> {
                    System.out.println("Inside 2nd then apply");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Inside 2nd then apply stopped");

                    return i++;
                })
                .thenApply(i -> {
                    System.out.println("Inside 3nd then apply");
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("Inside 3nd then apply stopped");
                    return "The i is ::: " + i;
                })
                .thenApply(s -> 1);
        System.out.println("Executing..");
        System.out.println("Executing..");
        System.out.println("Executing..");
        System.out.println("Executing..");
        System.out.println("Executing..");

    }

}
