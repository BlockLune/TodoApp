package top.fishg.todoapp.model;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "user")
@Schema(description = "A user")
public class TodoUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "The ID of the user")
    private Long id;

    @Column(unique = true, nullable = false)
    @Schema(description = "The email of the user. Must be unique")
    private String email;

    @Column(nullable = false)
    @Schema(description = "The password of the user. Stored as a bcrypt hash")
    private String password;

    @Column
    @Schema(description = "The name of the user")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Schema(description = "The roles of the user")
    private Set<String> roles;

    // Custom method to get roles (defensive copy)
    public Set<String> getRoles() {
        return new HashSet<>(roles);
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toSet());
    }
}
