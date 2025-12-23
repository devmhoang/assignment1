package demo.backend.service;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

@Service
public class RedisService {

  @Autowired
  private RedisTemplate<String, Object> redis;

  public void blacklistJwt(String jwt, long expSeconds) { // logged-out jwt
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(jwt.getBytes(StandardCharsets.UTF_8));
      String hashedToken = HexFormat.of().formatHex(hash);
      redis.opsForValue().set("blacklist:" + hashedToken, "blacklisted", expSeconds, TimeUnit.SECONDS);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace(); // show what's REALLY happening
      throw new RuntimeException("SHA-256 not supported!-01", e);
    }
  }

  public boolean isJwtBlacklisted(String jwt) { // validate if jwt is blacklisted
    try {
      MessageDigest digest = MessageDigest.getInstance("SHA-256");
      byte[] hash = digest.digest(jwt.getBytes(StandardCharsets.UTF_8));
      String hashedToken = HexFormat.of().formatHex(hash);
      return redis.hasKey("blacklist:" + hashedToken);
    } catch (NoSuchAlgorithmException e) {
      e.printStackTrace();
      throw new RuntimeException("SHA-256 not supported!-02");
    }
  }

}
