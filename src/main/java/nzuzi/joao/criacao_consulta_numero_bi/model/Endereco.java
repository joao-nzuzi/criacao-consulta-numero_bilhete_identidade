package nzuzi.joao.criacao_consulta_numero_bi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import lombok.Data;

@Entity
@Table(name = "tb_endereco")
@Data
public class Endereco {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore @Column(nullable = false)
    private int idEndereco;
    @Column(nullable = false)
    private String provincia;
    @Column(nullable = false)
    private String municipio;
    @Column(nullable = false)
    private String comuna;
    private String nomeCondominio;
    private String nomeBairro;
    private String numeroRua;
    private String numeroCasa;
    private String numeroApartamento;
    private String numeroAndar;
    private String quarteirao;

}
