package com.oliva.teste_guia_bolso.usuario.controller;

import com.oliva.teste_guia_bolso.transacao.dto.TransacaoDto;
import com.oliva.teste_guia_bolso.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/{id}/transacoes/{ano}/{mes}")
    public TransacaoDto findTransacoesDoUsuarioIdByAnoMes(@PathVariable Integer id, @PathVariable int ano, @PathVariable String mes) {
        return service.findTransacoesDoUsuarioIdByAnoMes(id, ano, mes);
    }
}
