package uz.best.company.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import uz.best.company.repository.UserRepository;
import uz.best.company.repository.UserRoleRepository;
import uz.best.company.domain.auth.User;
import uz.best.company.domain.auth.UserRole;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 07.08.2023 15:31
 */
@Component("userDetailsService")
@Slf4j
public class DomainUserDetailsService implements ReactiveUserDetailsService {

    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;

    public DomainUserDetailsService(UserRepository userRepository, UserRoleRepository userRoleRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
    }

    @Override
    @Transactional(readOnly = true)
    public Mono<UserDetails> findByUsername(final String login) {
        log.debug("Authenticating {}", login);

        return userRepository
                .findByUsername(login.toLowerCase(Locale.ENGLISH))
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("User " + login + " was not found in the database")))
                .flatMap(this::findUserRoles);
    }

    private Mono<org.springframework.security.core.userdetails.User> findUserRoles(User user) {
        return userRoleRepository
                .findAllByUserId(user.getId())
                .map(UserRole::getRoleId)
                .collect(Collectors.toList())
                .map(roles -> this.createSpringSecurityUser(user, roles));
    }

    private org.springframework.security.core.userdetails.User createSpringSecurityUser(User user, List<String> roles) {
        List<GrantedAuthority> grantedAuthorities = roles
                .stream()
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), grantedAuthorities);
    }
}
