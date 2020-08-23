package com.oliva.teste_guia_bolso.module.transacao.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.oliva.teste_guia_bolso.module.comum.util.NumeroUtil;
import com.oliva.teste_guia_bolso.module.usuario.model.Usuario;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transacao {
    @Id
    @SequenceGenerator(name = "SEQ_TRANSACAO", initialValue = 1000, sequenceName = "SEQ_TRANSACAO", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "SEQ_TRANSACAO")
    private Integer id;
    //todo: max: 120 min:10
    private String descricao;

    private Integer valor;

    private LocalDateTime data;
    @ManyToOne()
    @JoinColumn(name = "FK_USUARIO", referencedColumnName = "ID",
        foreignKey = @ForeignKey(name = "FK_USUARIO_TRANSACAO"))
    private Usuario usuario;

    private static final int VALOR_INICIAL = -9999999;

    private static final int VALOR_FINAL = 9999999;

    public static Transacao gerarTransacao(Usuario usuario, LocalDateTime data) {
        return Transacao.builder()
            .data(data)
            .descricao("aaaaaa")
            .usuario(usuario)
            .valor(NumeroUtil.gerarNumeroRandomComRangeEntre(VALOR_INICIAL, VALOR_FINAL))
            .build();
    }
}
