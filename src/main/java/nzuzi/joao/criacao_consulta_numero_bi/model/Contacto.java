package nzuzi.joao.criacao_consulta_numero_bi.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "tb_contactos")
@Data
public class Contacto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore @Column(nullable = false)
    private int idContacto;
    private String tipoTelefone;
    private String numeroTelefone;
    private String email;
}
