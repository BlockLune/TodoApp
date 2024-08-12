package top.fishg.todoapp.model.auth;

import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import top.fishg.todoapp.model.TodoUser;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "refresh_token")
@Schema(description = "A refresh token object")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    @Schema(description = "The ID of the refresh token")
    private Long id;

    @Column(nullable = false, unique = true)
    @Schema(description = "The refresh token itself")
    private String token;

    @Column(nullable = false)
    @Schema(description = "The expiry date of the refresh token")
    private Instant expiryDate;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    @JsonIgnore
    @Schema(description = "The user who owns the refresh token")
    private TodoUser todoUser;
}
