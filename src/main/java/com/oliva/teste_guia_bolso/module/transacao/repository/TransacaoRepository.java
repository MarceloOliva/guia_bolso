package com.oliva.teste_guia_bolso.module.transacao.repository;

import com.oliva.teste_guia_bolso.module.transacao.model.Transacao;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface TransacaoRepository extends CrudRepository<Transacao, Integer> {
    List<Transacao> findByUsuarioIdAndDataBetween(Integer id, LocalDateTime dataInicio, LocalDateTime dataFim);
}
