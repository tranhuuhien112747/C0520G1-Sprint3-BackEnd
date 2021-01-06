package c0520g1.sprint_3.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class UserDetailsImpl implements UserDetails {
    private long idUser;
    private String username;
    @JsonIgnore
    private String password;
    private String fullName;
    private String money;
    private String timeRemaining;
    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(long idUser, String username, String password, String fullName, Collection<? extends GrantedAuthority> authorities, String money,String timeRemaining) {
        this.idUser = idUser;
        this.username = username;
        this.password = password;
        this.fullName = fullName;
        this.authorities = authorities;
        this.money = money;
        this.timeRemaining = timeRemaining;
    }

    public static UserDetailsImpl build(User user) {
        List<GrantedAuthority> grantList = new ArrayList<>();
        ;
        grantList.add(new SimpleGrantedAuthority(user.getRole().getRoleName()));
        return new UserDetailsImpl(user.getIdUser(),
                user.getUsername(),
                user.getPassword(),
                user.getFullName(),
                grantList,
                user.getMoney(),
                user.getTimeRemaining());
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.authorities;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

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
        return true;
    }

    public long getIdUser() {
        return idUser;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getTimeRemaining() {
        return timeRemaining;
    }

    public void setTimeRemaining(String timeRemaining) {
        this.timeRemaining = timeRemaining;
    }

    public void setIdUser(long idUser) {
        this.idUser = idUser;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAuthorities(Collection<? extends GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

}
