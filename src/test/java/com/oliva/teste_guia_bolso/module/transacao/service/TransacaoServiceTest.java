package com.oliva.teste_guia_bolso.module.transacao.service;

import com.oliva.teste_guia_bolso.module.comum.exception.ValidacaoException;
import com.oliva.teste_guia_bolso.module.transacao.repository.TransacaoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;

@Transactional
@SpringBootTest
@Sql(scripts = "classpath:/scripts/test_transacao.sql")
class TransacaoServiceTest {

    @Autowired
    private TransacaoService service;
    @Autowired
    private TransacaoRepository repository;

    @Test
    public void gerarTransacoes_deveGerarTransacoes_seNaoExistir() {
        assertThat(repository.findAll()).hasSize(3);
        assertThat(service.gerarTransacoes(10, 2020, 1)).hasSize(4);
        assertThat(repository.findAll()).hasSize(7);
    }

    @Test
    public void findByUsuarioMesAno_deveRecuperar_seCasoExistir() {
        assertThat(service.findByUsuarioMesAno(10000, 4, 2020))
            .extracting("descricao", "valor", "usuario.id")
            .containsExactlyInAnyOrder(
                tuple("PROSPECT", 50, 10000),
                tuple("BASE NET", -5012, 10000),
                tuple("BASE CLARO", 523123, 10000)
            );
    }

    @Test
    public void findByUsuarioMesAno_deveDarErro_seAnoNegativoOuZero() {
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(()->service.findByUsuarioMesAno(10000, 4, -2020))
            .withMessage("Ano não pode ser número negativo ou zero");
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(()->service.findByUsuarioMesAno(10000, 4, 0))
            .withMessage("Ano não pode ser número negativo ou zero");
    }
  @Test
    public void findByUsuarioMesAno_deveDarErro_seMesNaoForDeUmADoze() {
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(()->service.findByUsuarioMesAno(10000, 0, 2020))
            .withMessage("Mês do ano está inválido, favor selecionar de 1 a 12");
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(()->service.findByUsuarioMesAno(10000, 13, 2200))
            .withMessage("Mês do ano está inválido, favor selecionar de 1 a 12");
    }

    @Test
    public void findByUsuarioMesAno_deveDarErro_seIdNaoEstiverEntreCertosNumeros() {
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(()->service.findByUsuarioMesAno(10, 1, 2020))
            .withMessage("Id do usuario esta invalido, o valor deve estar entre 1.000 a 100.000.000");
        assertThatExceptionOfType(ValidacaoException.class)
            .isThrownBy(()->service.findByUsuarioMesAno(1000000000, 12, 2200))
            .withMessage("Id do usuario esta invalido, o valor deve estar entre 1.000 a 100.000.000");
    }

    @Test
    public void findTransacao_deveRecuperar_seTudoCerto() {
        assertThat(service.findTransacao(10000, 4, 2020)).hasSize(3);
    }

    @Test
    public void findTransacao_deveRecuperar_seAnoMaiorQueZero() {
        assertThat(service.findByUsuarioMesAno(10000, 4, 97)).hasSize(4);
    }
}