package com.example.buysell.models;

import com.example.buysell.models.enums.Role;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="users")
@Data
public class User implements UserDetails {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //значение будет сгенерировано бд автоматически
    @Column(name="id")
    private Long id;
    @Column(name="email", unique = true)
    private String email;
    @Column(name="phoneNumber")
    private String phoneNumber;
    @Column(name="name")
    private String name;
    @Column(name="active")
    private boolean active;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER) //при удалении пользователя - удалена и его ава и ава загружена сразу при загрузке пользователя
    @JoinColumn(name = "image_id")
    private Image avatar;
    @Column(name="password", length = 1000)
    private String password;
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @CollectionTable(name="user_role", joinColumns = @JoinColumn(name="user_id")) //опр таблицу бд для хранения эл-тов
    @Enumerated(EnumType.STRING) //Role будут сохр в виде строк
    private Set<Role> roles=new HashSet<>();
    private LocalDateTime dateOfCreate;

    @PrePersist
    private void init(){
        dateOfCreate=LocalDateTime.now();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() { //возвращ коллекцию ролей пользователя для определения прав доступа
        return roles;
    }

    @Override
    public String getUsername() {
        return email;
    }

    // возвращ true, если данные пользователя действительны
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return active;
    } //active - пользователь в системе
}
