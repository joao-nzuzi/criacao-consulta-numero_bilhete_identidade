package nzuzi.joao.criacao_consulta_numero_bi.specification;

import nzuzi.joao.criacao_consulta_numero_bi.model.BilheteIdentidade;
import nzuzi.joao.criacao_consulta_numero_bi.model.Contacto;
import nzuzi.joao.criacao_consulta_numero_bi.model.Endereco;
import nzuzi.joao.criacao_consulta_numero_bi.model.Cidadao;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import java.util.Objects;

public class CidadaoSpecification {

    public static Specification<Cidadao> hasNameLike(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.<String>get("nomeCompleto"), "%" + nome + "%");
    }

    public static Specification<Cidadao> hasBINumber(String biNumber) {
        return (root, query, criteriaBuilder) ->{
            Join<BilheteIdentidade, Cidadao> cidadaoBilhete = root.join("bilheteIdentidade");
            return criteriaBuilder.equal(cidadaoBilhete.get("numeroBilhete"), biNumber);
        };
    }

    public static Specification<Cidadao> getPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) -> {
            Join<Contacto, Cidadao> cidadaoContacto = root.join("contactos");
            return criteriaBuilder.equal(cidadaoContacto.get("numeroTelefone"), phoneNumber);
        };
    }

    public static Specification<Cidadao> getProvince(String province) {
        return (root, query, criteriaBuilder) -> {
            Join<Endereco, Cidadao> cidadaoProvincia = root.join("endereco");
            return criteriaBuilder.equal(cidadaoProvincia.get("provincia"), province);
        };
    }

    public static Specification<Cidadao> getMunicipality(String municipality) {
        return (root, query, criteriaBuilder) -> {
            Join<Endereco, Cidadao> cidadaoMunicipio = root.join("endereco");
            return criteriaBuilder.equal(cidadaoMunicipio.get("municipio"), municipality);
        };
    }

    public static Specification<Cidadao> getComune(String comune) {
        return (root, query, criteriaBuilder) -> {
            Join<Endereco, Cidadao> cidadaoComuna = root.join("endereco");
            return criteriaBuilder.equal(cidadaoComuna.get("comuna"), comune);
        };
    }

    public static Specification<Cidadao> spec(String nome, String telefone, String provincia, String municipio, String comune) {
        Specification<Cidadao> spec = null;

        spec = getNomeSpecification(nome, spec);
//        spec = getNumeroBilheteSpecification(numeroBilhete, spec);
        spec = getTelefoneSpecification(telefone, spec);
        spec = getProvinciaSpecification(provincia, spec);
        spec = getMunicipioSpecification(municipio, spec);
        spec = getComunaSpecification(comune, spec);


        return spec;
    }

    private static Specification<Cidadao> getMunicipioSpecification(String municipio, Specification<Cidadao> spec) {
        if(StringUtils.isNotEmpty(municipio) && Objects.isNull(spec)){
            spec = Specification.where(getMunicipality(municipio));
        }else if(StringUtils.isNotEmpty(municipio) && Objects.nonNull(spec)){
            spec = spec.and(getMunicipality(municipio));
        }
        return spec;
    }

    private static Specification<Cidadao> getProvinciaSpecification(String provincia, Specification<Cidadao> spec) {
        if(StringUtils.isNotEmpty(provincia) && Objects.isNull(spec)){
            spec = Specification.where(getProvince(provincia));
        }else if(StringUtils.isNotEmpty(provincia) && Objects.nonNull(spec)){
            spec = spec.and(getProvince(provincia));
        }
        return spec;
    }

    private static Specification<Cidadao> getComunaSpecification(String comune, Specification<Cidadao> spec) {
        if(StringUtils.isNotEmpty(comune) && Objects.isNull(spec)){
            spec = Specification.where(getComune(comune));
        }else if(StringUtils.isNotEmpty(comune) && Objects.nonNull(spec)){
            spec = spec.and(getComune(comune));
        }
        return spec;
    }

    private static Specification<Cidadao> getTelefoneSpecification(String telefone, Specification<Cidadao> spec) {
        if(StringUtils.isNotEmpty(telefone) && Objects.isNull(spec)){
            spec = Specification.where(getPhoneNumber(telefone));
        }else if(StringUtils.isNotEmpty(telefone) && Objects.nonNull(spec)){
            spec = spec.and(getPhoneNumber(telefone));
        }
        return spec;
    }

//    private static Specification<cidadao> getNumeroBilheteSpecification(String numeroBilhete, Specification<BilheteIdentidade> spec) {
//        if(StringUtils.isNotEmpty(numeroBilhete) && Objects.isNull(spec)){
//            spec = Specification.where(hasBINumber(numeroBilhete));
//        }else if(StringUtils.isNotEmpty(numeroBilhete) && Objects.nonNull(spec)){
//            spec = spec.and(hasNameLike(numeroBilhete));
//        }
//        return spec;
//    }

    private static Specification<Cidadao> getNomeSpecification(String nome, Specification<Cidadao> spec) {
        if(StringUtils.isNotEmpty(nome)){
            spec = Specification.where(hasNameLike(nome));
        }
        return spec;
    }
}
