package com.oliva.teste_guia_bolso.module.transacao.model;

import com.oliva.teste_guia_bolso.module.comum.util.NumeroUtil;
import com.oliva.teste_guia_bolso.module.usuario.model.Usuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.RandomStringUtils;

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
            .descricao(RandomStringUtils.randomAlphabetic(NumeroUtil.gerarNumeroRandomComRangeEntre(20, 120)))
            .usuario(usuario)
            .valor(NumeroUtil.gerarNumeroRandomComRangeEntre(VALOR_INICIAL, VALOR_FINAL))
            .build();
    }
}
