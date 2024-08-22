package nzuzi.joao.criacao_consulta_numero_bi.model;

import lombok.Getter;
import lombok.Setter;

public class Comuna {

    @Getter
    @Setter
    public String codigoProvincia;
    @Getter
    @Setter
    public String codigoMunicipio;

    @Getter
    @Setter
    public String codigo;

    public Comuna(String codigoProvincia, String codigoMunicipio, String codigo){
        this.codigoProvincia = codigoProvincia;
        this.codigoMunicipio = codigoMunicipio;
        this.codigo = codigo;
    }

}
