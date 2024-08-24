package nzuzi.joao.criacao_consulta_numero_bi.service;

import nzuzi.joao.criacao_consulta_numero_bi.dto.PessoaDTO;
import nzuzi.joao.criacao_consulta_numero_bi.model.Pessoa;

import java.util.List;

public interface IPessoa {
    String gerarNumeroBilhete(Pessoa pessoa);
    List<PessoaDTO> findPessoas(String nome, String numeroBilhete, String telefone, String provincia, String municipio, String comuna);
}
