package nzuzi.joao.criacao_consulta_numero_bi.model;

import lombok.Getter;
import lombok.Setter;

public class Municipio {
    @Getter
    @Setter
    public String provincia;
    @Getter
    @Setter
    public String codigoMunicipio;

    public Municipio(String provincia, String codigoMunicipio){
        this.provincia = provincia;
        this.codigoMunicipio = codigoMunicipio;
    }

}
