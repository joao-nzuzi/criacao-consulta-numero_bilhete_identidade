package nzuzi.joao.criacao_consulta_numero_bi.service.impl;

import nzuzi.joao.criacao_consulta_numero_bi.dto.PessoaDTO;
import nzuzi.joao.criacao_consulta_numero_bi.model.Pessoa;
import nzuzi.joao.criacao_consulta_numero_bi.repository.PessoaRepository;
import nzuzi.joao.criacao_consulta_numero_bi.service.IPessoa;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static nzuzi.joao.criacao_consulta_numero_bi.specification.PessoaSpecification.*;
import static nzuzi.joao.criacao_consulta_numero_bi.util.BIUtils.generateBINumber;

@Service
public class PessoaImpl implements IPessoa {

    @Autowired
    private PessoaRepository pessoaRepository;

    @Override
    public String gerarNumeroBilhete(Pessoa pessoa) {
        pessoa.setIdPessoa(UUID.randomUUID());
        String numeroBilhete = generateBINumber(pessoa.getEndereco().getProvincia());
        pessoa.setNumeroBilhete(numeroBilhete);
        setDataValidadeBilhete(pessoa);
        pessoa.setDataEmissao(LocalDate.now());
        //TODO: Passar a responsabilidade desta validação na classe de validação dos dados de entrada
        if (LocalDate.now().getYear() - pessoa.getDataNascimento().getYear() < 6) {
            return "Não possui idade para tratar bilhete de identidade";
        } else{
            pessoaRepository.save(pessoa);
        }

        return pessoaRepository.findOne(hasBINumber(numeroBilhete))
                .map(Pessoa::getNumeroBilhete)
                .orElse(null);
    }

    @Override
    public List<PessoaDTO> findPessoas(String nome, String numeroBilhete, String telefone, String provincia, String municipio, String comuna) {
        return pessoaRepository.findAll(spec(nome, numeroBilhete, telefone, provincia, municipio, comuna))
                .stream()
                .map(PessoaDTO::convertToPessoaDTO)
                .collect(Collectors.toList());
    }


    private void setDataValidadeBilhete(Pessoa pessoa) {
        int idade = LocalDate.now().getYear() - pessoa.getDataNascimento().getYear();
        if(idade >= 6 && idade <= 10){
            pessoa.setDataValidade(String.valueOf(LocalDate.now().plusYears(10)));
        }else if(idade > 10 && idade <= 60){
            pessoa.setDataValidade(String.valueOf(LocalDate.now().plusYears(15)));
        }else{
            pessoa.setDataValidade("VITALÍCIO");
        }
    }

}
