package com.borges.controllers;

import com.borges.dtos.filial.ConsultarFilialDto;
import com.borges.dtos.filial.FilialMapper;
import com.borges.dtos.filial.RegistroFilialDto;
import com.borges.models.Filial;
import com.borges.services.FilialService;
import io.swagger.annotations.ApiOperation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/filial")
public class FilialController {

    private final FilialService filialService;

    public FilialController(FilialService filialService) {
        this.filialService = filialService;
    }

    @GetMapping
    @ApiOperation(value = "Buscar todas as filiais.")
    public ResponseEntity<Page<ConsultarFilialDto>> buscarTodasAsFiliais(@PageableDefault(size = 10) Pageable pageable) {
        return ResponseEntity.ok(filialService.buscarTodasAsFiliais(pageable)
                .map(FilialMapper::fromEntity));
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Salvar uma filial.")
    public ResponseEntity<ConsultarFilialDto> salvarFilial(@RequestBody RegistroFilialDto dto) {
        Filial filial = filialService.salvarFilial(FilialMapper.fromDto(dto));
        return ResponseEntity.ok(FilialMapper.fromEntity(filial));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Buscar uma filial.")
    public ResponseEntity<ConsultarFilialDto> buscarFilial(@PathVariable Long id) {
         Filial filial = filialService.buscarFilial(id);
         return ResponseEntity.ok(FilialMapper.fromEntity(filial));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Atualizar uma filial.")
    public ResponseEntity<ConsultarFilialDto> alterarFilial(@RequestBody RegistroFilialDto dto,
                                                            @PathVariable Long id) {
        try {
            Filial filial = filialService.atualizarFilial(FilialMapper.fromDto(dto), id);
            return ResponseEntity.ok(FilialMapper.fromEntity(filial));
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @ApiOperation(value = "Excluir uma filial.")
    public ResponseEntity<ConsultarFilialDto> excluirFilial(@PathVariable Long id) {
        try {
            filialService.excluirFilial(id);
            return ResponseEntity.ok().build();
        } catch(RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
