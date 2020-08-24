package com.oliva.teste_guia_bolso.module.usuario.service;

import com.oliva.teste_guia_bolso.module.comum.exception.ValidacaoException;
import com.oliva.teste_guia_bolso.module.transacao.service.TransacaoService;
import com.oliva.teste_guia_bolso.module.usuario.dto.TransacaoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioService {
    @Autowired
    private TransacaoService service;

    public List<TransacaoDto> findTransacoesDoUsuarioIdByAnoMes(Integer id, int ano, int mes) throws ValidacaoException {
        return service.findByUsuarioMesAno(id, mes, ano)
            .stream()
            .map(TransacaoDto::of)
            .collect(Collectors.toList());
    }
}
