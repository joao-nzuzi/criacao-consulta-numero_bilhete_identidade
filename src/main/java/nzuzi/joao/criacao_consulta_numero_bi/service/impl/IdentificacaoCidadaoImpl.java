package nzuzi.joao.criacao_consulta_numero_bi.service.impl;

import nzuzi.joao.criacao_consulta_numero_bi.config.exception.PessoaNotFoundExceptionHandler;
import nzuzi.joao.criacao_consulta_numero_bi.dto.GeradorBilheteDTO;
import nzuzi.joao.criacao_consulta_numero_bi.model.BilheteIdentidade;
import nzuzi.joao.criacao_consulta_numero_bi.model.Cidadao;
import nzuzi.joao.criacao_consulta_numero_bi.repository.CidadaoRepository;
import nzuzi.joao.criacao_consulta_numero_bi.service.ICidadao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static nzuzi.joao.criacao_consulta_numero_bi.specification.CidadaoSpecification.hasBINumber;
import static nzuzi.joao.criacao_consulta_numero_bi.specification.CidadaoSpecification.spec;
import static nzuzi.joao.criacao_consulta_numero_bi.util.BIUtils.generateBINumber;

@Service
public class CidadaoCidadaoImpl implements ICidadao {

    @Autowired
    private CidadaoRepository cidadaoRepository;

    @Override
    public String gerarNumeroBilhete(Cidadao cidadao) {
        cidadao.setIdCidadao(UUID.randomUUID());
        String numeroBilhete = generateBINumber(cidadao.getEndereco().getProvincia());
        BilheteIdentidade bilheteIdentidade = settingBilheteIdentidadeValues(numeroBilhete);
        cidadao.setBilheteIdentidade(bilheteIdentidade);
        //TODO: Passar a responsabilidade desta validação na classe de validação dos dados de entrada
        if (LocalDate.now().getYear() - bilheteIdentidade.getCidadao().getDataNascimento().getYear() < 6) {
            return "Não possui idade para tratar bilhete de identidade";
        } else{
            cidadaoRepository.save(cidadao);
        }

        return cidadaoRepository.findOne(hasBINumber(numeroBilhete))
                .map(Cidadao::getNumeroBilhete)
                .orElse(String.valueOf(new PessoaNotFoundExceptionHandler(numeroBilhete)));
    }

    private BilheteIdentidade settingBilheteIdentidadeValues(String numeroBilhete) {
        BilheteIdentidade bilheteIdentidade = new BilheteIdentidade();
        bilheteIdentidade.setNumeroBilhete(numeroBilhete);
        bilheteIdentidade.setDataValidade(setDataValidadeBilhete(bilheteIdentidade));
        bilheteIdentidade.setDataEmissao(LocalDate.now());
        return bilheteIdentidade;
    }

    @Override
    public List<GeradorBilheteDTO> findPessoas(String nome, String telefone, String provincia, String municipio, String comuna) {
        return cidadaoRepository.findAll(spec(nome, telefone, provincia, municipio, comuna))
                .stream()
                .map(GeradorBilheteDTO::convertToGeradorBilheteDTO)
                .collect(Collectors.toList());
    }


    private String setDataValidadeBilhete(BilheteIdentidade bilheteIdentidade) {
        int idade = LocalDate.now().getYear() - bilheteIdentidade.getCidadao().getDataNascimento().getYear();
        String validadeBilhete;
        if(idade >= 6 && idade <= 10){
            validadeBilhete = String.valueOf(LocalDate.now().plusYears(10));
        }else if(idade > 10 && idade <= 60){
            validadeBilhete = String.valueOf(LocalDate.now().plusYears(15));
        }else{
            validadeBilhete = "VITALÍCIO";
        }
        return validadeBilhete;
    }

}
