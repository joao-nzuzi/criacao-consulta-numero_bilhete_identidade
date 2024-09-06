package nzuzi.joao.criacao_consulta_numero_bi.validation;

import nzuzi.joao.criacao_consulta_numero_bi.config.exception.CidadaoException;
import nzuzi.joao.criacao_consulta_numero_bi.model.IdentificacaoCidadao;
import org.apache.commons.lang3.StringUtils;

import java.util.Objects;

public class CidadaoValidations {

    public static void fieldsMandatory(IdentificacaoCidadao cidadao){
        validateNomeCompleto(cidadao);
        validateNomePai(cidadao);
        validateNomeMae(cidadao);
        validateGenero(cidadao);
        validateEstadoCivil(cidadao);
        validateDataNascimento(cidadao);
    }

    private static void validateNomeCompleto(IdentificacaoCidadao cidadao) {
        if(StringUtils.isEmpty(cidadao.getNomeCompleto())){
            throw new CidadaoException("O nome completo é obrigatório");
        }
    }

    private static void validateNomePai(IdentificacaoCidadao cidadao){
        if(StringUtils.isEmpty(cidadao.getNomePai())){
            throw new CidadaoException("O nome do pai é obrigatório");
        }
    }

    private static void validateNomeMae(IdentificacaoCidadao cidadao){
        if(StringUtils.isEmpty(cidadao.getNomeMae())){
            throw new CidadaoException("O nome da mãe é obrigatório");
        }
    }

    private static void validateGenero(IdentificacaoCidadao cidadao){
        if(StringUtils.isEmpty(cidadao.getGenero())){
            throw new CidadaoException("O gênero é obrigatório");
        }
    }

    private static void validateEstadoCivil(IdentificacaoCidadao cidadao){
        if(StringUtils.isEmpty(cidadao.getEstadoCivil())){
            throw new CidadaoException("O estado civil é obrigatório");
        }
    }

    private static void validateDataNascimento(IdentificacaoCidadao cidadao){
        if(Objects.isNull(cidadao.getDataNascimento())){
            throw new CidadaoException("Nascimento é obrigatório");
        }
    }
}
