package nzuzi.joao.criacao_consulta_numero_bi.specification;

import nzuzi.joao.criacao_consulta_numero_bi.model.BilheteIdentidade;
import nzuzi.joao.criacao_consulta_numero_bi.model.Contacto;
import nzuzi.joao.criacao_consulta_numero_bi.model.Endereco;
import nzuzi.joao.criacao_consulta_numero_bi.model.IdentificacaoCidadao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.Objects;

public class IdentificacaoCidadaoSpecification {

    public static Specification<IdentificacaoCidadao> hasNameLike(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.<String>get("nomeCompleto"), "%" + nome + "%");
    }

    public static Specification<IdentificacaoCidadao> hasBINumber(String biNumber) {
        return (root, query, criteriaBuilder) ->{
            Join<BilheteIdentidade, IdentificacaoCidadao> cidadaoBilhete = root.join("bilheteIdentidade");
            return criteriaBuilder.equal(cidadaoBilhete.get("numeroBilhete"), biNumber);
        };
    }

    public static Specification<IdentificacaoCidadao> getPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) -> {
            Join<Contacto, IdentificacaoCidadao> cidadaoContacto = root.join("contactos");
            return criteriaBuilder.equal(cidadaoContacto.get("numeroTelefone"), phoneNumber);
        };
    }

    public static Specification<IdentificacaoCidadao> getProvince(String province) {
        return (root, query, criteriaBuilder) -> {
            Join<Endereco, IdentificacaoCidadao> cidadaoProvincia = root.join("endereco");
            return criteriaBuilder.equal(cidadaoProvincia.get("provincia"), province);
        };
    }

    public static Specification<IdentificacaoCidadao> getMunicipality(String municipality) {
        return (root, query, criteriaBuilder) -> {
            Join<Endereco, IdentificacaoCidadao> cidadaoMunicipio = root.join("endereco");
            return criteriaBuilder.equal(cidadaoMunicipio.get("municipio"), municipality);
        };
    }

    public static Specification<IdentificacaoCidadao> getComune(String comune) {
        return (root, query, criteriaBuilder) -> {
            Join<Endereco, IdentificacaoCidadao> cidadaoComuna = root.join("endereco");
            return criteriaBuilder.equal(cidadaoComuna.get("comuna"), comune);
        };
    }

    public static Specification<IdentificacaoCidadao> spec(String nome, String numeroBilhete, String telefone, String provincia, String municipio, String comune) {
        Specification<IdentificacaoCidadao> spec = null;

        spec = getNomeSpecification(nome, spec);
        spec = getNumeroBilheteSpecification(numeroBilhete, spec);
        spec = getTelefoneSpecification(telefone, spec);
        spec = getProvinciaSpecification(provincia, spec);
        spec = getMunicipioSpecification(municipio, spec);
        spec = getComunaSpecification(comune, spec);


        return spec;
    }

    private static Specification<IdentificacaoCidadao> getMunicipioSpecification(String municipio, Specification<IdentificacaoCidadao> spec) {
        if(StringUtils.isNotEmpty(municipio) && Objects.isNull(spec)){
            spec = Specification.where(getMunicipality(municipio));
        }else if(StringUtils.isNotEmpty(municipio) && Objects.nonNull(spec)){
            spec = spec.and(getMunicipality(municipio));
        }
        return spec;
    }

    private static Specification<IdentificacaoCidadao> getProvinciaSpecification(String provincia, Specification<IdentificacaoCidadao> spec) {
        if(StringUtils.isNotEmpty(provincia) && Objects.isNull(spec)){
            spec = Specification.where(getProvince(provincia));
        }else if(StringUtils.isNotEmpty(provincia) && Objects.nonNull(spec)){
            spec = spec.and(getProvince(provincia));
        }
        return spec;
    }

    private static Specification<IdentificacaoCidadao> getComunaSpecification(String comune, Specification<IdentificacaoCidadao> spec) {
        if(StringUtils.isNotEmpty(comune) && Objects.isNull(spec)){
            spec = Specification.where(getComune(comune));
        }else if(StringUtils.isNotEmpty(comune) && Objects.nonNull(spec)){
            spec = spec.and(getComune(comune));
        }
        return spec;
    }

    private static Specification<IdentificacaoCidadao> getTelefoneSpecification(String telefone, Specification<IdentificacaoCidadao> spec) {
        if(StringUtils.isNotEmpty(telefone) && Objects.isNull(spec)){
            spec = Specification.where(getPhoneNumber(telefone));
        }else if(StringUtils.isNotEmpty(telefone) && Objects.nonNull(spec)){
            spec = spec.and(getPhoneNumber(telefone));
        }
        return spec;
    }

    private static Specification<IdentificacaoCidadao> getNumeroBilheteSpecification(String numeroBilhete, Specification<IdentificacaoCidadao> spec) {
        if(StringUtils.isNotEmpty(numeroBilhete) && Objects.isNull(spec)){
            spec = Specification.where(hasBINumber(numeroBilhete));
        }else if(StringUtils.isNotEmpty(numeroBilhete) && Objects.nonNull(spec)){
            spec = spec.and(hasNameLike(numeroBilhete));
        }
        return spec;
    }

    private static Specification<IdentificacaoCidadao> getNomeSpecification(String nome, Specification<IdentificacaoCidadao> spec) {
        if(StringUtils.isNotEmpty(nome)){
            spec = Specification.where(hasNameLike(nome));
        }
        return spec;
    }
}
