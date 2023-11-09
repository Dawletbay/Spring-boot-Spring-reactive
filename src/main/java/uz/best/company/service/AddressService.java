package uz.best.company.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;
import uz.best.company.repository.AddressRepository;
import uz.best.company.domain.address.Address;
import uz.best.company.service.base.BaseService;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
@Slf4j
public class AddressService extends BaseService {

    AddressRepository addressRepository;

    Mono<Address> create(Address address) {
        return addressRepository.save(address);
    }

    Mono<Address> getById(UUID addressId) {
        return addressRepository.findById(addressId);
    }
}
