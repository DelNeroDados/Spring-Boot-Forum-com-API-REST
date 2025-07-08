package com.delnerodados.forumhub.domain.topico;

import java.time.LocalDateTime;

public record DadosDetalhamentoTopico(
        Long id,
        String titulo,
        String mensagem,
        String autor,
        LocalDateTime dataCriacao
) {
    public DadosDetalhamentoTopico(Topico topico) {
        this(
                topico.getId(),
                topico.getTitulo(),
                topico.getMensagem(),
                topico.getAutor() != null ? topico.getAutor().getLogin() : null,
                topico.getDataCriacao()
        );
    }
}


