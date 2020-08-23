package com.oliva.teste_guia_bolso.module.usuario.controller;

import com.oliva.teste_guia_bolso.module.usuario.dto.TransacaoDto;
import com.oliva.teste_guia_bolso.module.usuario.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/usuario")
public class UsuarioController {
    @Autowired
    private UsuarioService service;

    @GetMapping("/{id}/transacoes/{ano}/{mes}")
    public List<TransacaoDto> findTransacoesDoUsuarioIdByAnoMes(@PathVariable Integer id,
                                                                @PathVariable int ano,
                                                                @PathVariable int mes) {
        return service.findTransacoesDoUsuarioIdByAnoMes(id, ano, mes);
    }
}
