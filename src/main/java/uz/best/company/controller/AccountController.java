package uz.best.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import uz.best.company.config.ApplicationProperties;
import uz.best.company.dto.auth.TokenDTO;
import uz.best.company.dto.integration.best.LoginDTO;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import static uz.best.company.utils.SecurityUtils.AUTHORITIES_KEY;
import static uz.best.company.utils.SecurityUtils.JWT_ALGORITHM;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 07.08.2023 15:31
 */

@Tag(name = "Account controller")
@RestController
@RequestMapping("/api/v1/account")
public class AccountController {

    private final ReactiveAuthenticationManager authenticationManager;
    private final JwtEncoder jwtEncoder;
    private final ApplicationProperties applicationProperties;

    public AccountController(ReactiveAuthenticationManager authenticationManager, JwtEncoder jwtEncoder, ApplicationProperties applicationProperties) {
        this.authenticationManager = authenticationManager;
        this.jwtEncoder = jwtEncoder;
        this.applicationProperties = applicationProperties;
    }

    @Operation(method = "POST", description = "Login URL ", summary = "Login URL")
    @PostMapping("/authenticate")
    public Mono<ResponseEntity<TokenDTO>> authenticate(@RequestBody Mono<LoginDTO> loginDTO) {
        return loginDTO
                .flatMap(login ->
                        authenticationManager
                                .authenticate(new UsernamePasswordAuthenticationToken(login.getUsername(), login.getPassword()))
                                .flatMap(auth -> Mono.fromCallable(() -> this.createToken(auth, login.isRememberMe())))
                )
                .map(token -> {
                    HttpHeaders httpHeaders = new HttpHeaders();
                    httpHeaders.setBearerAuth(token);
                    return new ResponseEntity<>(new TokenDTO(token), httpHeaders, HttpStatus.OK);
                });
    }

    @Operation(method = "GET", description = "Test URL ", summary = "Test URL")
    @GetMapping("/test")
    public ResponseEntity<Mono<String>> test() {
        return ResponseEntity.ok(Mono.just("Test"));
    }

    public String createToken(Authentication authentication, boolean rememberMe) {
        String authorities = authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.joining(" "));

        Instant now = Instant.now();
        Instant validity;
        if (rememberMe) {
            validity = now.plus(applicationProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSecondsForRememberMe(), ChronoUnit.SECONDS);
        } else {
            validity = now.plus(applicationProperties.getSecurity().getAuthentication().getJwt().getTokenValidityInSeconds(), ChronoUnit.SECONDS);
        }

        // @formatter:off
        JwtClaimsSet claims = JwtClaimsSet.builder()
                .issuedAt(now)
                .expiresAt(validity)
                .subject(authentication.getName())
                .claim(AUTHORITIES_KEY, authorities)
                .build();

        JwsHeader jwsHeader = JwsHeader.with(JWT_ALGORITHM).build();
        return this.jwtEncoder.encode(JwtEncoderParameters.from(jwsHeader, claims)).getTokenValue();
    }
}
