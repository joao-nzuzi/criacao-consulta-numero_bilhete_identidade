package nzuzi.joao.criacao_consulta_numero_bi.repository;

import nzuzi.joao.criacao_consulta_numero_bi.model.BilheteIdentidade;
import nzuzi.joao.criacao_consulta_numero_bi.model.Cidadao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface CidadaoRepository extends JpaRepository<Cidadao, UUID>, JpaSpecificationExecutor<Cidadao> {
}
