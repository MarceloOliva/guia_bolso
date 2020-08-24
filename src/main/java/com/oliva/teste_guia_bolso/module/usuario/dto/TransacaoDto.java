package com.oliva.teste_guia_bolso.module.usuario.dto;

import com.oliva.teste_guia_bolso.module.transacao.model.Transacao;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.BeanUtils;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class TransacaoDto {
    private String descricao;
    private LocalDateTime data;
    private Integer valor;

    public static TransacaoDto of(Transacao transacao) {
        var response = new TransacaoDto();
        BeanUtils.copyProperties(transacao, response);
        return response;
    }
}


