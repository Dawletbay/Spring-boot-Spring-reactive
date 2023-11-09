package uz.best.company.service;

import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.best.company.domain.company.CompanyBank;
import uz.best.company.dto.bank.BankLookUpDTO;
import uz.best.company.dto.companyBank.CompanyBankDTO;
import uz.best.company.dto.companyBank.CompanyBankDetailDTO;
import uz.best.company.repository.BankRepository;
import uz.best.company.repository.CompanyBankRepository;
import uz.best.company.service.base.BaseService;

import java.util.Objects;
import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
@Transactional(readOnly = true)
@Slf4j
public class CompanyBankService extends BaseService {

    CompanyBankRepository companyBankRepository;
    BankRepository bankRepository;

    public Mono<CompanyBankDetailDTO> get(UUID companyId) {
        return companyBankRepository.findByCompanyId(companyId)
                .flatMap(companyBank -> {
                    CompanyBankDetailDTO companyBankDetailDTO = new CompanyBankDetailDTO();
                    companyBankDetailDTO.setId(companyBank.getId());
                    companyBankDetailDTO.setMain(companyBank.isMain());
                    companyBankDetailDTO.setAccountNumber(companyBank.getAccountNumber());
                    companyBankDetailDTO.setOked(companyBank.getOked());
                    if (Objects.nonNull(companyBank.getBankId()))
                        return bankRepository
                                .findById(companyBank.getBankId())
                                .flatMap(bank -> {
                                    BankLookUpDTO bankLookUpDTO = new BankLookUpDTO();
                                    bankLookUpDTO.setId(bank.getId());
                                    bankLookUpDTO.setMfo(bank.getMfo());
                                    bankLookUpDTO.setName(bank.getNameEn());
                                    companyBankDetailDTO.setBank(bankLookUpDTO);
                                    if (Objects.nonNull(bank.getParentId()))
                                        return bankRepository
                                                .findById(bank.getParentId())
                                                .flatMap(bankParent -> {
                                                    BankLookUpDTO parentBankDTO = new BankLookUpDTO();
                                                    parentBankDTO.setId(bankParent.getId());
                                                    parentBankDTO.setMfo(bankParent.getMfo());
                                                    parentBankDTO.setName(bankParent.getNameEn());
                                                    companyBankDetailDTO.setParentBank(parentBankDTO);
                                                    return Mono.just(companyBankDetailDTO);
                                                });
                                    return Mono.just(companyBankDetailDTO);
                                });
                    else return Mono.just(companyBankDetailDTO);
                });
    }

    public Flux<CompanyBankDTO> getList() {
        return companyBankRepository.findAllByDeletedIsFalse()
                .map(CompanyBank::toDTO)
                .switchIfEmpty(Flux.just(new CompanyBankDTO()));
    }

    public Mono<CompanyBank> create(CompanyBank companyBank) {
        return companyBankRepository.save(companyBank);
    }

    public Mono<CompanyBank> getByCompanyIdAndBankId(UUID companyId, UUID bankId) {
        return companyBankRepository.findByCompanyIdAndBankId(companyId, bankId);
    }
}
