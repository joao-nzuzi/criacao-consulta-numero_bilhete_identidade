package nzuzi.joao.criacao_consulta_numero_bi.model;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;
class IdentificacaoCidadaoTest {

    @Test
    void temIdadeParaTerBilheteDeIdentidade() {
        IdentificacaoCidadao cidadao = new IdentificacaoCidadao("Joao", LocalDate.of(2018, 1, 1));
        Assertions.assertEquals(6, cidadao.getIdade());
    }
}