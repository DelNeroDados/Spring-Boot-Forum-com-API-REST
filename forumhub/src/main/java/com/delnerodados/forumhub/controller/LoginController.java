package com.delnerodados.forumhub.controller;

import com.delnerodados.forumhub.domain.usuario.Usuario;
import com.delnerodados.forumhub.domain.usuario.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Map;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepository;

    private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @PostMapping
    public ResponseEntity<?> login(@RequestBody Map<String, String> body) {
        String login = body.get("login");
        String senha = body.get("senha");
        Usuario usuario = usuarioRepository.findByLogin(login);

        if (usuario != null && encoder.matches(senha, usuario.getSenha())) {
            // Retorna o id e login do usuário autenticado
            return ResponseEntity.ok(Map.of(
                    "id", usuario.getId(),
                    "login", usuario.getLogin()
            ));
        }
        // Retorno padronizado para erro
        return ResponseEntity.status(401).body(Map.of("mensagem", "Usuário ou senha inválidos"));
    }
}
