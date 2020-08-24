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
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class TransacaoService {

    @Autowired
    private TransacaoRepository repository;
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public List<Transacao> gerarTransacoes(Integer id, int ano, int mes) {
        var usuario = new Usuario(id);
        var transacoes = IntStream.range(1, 5)
            .mapToObj(i -> Transacao.gerarTransacao(usuario, LocalDate.of(ano, mes, i).atStartOfDay()))
            .collect(Collectors.toList());
        usuario.setTransacoes(transacoes);

        return usuarioRepository.save(usuario).getTransacoes();
    }

    public List<Transacao> findTransacao(Integer id, int mes, int ano) {
        var dataInicio = LocalDate.of(ano, mes, 1).atStartOfDay();
        var dataFim = dataInicio.with(TemporalAdjusters.lastDayOfMonth());
        return repository.findByUsuarioIdAndDataBetween(id, dataInicio, dataFim);
    }

    public List<Transacao> findByUsuarioMesAno(Integer id, int mes, int ano) throws ValidacaoException {
        validarRequest(id, mes, ano);
        var transacoes = findTransacao(id, mes, ano);
        return ObjectUtils.isEmpty(transacoes)
            ? gerarTransacoes(id, ano, mes)
            : transacoes;
    }


    private void validarRequest(Integer id, int mes, int ano) throws ValidacaoException {
        if (ObjectUtils.isEmpty(id) || ObjectUtils.isEmpty(mes) || ObjectUtils.isEmpty(ano)) {
            throw new ValidacaoException("Todos campos devem estar populados.");
        }
        validarId(id);
        validarMes(mes);
        validarAno(ano);
    }

    private void validarAno(int ano) throws ValidacaoException {
        if (ano <= 0) {
            throw new ValidacaoException("Ano não pode ser número negativo ou zero");
        }
    }


    private void validarMes(int mes) throws ValidacaoException {
        if (mes <= 0 || mes > 12) {
            throw new ValidacaoException("Mês do ano está inválido, favor selecionar de 1 a 12");
        }
    }

    private void validarId(Integer id) throws ValidacaoException {
        if (id < 1000 || id > 100000000) {
            throw new ValidacaoException("Id do usuario esta invalido, o valor deve estar entre 1.000 a 100.000.000");
        }
    }
}
