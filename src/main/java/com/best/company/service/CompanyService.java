package com.best.company.service;

import com.best.company.integration.TestClient;
import com.best.company.repository.*;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import com.best.company.domain.Bank;
import com.best.company.domain.address.Address;
import com.best.company.domain.address.Region;
import com.best.company.domain.company.Company;
import com.best.company.domain.company.CompanyBank;
import com.best.company.dto.address.AddressDetailDTO;
import com.best.company.dto.bank.BankDetailDTO;
import com.best.company.dto.company.CompanyDetailDTO;
import com.best.company.dto.company.CompanyListDTO;
import com.best.company.dto.integration.some.CompanySomeDTO;
import com.best.company.exceptions.NotFoundException;
import com.best.company.service.base.BaseService;

import java.util.Objects;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 20.07.2023 15:31
 */

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
@Slf4j
public class CompanyService extends BaseService {

    CompanyRepository companyRepository;
    CompanyBankService companyBankService;
    AddressService addressService;
    BankService bankService;
    DistrictRepository districtRepository;
    RegionRepository regionRepository;
    CompanyBankRepository companyBankRepository;
    BankRepository bankRepository;
    TestClient testClient;
    AddressRepository addressRepository;

    public Mono<CompanyDetailDTO> get(UUID id) {
        return companyRepository
                .findById(id)
                .flatMap(company -> {
                    CompanyDetailDTO companyDetailDTO = new CompanyDetailDTO();
                    companyDetailDTO.setId(company.getId());
                    companyDetailDTO.setName(company.getName());
                    companyDetailDTO.setTin(company.getTin());
                    companyDetailDTO.setDirector(company.getDirector());
                    companyDetailDTO.setAccountant(company.getAccountant());
                    return companyBankRepository
                            .findByCompanyId(company.getId())
                            .flatMap(companyBank -> {
                                        if (Objects.nonNull(companyBank.getBankId()))
                                            return bankRepository
                                                    .findById(companyBank.getBankId())
                                                    .flatMap(bank -> {
                                                        BankDetailDTO bankDetailDTO = new BankDetailDTO();
                                                        bankDetailDTO.setId(companyBank.getBankId());
                                                        bankDetailDTO.setAccountNumber(companyBank.getAccountNumber());
                                                        bankDetailDTO.setOked(companyBank.getOked());
                                                        bankDetailDTO.setMfo(bank.getCode());
                                                        bankDetailDTO.setName(bank.getNameRu());
                                                        companyDetailDTO.setBankDetailDTO(bankDetailDTO);
                                                        return Mono.just(companyDetailDTO);
                                                    }).switchIfEmpty(Mono.just(companyDetailDTO));
                                        else
                                            return Mono.just(companyDetailDTO);
                                    }
                            )
                            .flatMap(companyDetailDTO1 -> {
                                        if (Objects.nonNull(company.getAddressId()))
                                            return addressRepository
                                                    .findById(company.getAddressId())
                                                    .flatMap(address -> {
                                                        AddressDetailDTO addressDetailDTO = new AddressDetailDTO();
                                                        addressDetailDTO.setStreet(address.getStreet());
                                                        addressDetailDTO.setApartment(address.getApartment());
                                                        addressDetailDTO.setHouse(address.getHouse());
                                                        addressDetailDTO.setLatitude(address.getLatitude());
                                                        addressDetailDTO.setLongitude(address.getLongitude());
                                                        companyDetailDTO.setAddress(addressDetailDTO);
                                                        return Mono.just(companyDetailDTO);
                                                    }).switchIfEmpty(Mono.just(companyDetailDTO));
                                        else
                                            return Mono.just(companyDetailDTO);
                                    }
                            );
                }).switchIfEmpty(Mono.error(new NotFoundException("By this ID Company not found!")));
    }

    public Flux<CompanyListDTO> getList() {
        return companyRepository
                .findAllByDeletedIsFalseOrderByCreatedDateAsc()
                .map(Company::toListDTO)
                .onErrorContinue((e, company) -> log.error(e.getMessage(), e));
    }


    /*
     * START
     * SYNCHRONIZATION
     * Company Data BY TIN
     * From Tax
     * */

    private Mono<Company> save(Company company, CompanySomeDTO companySomeDTO) {
        if (Objects.isNull(companySomeDTO))
            return Mono.empty();
        company.setTin(companySomeDTO.getTin());
        company.setName(companySomeDTO.getName());
        company.setBrand(companySomeDTO.getBranchName());
        company.setPhone(companySomeDTO.getMobile());
        company.setDirector(companySomeDTO.getDirector());
        return companyRepository.save(company)
                .flatMap(savedCompany -> this.companyBank(savedCompany, companySomeDTO))
                .flatMap(savedCompany -> this.saveAddress(savedCompany, companySomeDTO));
    }

    private Mono<Company> companyBank(Company company, CompanySomeDTO companySomeDTO) {
        if (StringUtils.isNotBlank(companySomeDTO.getMfo())) {
            return bankService
                    .getByMfo(companySomeDTO.getMfo())
                    .flatMap(bank -> companyBankService
                            .getByCompanyIdAndBankId(company.getId(), bank.getId())
                            .flatMap(companyBank -> this.saveCompanyBank(companyBank, company, bank, companySomeDTO))
                            .switchIfEmpty(this.saveCompanyBank(new CompanyBank(), company, bank, companySomeDTO)));
        }
        return Mono.just(company);
    }

    private Mono<Company> saveCompanyBank(CompanyBank companyBank, Company company, Bank bank, CompanySomeDTO companySomeDTO) {
        companyBank.setMain(true);
        companyBank.setBankId(bank.getId());
        companyBank.setCompanyId(company.getId());
        companyBank.setOked(companySomeDTO.getOked());
        companyBank.setAccountNumber(companySomeDTO.getAccount());
        return companyBankService.create(companyBank).thenReturn(company);
    }

    private Mono<Company> saveAddress(Company company, CompanySomeDTO companySomeDTO) {
        if (companySomeDTO.getCode1() != null) {
            return regionRepository
                    .findByCode(companySomeDTO.getCode1())
                    .flatMap(region -> {
                        if (company.getAddressId() != null)
                            return addressService
                                    .getById(company.getAddressId())
                                    .flatMap(address -> {
                                        address.setStreet(companySomeDTO.getAddress());
                                        address.setRegionId(region.getId());
                                        return addressService.create(address);
                                    });
                        else
                            return this.saveCompanyAddress(company, region, companySomeDTO);
                    })
                    .flatMap(address -> {
                        if (companySomeDTO.getCode2() != null) {
                            return districtRepository
                                    .findByRegionCodeAndCode(companySomeDTO.getCode1(), companySomeDTO.getCode2())
                                    .flatMap(district -> {
                                        address.setDistrictId(district.getId());
                                        return addressService.create(address).thenReturn(company);
                                    });
                        } else
                            return Mono.just(company);
                    });
        }
        return Mono.just(company);
    }

    private Mono<Address> saveCompanyAddress(Company company, Region region, CompanySomeDTO companySomeDTO) {
        Address address = new Address();
        address.setStreet(companySomeDTO.getAddress());
        address.setRegionId(region.getId());
        return addressService.create(address)
                .flatMap(savedAddress -> {
                    company.setAddressId(savedAddress.getId());
                    return companyRepository.save(company).thenReturn(savedAddress);
                });
    }
    /*
     * END
     * SYNCHRONIZATION
     * Company Data BY TIN
     * From Tax
     * */

    public Mono<Void> syncCTSCompanyTins() {
        return testClient
                .getTins()
                .then();
    }
}
