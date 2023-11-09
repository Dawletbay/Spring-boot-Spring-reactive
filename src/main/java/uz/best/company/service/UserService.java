package uz.best.company.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.best.company.domain.auth.User;
import uz.best.company.dto.auth.UserDTO;
import uz.best.company.mappers.UserMapper;
import uz.best.company.repository.UserRepository;
import uz.best.company.service.base.BaseService;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 26.07.2023 15:07
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
public class UserService extends BaseService {

    UserRepository userRepository;
    UserMapper userMapper;

    public Mono<User> getById(UUID uuid) {
        return userRepository.findById(uuid);
    }

    public Flux<UserDTO> getList() {
        return userRepository.findAllByDeletedIsFalse().map(userMapper::toDTO);
    }
}
