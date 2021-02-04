package com.linyy.kitty.admin.redis;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.Message;
import org.springframework.data.redis.listener.KeyExpirationEventMessageListener;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.stereotype.Component;

/**
 * 监听redis所有过期事件
 */
@Component
public class RedisKeyExpirationListener extends KeyExpirationEventMessageListener {

    private static final Logger log = LoggerFactory.getLogger(RedisKeyExpirationListener.class);


    public RedisKeyExpirationListener(RedisMessageListenerContainer listenerContainer) {
        super(listenerContainer);
    }


    @Override
    public void onMessage(Message message, byte[] pattern) {
        String expiredKey = message.toString();
        log.info("expireKey: {}", expiredKey);
    }
}
