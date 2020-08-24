package dev.hulk.leave.service;

import dev.hulk.leave.entity.Role;
import dev.hulk.leave.entity.User;
import dev.hulk.leave.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MyUserDetailService implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findOneByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException("No user found with email: " + username);
        }
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassHash(), getAuthorities(user.getRole()));
    }

    private static List<GrantedAuthority> getAuthorities(Role role) {
        List<GrantedAuthority> authorities = new ArrayList<>();
            authorities.add(new SimpleGrantedAuthority(role.getName()));

        return authorities;
    }
}
