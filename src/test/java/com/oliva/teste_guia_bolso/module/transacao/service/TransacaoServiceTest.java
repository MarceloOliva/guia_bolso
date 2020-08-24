package com.oliva.teste_guia_bolso.module.transacao.service;

import com.oliva.teste_guia_bolso.module.transacao.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;

@Transactional
@SpringBootTest
@Sql(scripts = "classpath:/scripts/test_transacao.sql")
class TransacaoServiceTest {

    @Autowired
    private TransacaoService service;
    @Autowired
    private TransacaoRepository repository;

    @Test
    public void gerarTransacoes() {
        assertThat(repository.findAll()).hasSize(3);
        assertThat(service.gerarTransacoes(10, 2020, 1)).hasSize(4);
        assertThat(repository.findAll()).hasSize(7);
    }

    @Test
    public void findByUsuarioMesAno() {
        assertThat(service.findByUsuarioMesAno(10000, 4, 2020))
            .extracting("descricao", "valor", "usuario.id")
            .containsExactlyInAnyOrder(
                tuple("PROSPECT", 50, 10000),
                tuple("BASE NET", -5012, 10000),
                tuple("BASE CLARO", 523123, 10000)
            );
    }

    @Test
    public void findTransacao() {
        assertThat(service.findTransacao(10000, 4, 2020)).hasSize(3);
    }

    @Test
    public void findTransacao_() {
        assertThat(service.findByUsuarioMesAno(10000, 4, 97)).hasSize(4);
    }
}