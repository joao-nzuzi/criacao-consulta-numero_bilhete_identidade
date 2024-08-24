package nzuzi.joao.criacao_consulta_numero_bi.repository;

import nzuzi.joao.criacao_consulta_numero_bi.model.IdentificacaoCidadao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.UUID;

public interface IdentificacaoCidadaoRepository extends JpaRepository<IdentificacaoCidadao, UUID>, JpaSpecificationExecutor<IdentificacaoCidadao> {
}
