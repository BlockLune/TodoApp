package top.fishg.todoapp.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;

@Entity
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
    @JsonIgnore
    private String password;

    @Column
    @Schema(description = "The name of the user")
    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "role", joinColumns = @JoinColumn(name = "user_id"))
    @Column(name = "role")
    @Schema(description = "The roles of the user")
    private Set<String> roles;

    public Collection<? extends GrantedAuthority> getAuthorities() {
        return getRoles().stream()
                .map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
                .collect(Collectors.toSet());
    }

    public TodoUser() {
    }

    public TodoUser(Long id, String email, String password, String name, Set<String> roles) {
        this.id = id;
        this.email = email;
        this.password = password;
        this.name = name;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Custom method to get roles (defensive copy)
    public Set<String> getRoles() {
        return new HashSet<>(roles);
    }

    public void setRoles(Set<String> roles) {
        this.roles = roles;
    }
}
