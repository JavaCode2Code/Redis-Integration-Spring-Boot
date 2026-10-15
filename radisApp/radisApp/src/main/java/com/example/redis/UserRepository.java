package com.example.redis;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepository {

    private final RedisTemplate<String, Object> redisTemplate;
    private static final String HASH_KEY = "User";

    public UserRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public User save(User user) {
        redisTemplate.opsForHash().put(HASH_KEY, user.getId(), user);
        return user;
    }

    public User findById(String id) {
        return (User) redisTemplate.opsForHash().get(HASH_KEY, id);
    }

    public void delete(String id) {
        redisTemplate.opsForHash().delete(HASH_KEY, id);
    }
}
            