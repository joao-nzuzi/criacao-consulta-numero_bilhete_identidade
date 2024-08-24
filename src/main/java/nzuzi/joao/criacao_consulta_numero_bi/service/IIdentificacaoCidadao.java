package nzuzi.joao.criacao_consulta_numero_bi.service;

import nzuzi.joao.criacao_consulta_numero_bi.dto.GeradorBilheteDTO;
import nzuzi.joao.criacao_consulta_numero_bi.model.BilheteIdentidade;
import nzuzi.joao.criacao_consulta_numero_bi.model.Cidadao;

import java.util.List;

public interface ICidadao {
    String gerarNumeroBilhete(Cidadao cidadao);
    List<GeradorBilheteDTO> findPessoas(String nome, String telefone, String provincia, String municipio, String comuna);
}
