package com.zerobase.tablereservation.manager.entity;

import com.zerobase.tablereservation.auth.type.UserType;
import com.zerobase.tablereservation.global.entity.BaseEntity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Manager extends BaseEntity implements UserDetails {

    /**
     * 매니저 아이디
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 매니저 이름
     */
    @NotBlank
    private String name;

    /**
     * 회원 구분
     */
    @Enumerated(EnumType.STRING)
    private UserType userType;

    /**
     * 매니저 이메일
     */
    @Email
    @Column(unique = true)
    private String email;

    /**
     * 매니저 비밀번호
     */
    @NotBlank
    private String password;

    /**
     * 매니저 휴대폰 번호
     */
    @NotBlank
    private String phone;


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_PARTNER"));
    }

    @Override
    public String getUsername() {
        return null;
    }


    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return false;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return false;
    }
}
