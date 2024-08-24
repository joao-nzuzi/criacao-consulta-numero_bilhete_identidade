package nzuzi.joao.criacao_consulta_numero_bi.dto;

import lombok.Getter;
import lombok.Setter;
import nzuzi.joao.criacao_consulta_numero_bi.model.BilheteIdentidade;
import nzuzi.joao.criacao_consulta_numero_bi.model.Cidadao;
import org.springframework.beans.BeanUtils;

import java.io.Serializable;
import java.time.LocalDate;

@Getter @Setter
public class GeradorBilheteDTO implements Serializable {

    private String numeroBilhete;
    private Cidadao cidadao;
    private LocalDate dataEmissao;
    private String dataValidade;

    public static GeradorBilheteDTO convertToGeradorBilheteDTO(Cidadao cidadao){
        GeradorBilheteDTO geradorBilheteDTO = new GeradorBilheteDTO();
        BeanUtils.copyProperties(cidadao, geradorBilheteDTO);

        return geradorBilheteDTO;
    }
}
