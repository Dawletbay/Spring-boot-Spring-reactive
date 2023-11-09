package com.best.company.service;

import com.best.company.repository.UserRoleRepository;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.best.company.service.base.BaseService;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 26.07.2023 15:07
 */
@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Transactional
public class UserRoleService extends BaseService {

    UserRoleRepository userRoleRepository;
}
