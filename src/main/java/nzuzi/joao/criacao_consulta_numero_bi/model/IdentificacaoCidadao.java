package nzuzi.joao.criacao_consulta_numero_bi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_pessoa")
@Data

public class IdentificacaoCidadao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @JsonIgnore
    private UUID idCidadao;

    @NotNull(message = "O nome completo é obrigatório")
    private String nomeCompleto;

    @NotNull(message = "O gênero é obrigatório")
    private String genero;

    @NotNull(message = "O estado civil é obrigatório")
    private String estadoCivil;

    @NotNull(message = "O nome do pai é obrigatório")
    private String nomePai;

    @NotNull(message = "O nome da mãe é obrigatório")
    private String nomeMae;

    @NotNull(message = "A data de nascimento é obrigatório")
    private LocalDate dataNascimento;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_endereco")
    @NotNull(message = "O endereço é obrigatório")
    private Endereco endereco;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "id_pessoa")
    @NotNull(message = "O contacto é obrigatório")
    private List<Contacto> contactos;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_bilhete", referencedColumnName = "idBilhete")
    @JsonIgnore
    private BilheteIdentidade bilheteIdentidade;

    public IdentificacaoCidadao(){}

    public IdentificacaoCidadao(String nomeCompleto, LocalDate dataNascimento){
        this.nomeCompleto = nomeCompleto;
        this.dataNascimento = dataNascimento;
    }

    public int getIdade(){
        return (int) ChronoUnit.YEARS.between(this.dataNascimento, LocalDate.now());
    }
}
