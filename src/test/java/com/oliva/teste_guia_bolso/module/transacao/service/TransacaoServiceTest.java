package com.oliva.teste_guia_bolso.module.transacao.service;

import com.oliva.teste_guia_bolso.module.transacao.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

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
        var a = service.findByUsuarioMesAno(10, 1, 2020);
        var b = service.findByUsuarioMesAno(10, 1, 2020);
        assertThat(a).isEqualTo(b);
    }

    @Test
    public void findTransacao() {
        assertThat(service.findTransacao(1, 4, 2020)).hasSize(3);
    }
}