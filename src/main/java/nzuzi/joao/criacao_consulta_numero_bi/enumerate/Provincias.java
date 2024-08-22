package nzuzi.joao.criacao_consulta_numero_bi.enumerate;

import lombok.Getter;
import lombok.Setter;

public enum Provincias {

    BENGUELA("BA", "BENGUELA"),
    BIE("BE", "BIÉ"),
    BENGO("BO", "BENGO"),
    CABINDA ("CA", "CABINDA"),
    CUANDO_CUBANGO("CC", "CUANDO CUBANGO"),
    CUNENE("CE", "CUNENE"),
    CUANZA_NORTE("CN", "CUANZA NORTE"),
    CUANZA_SUL("CS", "CUANZA SUL"),
    HUILA("HA", "HUILA"),
    HUAMBO("HO", "HUAMBO"),
    LUANDA("LA", "LUANDA"),
    LUNDA_NORTE("LN", "LUNDA_NORTE"),
    LUNDA_SUL("LS", "LUNDA SUL"),
    MALANJE("ME", "MALANJE"),
    MOXICO("MO", "MOXICO"),
    NAMIBE("NE", "NAMIBE"),
    UIGE("UE", "UÍGE"),
    ZAIRE("ZE", "ZAIRE");

        @Getter
        @Setter
        private String code;
        @Getter
        @Setter
        private String description;

    Provincias(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public static Provincias getProvinceCode(String value) {
        for(Provincias code : Provincias.values()) {
            if(value.equals(code.getCode())) {
                return code;
            }
        }
        return null;
    }
}
