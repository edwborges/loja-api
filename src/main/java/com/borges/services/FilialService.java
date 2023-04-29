package com.borges.services;

import com.borges.exception.EntityNotFoundException;
import com.borges.models.Filial;
import com.borges.repositories.FilialRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class FilialService {

    private final FilialRepository filialRepository;

    public FilialService(FilialRepository filialRepository) {
        this.filialRepository = filialRepository;
    }

    public Filial salvarFilial(Filial filial) {
        return filialRepository.save(filial);
    }

    public Page<Filial> buscarTodasAsFiliais(Pageable pageable) {
        return filialRepository.findAll(pageable);
    }

    public Filial buscarFilial(Long id) {
        Optional<Filial> optional = filialRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Filial não encontrada"));
    }

    public Filial atualizarFilial(Filial filial, Long id) {
        Filial filialOriginal = this.buscarFilial(id);
        filial.setId(filialOriginal.getId());
        return filialRepository.save(filial);
    }

    public void excluirFilial(Long id) {
        Filial filialOriginal = this.buscarFilial(id);
        filialRepository.deleteById(filialOriginal.getId());
    }
}
