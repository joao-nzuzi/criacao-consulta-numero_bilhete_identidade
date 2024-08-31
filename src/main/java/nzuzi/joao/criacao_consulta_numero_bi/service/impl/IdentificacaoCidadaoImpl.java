package nzuzi.joao.criacao_consulta_numero_bi.service.impl;

import nzuzi.joao.criacao_consulta_numero_bi.config.exception.CidadaoNotFoundExceptionHandler;
import nzuzi.joao.criacao_consulta_numero_bi.dto.IdentificacaoCidadaoDTO;
import nzuzi.joao.criacao_consulta_numero_bi.model.BilheteIdentidade;
import nzuzi.joao.criacao_consulta_numero_bi.model.IdentificacaoCidadao;
import nzuzi.joao.criacao_consulta_numero_bi.repository.IdentificacaoCidadaoRepository;
import nzuzi.joao.criacao_consulta_numero_bi.service.IIdentificacaoCidadao;
import nzuzi.joao.criacao_consulta_numero_bi.util.BIUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import static nzuzi.joao.criacao_consulta_numero_bi.specification.IdentificacaoCidadaoSpecification.hasBINumber;
import static nzuzi.joao.criacao_consulta_numero_bi.specification.IdentificacaoCidadaoSpecification.spec;
import static nzuzi.joao.criacao_consulta_numero_bi.util.BIUtils.generateBINumber;

@Service
public class IdentificacaoCidadaoImpl implements IIdentificacaoCidadao {

    @Autowired
    private IdentificacaoCidadaoRepository identificacaoCidadaoRepository;

    @Override
    public String generateBINumber(IdentificacaoCidadao identificacaoCidadao) {
        identificacaoCidadao.setIdCidadao(UUID.randomUUID());
        String numeroBilhete = BIUtils.generateBINumber(identificacaoCidadao.getEndereco().getProvincia());
        BilheteIdentidade bilheteIdentidade = settingBilheteIdentidadeValues(numeroBilhete, identificacaoCidadao);
        identificacaoCidadao.setBilheteIdentidade(bilheteIdentidade);
        //TODO: Passar a responsabilidade desta validação na classe de validação dos dados de entrada
        if ((ChronoUnit.YEARS.between(identificacaoCidadao.getDataNascimento(), LocalDate.now()) < 6)) {
            return "Não possui idade para tratar bilhete de identidade";
        } else{
            identificacaoCidadaoRepository.save(identificacaoCidadao);
        }

        return identificacaoCidadaoRepository.findOne(hasBINumber(numeroBilhete))
                .map(citizen -> citizen.getBilheteIdentidade().getNumeroBilhete())
                .orElse(String.valueOf(new CidadaoNotFoundExceptionHandler(numeroBilhete)));
    }

    private BilheteIdentidade settingBilheteIdentidadeValues(String numeroBilhete, IdentificacaoCidadao cidadao) {
        BilheteIdentidade bilheteIdentidade = new BilheteIdentidade();
        bilheteIdentidade.setNumeroBilhete(numeroBilhete);
        bilheteIdentidade.setDataValidade(setDataValidadeBilhete(cidadao));
        bilheteIdentidade.setDataEmissao(LocalDate.now());
        return bilheteIdentidade;
    }

    @Override
    public List<IdentificacaoCidadaoDTO> findCitizens(String nome, String numeroBilhete, String telefone, String provincia, String municipio, String comuna) {
        try {
            return identificacaoCidadaoRepository.findAll(spec(nome, numeroBilhete, telefone, provincia, municipio, comuna))
                    .stream()
                    .map(IdentificacaoCidadaoDTO::convertToGeradorBilheteDTO)
                    .collect(Collectors.toList());
        }catch (Exception exception){
            throw new CidadaoNotFoundExceptionHandler(numeroBilhete);
        }
    }


    private String setDataValidadeBilhete(IdentificacaoCidadao cidadao) {
        int idade = LocalDate.now().getYear() - cidadao.getDataNascimento().getYear();
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
