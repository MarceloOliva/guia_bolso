package com.oliva.teste_guia_bolso.module.usuario.model;

import com.oliva.teste_guia_bolso.module.transacao.model.Transacao;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Usuario {

    private static final int INICIO_MES = 1;
    private static final int RAGE_DATA_FINAL = 5;

    @Id
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    public Usuario(Integer id) {
        this.id = id;
    }

    public static Usuario criarTransacoes(Integer usuarioId, int ano, int mes) {
        var usuario = new Usuario(usuarioId);
        var transacoes = IntStream.range(INICIO_MES, RAGE_DATA_FINAL)
            .mapToObj(i -> Transacao.gerarTransacao(usuario, LocalDate.of(ano, mes, i).atStartOfDay()))
            .collect(Collectors.toList());
        usuario.setTransacoes(transacoes);
        return usuario;
    }
}
