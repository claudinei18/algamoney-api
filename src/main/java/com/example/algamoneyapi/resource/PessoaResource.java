package com.example.algamoneyapi.resource;

import com.example.algamoneyapi.event.RecursoCriadoEvent;
import com.example.algamoneyapi.model.Pessoa;
import com.example.algamoneyapi.repository.PessoaRepository;
import com.example.algamoneyapi.repository.PessoaRepository;
import com.example.algamoneyapi.service.PessoaService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/pessoas")
public class PessoaResource {
    @Autowired
    private PessoaRepository pessoaRepository;

    @Autowired
    private ApplicationEventPublisher publisher;

    @Autowired
    private PessoaService pessoaService;

    @GetMapping
    @PreAuthorize("hasAnyAuthority('ROLE_PESQUISAR_PESSOA') and Moauth2.hasScope('read')")
    public List<Pessoa> listar(){
        return pessoaRepository.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @PreAuthorize("hasAnyAuthority('ROLE_CADASTRAR_PESSOA') and Moauth2.hasScope('write')")
    public ResponseEntity<Pessoa> criar(@Valid @RequestBody Pessoa Pessoa, HttpServletResponse response){
        Pessoa pessoaSalva = pessoaRepository.save(Pessoa);

        publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));

        return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
    }

    @GetMapping("/{codigo}")
    @PreAuthorize("hasAnyAuthority('ROLE_PESQUISAR_PESSOA') and Moauth2.hasScope('read')")
    public ResponseEntity<?> buscarPeloCodigo(@PathVariable("codigo") Long codigo){
        Pessoa pessoa = pessoaRepository.findOne(codigo);
        if(pessoa == null){
            return ResponseEntity.notFound().build();
        }else{
            return ResponseEntity.ok(pessoa);
        }
    }

    @DeleteMapping("/{codigo}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ROLE_REMOVER_PESSOA') and Moauth2.hasScope('write')")
    public void remover(@PathVariable("codigo") Long codigo){
        pessoaRepository.delete(codigo);

    }

    @PutMapping("/{codigo}")
    @PreAuthorize("hasAnyAuthority('ROLE_CADASTRAR_PESSOA') and Moauth2.hasScope('write')")
    public ResponseEntity<Pessoa> atualizar(@PathVariable ("codigo") Long codigo, @RequestBody Pessoa pessoa){
        Pessoa pessoaSalva = pessoaService.atualizar(codigo, pessoa);
        return ResponseEntity.ok(pessoaSalva);
    }

    @PutMapping("/{codigo}/ativo")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAnyAuthority('ROLE_CADASTRAR_PESSOA') and Moauth2.hasScope('write')")
    public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo){
        pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
    }

}
