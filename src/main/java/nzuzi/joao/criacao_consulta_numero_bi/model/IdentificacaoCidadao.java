package nzuzi.joao.criacao_consulta_numero_bi.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "tb_pessoa")
@Data

public class Cidadao implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    @JsonIgnore
    private UUID idCidadao;
    private String nomeCompleto;
    @NotNull(message = "O campo gênero é obrigatório")
    private String genero;
    @NotNull(message = "O campo estado civil é obrigatório")
    private String estadoCivil;
    private String nomePai;
    private String nomeMae;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    @NotNull(message = "O campo data de nascimento é obrigatório")
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
    private BilheteIdentidade bilheteIdentidade;

    public Cidadao(){}
}
