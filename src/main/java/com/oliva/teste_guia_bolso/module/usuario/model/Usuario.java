package com.oliva.teste_guia_bolso.module.usuario.model;

import com.oliva.teste_guia_bolso.module.transacao.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class Usuario {
    @Id
    @SequenceGenerator(name = "SEQ_USUARIO", initialValue = 1000, sequenceName = "SEQ_USUARIO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_USUARIO")
    private Integer id;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario", cascade = CascadeType.ALL)
    private List<Transacao> transacoes;

    public Usuario(Integer id) {
        this.id = id;
    }
}
