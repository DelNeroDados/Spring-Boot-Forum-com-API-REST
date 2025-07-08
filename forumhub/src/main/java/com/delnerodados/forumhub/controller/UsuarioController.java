package com.delnerodados.forumhub.controller;

import com.delnerodados.forumhub.domain.usuario.*;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository repository;

    // Encoder para criptografar senhas
    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping
    public ResponseEntity<?> cadastrar(@RequestBody @Valid DadosCadastroUsuario dados) {
        // Verifica se o login já existe
        if (repository.findByLogin(dados.login()) != null) {
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("mensagem", "Login já existente"));
        }
        // Criptografa a senha antes de salvar
        String senhaCriptografada = encoder.encode(dados.senha());
        Usuario usuario = new Usuario(dados.login(), senhaCriptografada);
        repository.save(usuario);
        return ResponseEntity.ok(Map.of("mensagem", "Usuário cadastrado com sucesso!"));
    }
}
