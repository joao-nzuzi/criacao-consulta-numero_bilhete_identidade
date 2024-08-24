package nzuzi.joao.criacao_consulta_numero_bi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import lombok.Data;

import java.util.UUID;

@Entity
@Table(name = "tb_endereco")
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore @Column(nullable = false)
    private UUID idEndereco;
    @Column(nullable = false)
    @NotNull(message = "O campo provincia é obrigatório")
    private String provincia;
    @NotNull(message = "O campo município é obrigatório")
    @Column(nullable = false)
    private String municipio;
    @Column(nullable = false)
    @NotNull(message = "O campo comuna é obrigatório")
    private String comuna;
    private String nomeCondominio;
    private String nomeBairro;
    private String numeroRua;
    private String numeroCasa;
    private String numeroApartamento;
    private String numeroAndar;
    private String quarteirao;

}
