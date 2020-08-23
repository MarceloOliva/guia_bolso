package com.oliva.teste_guia_bolso.module.transacao.service;

import com.oliva.teste_guia_bolso.module.transacao.model.Transacao;
import com.oliva.teste_guia_bolso.module.transacao.repository.TransacaoRepository;
import com.oliva.teste_guia_bolso.module.usuario.model.Usuario;
import com.oliva.teste_guia_bolso.module.usuario.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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

    public List<Transacao> findByUsuarioMesAno(Integer id, int mes, int ano) {
        var transacoes = findTransacao(id, mes, ano);
        return ObjectUtils.isEmpty(transacoes)
            ? gerarTransacoes(id, ano, mes)
            : transacoes;
    }
}
