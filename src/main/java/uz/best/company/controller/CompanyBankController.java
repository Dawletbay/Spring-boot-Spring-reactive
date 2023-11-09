package uz.best.company.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import uz.best.company.service.CompanyBankService;
import uz.best.company.dto.companyBank.CompanyBankDTO;
import uz.best.company.dto.companyBank.CompanyBankDetailDTO;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 21.08.2023 15:51
 */
@Tag(name = "Company Bank controller")
@RestController
@RequestMapping("/api/v1/company-bank")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CompanyBankController {

    CompanyBankService companyBankService;

    @Operation(method = "GET", description = "Company Bank list ", summary = "Get Company Bank list")
    @GetMapping
    public ResponseEntity<Flux<CompanyBankDTO>> getList() {
        return ResponseEntity.ok(companyBankService.getList());
    }

    @Operation(method = "GET", description = "Company Bank detail by ID ", summary = "Get Company detail by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Mono<CompanyBankDetailDTO>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(companyBankService.get(id));
    }
}
