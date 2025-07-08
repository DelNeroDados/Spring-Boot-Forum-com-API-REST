package com.delnerodados.forumhub.domain.topico;

public record DadosListagemTopico(Long id, String titulo, String mensagem, String autor) {
    public DadosListagemTopico(Topico topico) {
        this(topico.getId(), topico.getTitulo(), topico.getMensagem(),
                topico.getAutor() != null ? topico.getAutor().getLogin() : null);
    }
}
