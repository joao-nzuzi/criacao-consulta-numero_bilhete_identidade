package nzuzi.joao.criacao_consulta_numero_bi.specification;

import nzuzi.joao.criacao_consulta_numero_bi.model.Contacto;
import nzuzi.joao.criacao_consulta_numero_bi.model.Endereco;
import nzuzi.joao.criacao_consulta_numero_bi.model.Pessoa;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class PessoaSpecification {

    public static Specification<Pessoa> hasNameLike(String nome) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.<String>get("nomeCompleto"), "%" + nome + "%");
    }

    public static Specification<Pessoa> hasBINumber(String biNumber) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.equal(root.<String>get("numeroBilhete"), biNumber);
    }

    public static Specification<Pessoa> getPhoneNumber(String phoneNumber) {
        return (root, query, criteriaBuilder) -> {
            Join<Contacto, Pessoa> pessoaContacto = root.join("contactos");
            return criteriaBuilder.equal(pessoaContacto.get("numeroTelefone"), phoneNumber);
        };
    }

    public static Specification<Pessoa> getProvince(String province) {
        return (root, query, criteriaBuilder) -> {
            Join<Endereco, Pessoa> pessoasProvincia = root.join("endereco");
            return criteriaBuilder.equal(pessoasProvincia.get("provincia"), province);
        };
    }

    public static Specification<Pessoa> getMunicipality(String municipality) {
        return (root, query, criteriaBuilder) -> {
            Join<Endereco, Pessoa> pessoasMunicipio = root.join("endereco");
            return criteriaBuilder.equal(pessoasMunicipio.get("municipio"), municipality);
        };
    }

    public static Specification<Pessoa> getComune(String comune) {
        return (root, query, criteriaBuilder) -> {
            Join<Endereco, Pessoa> pessoasComuna = root.join("endereco");
            return criteriaBuilder.equal(pessoasComuna.get("comuna"), comune);
        };
    }

    public static Specification<Pessoa> spec(String nome, String numeroBilhete, String telefone, String provincia, String municipio, String comune) {
        Specification<Pessoa> spec = null;

        spec = getNomeSpecification(nome, spec);
        spec = getNumeroBilheteSpecification(numeroBilhete, spec);
        spec = getTelefoneSpecification(telefone, spec);
        spec = getProvinciaSpecification(provincia, spec);
        spec = getMunicipioSpecification(municipio, spec);
        spec = getComunaSpecification(comune, spec);


        return spec;
    }

    private static Specification<Pessoa> getMunicipioSpecification(String municipio, Specification<Pessoa> spec) {
        if(StringUtils.isNotEmpty(municipio) && Objects.isNull(spec)){
            spec = Specification.where(getMunicipality(municipio));
        }else if(StringUtils.isNotEmpty(municipio) && Objects.nonNull(spec)){
            spec = spec.and(getMunicipality(municipio));
        }
        return spec;
    }

    private static Specification<Pessoa> getProvinciaSpecification(String provincia, Specification<Pessoa> spec) {
        if(StringUtils.isNotEmpty(provincia) && Objects.isNull(spec)){
            spec = Specification.where(getProvince(provincia));
        }else if(StringUtils.isNotEmpty(provincia) && Objects.nonNull(spec)){
            spec = spec.and(getProvince(provincia));
        }
        return spec;
    }

    private static Specification<Pessoa> getComunaSpecification(String comune, Specification<Pessoa> spec) {
        if(StringUtils.isNotEmpty(comune) && Objects.isNull(spec)){
            spec = Specification.where(getComune(comune));
        }else if(StringUtils.isNotEmpty(comune) && Objects.nonNull(spec)){
            spec = spec.and(getComune(comune));
        }
        return spec;
    }

    private static Specification<Pessoa> getTelefoneSpecification(String telefone, Specification<Pessoa> spec) {
        if(StringUtils.isNotEmpty(telefone) && Objects.isNull(spec)){
            spec = Specification.where(getPhoneNumber(telefone));
        }else if(StringUtils.isNotEmpty(telefone) && Objects.nonNull(spec)){
            spec = spec.and(getPhoneNumber(telefone));
        }
        return spec;
    }

    private static Specification<Pessoa> getNumeroBilheteSpecification(String numeroBilhete, Specification<Pessoa> spec) {
        if(StringUtils.isNotEmpty(numeroBilhete) && Objects.isNull(spec)){
            spec = Specification.where(hasBINumber(numeroBilhete));
        }else if(StringUtils.isNotEmpty(numeroBilhete) && Objects.nonNull(spec)){
            spec = spec.and(hasNameLike(numeroBilhete));
        }
        return spec;
    }

    private static Specification<Pessoa> getNomeSpecification(String nome, Specification<Pessoa> spec) {
        if(StringUtils.isNotEmpty(nome)){
            spec = Specification.where(hasNameLike(nome));
        }
        return spec;
    }
}
