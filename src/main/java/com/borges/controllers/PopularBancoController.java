package com.borges.controllers;

import com.borges.services.DBService;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/banco")
public class PopularBancoController {

        private final DBService dbService;

    public PopularBancoController(DBService dbService) {
        this.dbService = dbService;
    }

    @PostMapping
    @ApiOperation(value = "Popular banco de dados.")
    public ResponseEntity<Void> popularBanco() {
        dbService.run();
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }
}
