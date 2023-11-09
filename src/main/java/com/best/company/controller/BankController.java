package com.best.company.controller;

import com.best.company.service.BankService;
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
import com.best.company.dto.bank.BankDTO;

import java.util.UUID;

import static lombok.AccessLevel.PRIVATE;

/**
 * Powered by: Dawletbay Tilepbaev
 * Date: 21.08.2023 10:23
 */

@Tag(name = "Bank controller")
@RestController
@RequestMapping("/api/v1/bank")
@RequiredArgsConstructor
@FieldDefaults(level = PRIVATE, makeFinal = true)
public class BankController {

    BankService bankService;

    @Operation(method = "GET", description = "Bank by ID ", summary = "Get Bank data by ID")
    @GetMapping("/{id}")
    public ResponseEntity<Mono<BankDTO>> get(@PathVariable UUID id) {
        return ResponseEntity.ok(bankService.get(id));
    }

    @Operation(method = "GET", description = "Bank by MFO ", summary = "Get Bank data by MFO")
    @GetMapping("/mfo/{mfo}")
    public ResponseEntity<Mono<BankDTO>> getByMfo(@PathVariable String mfo) {
        return ResponseEntity.ok(bankService.getBankByMfo(mfo));
    }

    @Operation(method = "GET", description = "Bank list ", summary = "Get Bank list")
    @GetMapping
    public ResponseEntity<Flux<BankDTO>> getList() {
        return ResponseEntity.ok(bankService.getList());
    }
}
