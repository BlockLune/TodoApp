package top.fishg.todoapp.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.fishg.todoapp.dto.auth.LoginResponse;
import top.fishg.todoapp.dto.auth.RefreshTokenRequest;
import top.fishg.todoapp.exception.RefreshTokenInvalidException;
import top.fishg.todoapp.model.auth.RefreshToken;
import top.fishg.todoapp.service.auth.AccessTokenService;
import top.fishg.todoapp.service.auth.RefreshTokenService;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    private final AccessTokenService accessTokenService;
    private final RefreshTokenService refreshTokenService;

    public AuthController(AccessTokenService accessTokenService, RefreshTokenService refreshTokenService) {
        this.accessTokenService = accessTokenService;
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(Authentication authentication) {
        return ResponseEntity.ok(
                new LoginResponse(
                        accessTokenService.create(authentication),
                        refreshTokenService.create(authentication.getName()).getToken()));
    }

    @PostMapping("/refresh")
    public ResponseEntity<LoginResponse> refreshAccessToken(@RequestBody RefreshTokenRequest refreshTokenRequest) {
        try {
            RefreshToken refreshToken = refreshTokenService.verifyExpiration(
                    refreshTokenService.findByToken(refreshTokenRequest.refreshToken())
                            .orElseThrow(() -> new RefreshTokenInvalidException("Refresh token not found")));
            refreshTokenService.deleteByToken(refreshTokenRequest.refreshToken());

            String newAccessToken = accessTokenService.create(refreshToken.getTodoUser());
            String newRefreshToken = refreshTokenService.create(refreshToken.getTodoUser().getEmail()).getToken();
            return ResponseEntity.ok(new LoginResponse(newAccessToken, newRefreshToken));
        } catch (Exception e) {
            return ResponseEntity.badRequest().build();
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(Authentication authentication) {
        refreshTokenService.deleteByEmail(authentication.getName());
        return ResponseEntity.noContent().build();
    }
}
