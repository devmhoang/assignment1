package demo.backend;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;

import demo.backend.service.RedisService;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class RedisServiceTest {

    @Mock
    private RedisTemplate<String, Object> redis;

    @Mock
    private ValueOperations<String, Object> valueOperations;

    @InjectMocks
    private RedisService redisService;

    @Test
    void blacklistJwt_ShouldStoreHashedToken() {
        when(redis.opsForValue()).thenReturn(valueOperations);
        
        redisService.blacklistJwt("test.jwt.token", 600L);
        
        verify(valueOperations, times(1)).set(
            startsWith("blacklist:"), 
            eq("blacklisted"), 
            eq(600L), 
            eq(TimeUnit.SECONDS)
        );
    }

    @Test
    void isJwtBlacklisted_ShouldReturnTrue_WhenKeyExists() {
        when(redis.hasKey(anyString())).thenReturn(true);
        
        boolean result = redisService.isJwtBlacklisted("test.jwt.token");
        
        assertTrue(result);
        verify(redis, times(1)).hasKey(anyString());
    }

}