package nzuzi.joao.criacao_consulta_numero_bi.model;

import lombok.Getter;
import lombok.Setter;

public class Provincia {

    @Getter
    @Setter
    public String codigo;

    @Getter
    @Setter
    public String nome;

    public Provincia(String codigo, String nome){
        this.codigo = codigo;
        this.nome   = nome;
    }
}
