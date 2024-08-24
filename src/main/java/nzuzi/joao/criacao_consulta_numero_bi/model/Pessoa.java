package nzuzi.joao.criacao_consulta_numero_bi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_pessoa")
@Data

public class Pessoa implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false) @JsonIgnore
    private UUID idPessoa;
    @JsonIgnore
    private String numeroBilhete;
    private String nomeCompleto;
    private String genero;
    private String estadoCivil;
    private String nomePai;
    private String nomeMae;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataNascimento;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    private Endereco endereco;
    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa")
    private List<Contacto> contactos;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataEmissao;
    private String dataValidade;

    public Pessoa(){}
}
