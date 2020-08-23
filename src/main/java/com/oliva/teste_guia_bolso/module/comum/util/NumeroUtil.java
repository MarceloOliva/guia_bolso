package com.oliva.teste_guia_bolso.module.comum.util;

import com.oliva.teste_guia_bolso.module.comum.exception.ValidacaoException;

import java.util.Random;

public class NumeroUtil {

    public static Integer gerarNumeroRandomComRangeEntre(int min, Integer max){
        return new Random().ints(min,(max+1))
            .findFirst()
            .orElse(0);
    }
}
