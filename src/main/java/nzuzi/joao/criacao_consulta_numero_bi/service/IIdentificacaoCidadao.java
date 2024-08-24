package nzuzi.joao.criacao_consulta_numero_bi.service;

import nzuzi.joao.criacao_consulta_numero_bi.dto.IdentificacaoCidadaoDTO;
import nzuzi.joao.criacao_consulta_numero_bi.model.IdentificacaoCidadao;

import java.util.List;

public interface IIdentificacaoCidadao {
    String generateBINumber(IdentificacaoCidadao identificacaoCidadao);
    List<IdentificacaoCidadaoDTO> findCitizens(String nome, String numeroBilhete, String telefone, String provincia, String municipio, String comuna);
}
