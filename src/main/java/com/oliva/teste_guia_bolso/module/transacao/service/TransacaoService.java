package com.oliva.teste_guia_bolso.module.transacao.service;

import com.oliva.teste_guia_bolso.module.comum.exception.ValidacaoException;
import com.oliva.teste_guia_bolso.module.transacao.model.Transacao;
import com.oliva.teste_guia_bolso.module.transacao.repository.TransacaoRepository;
import com.oliva.teste_guia_bolso.module.usuario.model.Usuario;
import com.oliva.teste_guia_bolso.module.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
import java.util.List;

@Service
public class TransacaoService {

    private static final int VALOR_INICIAL = 1000;
    private static final int VALOR_FINAL = 100000000;
    private static final int MES_DEZEMBRO = 12;
    private static final int INICIO_MES = 1;
    @Autowired
    private TransacaoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public List<Transacao> gerarTransacoes(Integer usuarioId, int ano, int mes) {
        return usuarioRepository.save(Usuario.criarTransacoes(usuarioId, ano, mes)).getTransacoes();
    }

    public List<Transacao> findTransacao(Integer usuarioId, int mes, int ano) {
        var dataInicio = LocalDate.of(ano, mes, INICIO_MES).atStartOfDay();
        var dataFim = dataInicio.with(TemporalAdjusters.lastDayOfMonth());
        return repository.findByUsuarioIdAndDataBetween(usuarioId, dataInicio, dataFim);
    }

    public List<Transacao> findByUsuarioMesAno(Integer usuarioId, int mes, int ano) {
        validarRequest(usuarioId, mes, ano);
        var transacoes = findTransacao(usuarioId, mes, ano);
        return ObjectUtils.isEmpty(transacoes)
            ? gerarTransacoes(usuarioId, ano, mes)
            : transacoes;
    }


    private void validarRequest(Integer usuarioId, int mes, int ano) {
        if (ObjectUtils.isEmpty(usuarioId) || ObjectUtils.isEmpty(mes) || ObjectUtils.isEmpty(ano)) {
            throw new ValidacaoException("Todos campos devem estar populados.");
        }
        validarId(usuarioId);
        validarMes(mes);
        validarAno(ano);
    }

    private void validarAno(int ano) {
        if (ano <= 0) {
            throw new ValidacaoException("Ano não pode ser número negativo ou zero");
        }
    }


    private void validarMes(int mes) {
        if (mes <= 0 || mes > MES_DEZEMBRO) {
            throw new ValidacaoException("Mês do ano está inválido, favor selecionar de 1 a 12");
        }
    }

    private void validarId(Integer usuarioId) {
        if (usuarioId < VALOR_INICIAL || usuarioId > VALOR_FINAL) {
            throw new ValidacaoException("Id do usuário esta inválido, o valor deve estar entre 1.000 a 100.000.000");
        }
    }
}
