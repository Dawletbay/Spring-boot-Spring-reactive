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
import uz.best.company.dto.company.CompanyDetailDTO;
import uz.best.company.dto.company.CompanyListDTO;
import uz.best.company.service.CompanyService;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 20.07.2023 17:30
 */
@Tag(name = "Company controller")
@RestController
@RequestMapping("/api/v1/companies")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class CompanyController {

    CompanyService companyService;

    @Operation(method = "GET", description = "Company list ", summary = "Get Company list")
    @GetMapping
    public ResponseEntity<Flux<CompanyListDTO>> getList() {
        return ResponseEntity.ok(companyService.getList());
    }

    @Operation(method = "GET", description = "Company Detail by ID ", summary = "Get Company detail by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Mono<CompanyDetailDTO>> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(companyService.get(id));
    }
}
