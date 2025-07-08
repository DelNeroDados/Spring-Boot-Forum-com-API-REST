package com.delnerodados.forumhub.controller;

import com.delnerodados.forumhub.domain.topico.*;
import com.delnerodados.forumhub.domain.usuario.UsuarioRepository;
import com.delnerodados.forumhub.domain.usuario.Usuario;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/topicos")
public class TopicoController {

    @Autowired
    private TopicoRepository repository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @PostMapping
    @Transactional
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroTopico dados, UriComponentsBuilder uriBuilder) {
        Usuario autor = usuarioRepository.findById(dados.autorId())
                .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
        var topico = new Topico(dados, autor);
        repository.save(topico);
        var uri = uriBuilder.path("/topicos/{id}").buildAndExpand(topico.getId()).toUri();
        return ResponseEntity.created(uri).body(new DadosDetalhamentoTopico(topico));
    }


    @GetMapping
    public ResponseEntity<Page<DadosListagemTopico>> listar(
            @PageableDefault(size = 10, sort = {"dataCriacao"}) Pageable paginacao) {
        var page = repository.findAllByAtivoTrue(paginacao).map(DadosListagemTopico::new);
        return ResponseEntity.ok(page);
    }

    @GetMapping("/{id}")
    public ResponseEntity detalhar(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @PutMapping
    @Transactional
    public ResponseEntity atualizar(@RequestBody @Valid DadosAtualizacaoTopico dados) {
        var topico = repository.getReferenceById(dados.id());
        if (dados.titulo() != null) topico.setTitulo(dados.titulo());
        if (dados.mensagem() != null) topico.setMensagem(dados.mensagem());
        return ResponseEntity.ok(new DadosDetalhamentoTopico(topico));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity excluir(@PathVariable Long id) {
        var topico = repository.getReferenceById(id);
        topico.setAtivo(false);
        return ResponseEntity.noContent().build();
    }

}
