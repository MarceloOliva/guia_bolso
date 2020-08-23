package com.oliva.teste_guia_bolso.module.usuario.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class UsuarioControllerTest {
    private static final String END_POINT_CARTEIRA = "/api/usuario/";

    @Autowired
    private MockMvc mvc;

    @Test
    public void deveResgatarOsdadosDeAcordoComOContrato_semErros() throws Exception {

        mvc.perform(get(END_POINT_CARTEIRA.concat("/10/transacoes/2020/5"))
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$", hasSize(4)));
    }
}