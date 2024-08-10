package top.fishg.todoapp.service.auth;

import org.springframework.stereotype.Service;
import top.fishg.todoapp.exception.RefreshTokenInvalidException;
import top.fishg.todoapp.exception.UserNotFoundException;
import top.fishg.todoapp.model.auth.RefreshToken;
import top.fishg.todoapp.repository.TodoUserRepository;
import top.fishg.todoapp.repository.auth.RefreshTokenRepository;

import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

// Refresh Token: UUID String
@Service
public class RefreshTokenService {
    private final RefreshTokenRepository refreshTokenRepository;
    private final TodoUserRepository userRepository;

    public RefreshTokenService(RefreshTokenRepository refreshTokenRepository, TodoUserRepository userRepository) {
        this.refreshTokenRepository = refreshTokenRepository;
        this.userRepository = userRepository;
    }

    public RefreshToken create(String email) {
        RefreshToken refreshToken = RefreshToken.builder()
                .todoUser(userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User not found with email: " + email)))
                .token(UUID.randomUUID().toString())
                .expiryDate(Instant.now().plusSeconds(60 * 60 * 24)) // 24 hours
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    public Optional<RefreshToken> findByToken(String token) {
        return refreshTokenRepository.findByToken(token);
    }

    public void deleteByToken(String token) {
        refreshTokenRepository.deleteByToken(token);
    }

    public void deleteByEmail(String email) {
        userRepository.findByEmail(email).ifPresent(
                refreshTokenRepository::deleteByTodoUser
        );
    }

    public RefreshToken verifyExpiration(RefreshToken token) {
        if (token.getExpiryDate().compareTo(Instant.now()) < 0) {
            refreshTokenRepository.delete(token);
            throw new RefreshTokenInvalidException("Refresh token (" + token.getToken() + ") is expired. Please make a new login!");
        }
        return token;
    }
}
