package com.borges.services;

import com.borges.exception.EntityNotFoundException;
import com.borges.models.Cliente;
import com.borges.repositories.ClienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;

    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    public Cliente salvarCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Page<Cliente> buscarTodosOsClientes(@PageableDefault Pageable pageable) {
        return clienteRepository.findAll(pageable);
    }

    public Cliente buscarCliente(@PathVariable Long id) {
        Optional<Cliente> optional = clienteRepository.findById(id);
        return optional.orElseThrow(() -> new EntityNotFoundException("Cliente não encontrado."));
    }

    public Cliente atualizarCliente(Cliente cliente, Long id) {
        Cliente clienteOriginal = this.buscarCliente(id);
        cliente.setId(clienteOriginal.getId());
        return clienteRepository.save(cliente);
    }

    public void excluirCliente(@PathVariable Long id) {
        Cliente clienteOriginal = this.buscarCliente(id);
        clienteRepository.deleteById(clienteOriginal.getId());
    }
}
