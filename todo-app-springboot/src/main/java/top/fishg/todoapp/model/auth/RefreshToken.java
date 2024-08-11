package top.fishg.todoapp.model.auth;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import top.fishg.todoapp.model.TodoUser;

import java.time.Instant;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
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
