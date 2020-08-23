package com.oliva.teste_guia_bolso.module.usuario.model;

import com.oliva.teste_guia_bolso.module.transacao.model.Transacao;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Usuario {
    @Id
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    public Usuario(Integer id) {
        this.id = id;
    }
}
