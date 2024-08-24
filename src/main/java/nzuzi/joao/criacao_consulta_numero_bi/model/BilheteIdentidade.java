package nzuzi.joao.criacao_consulta_numero_bi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "tb_bilhete_identidade")
@Data
public class BilheteIdentidade implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false) @JsonIgnore
    private UUID idBilhete;
    private String numeroBilhete;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataEmissao;
    private String dataValidade;
}
