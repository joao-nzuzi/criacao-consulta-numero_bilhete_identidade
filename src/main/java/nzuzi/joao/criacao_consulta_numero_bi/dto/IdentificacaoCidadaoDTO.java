package nzuzi.joao.criacao_consulta_numero_bi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import nzuzi.joao.criacao_consulta_numero_bi.model.BilheteIdentidade;
import nzuzi.joao.criacao_consulta_numero_bi.model.Contacto;
import nzuzi.joao.criacao_consulta_numero_bi.model.Endereco;
import nzuzi.joao.criacao_consulta_numero_bi.model.IdentificacaoCidadao;
import org.springframework.beans.BeanUtils;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Getter @Setter
public class IdentificacaoCidadaoDTO implements Serializable {

    private String nomeCompleto;
    private String nomePai;
    private String nomeMae;
    private String genero;
    private String estadoCivil;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataNascimento;
    private Endereco endereco;
    private BilheteIdentidade bilheteIdentidade;
    private List<Contacto> contactos;

    public static IdentificacaoCidadaoDTO convertToGeradorBilheteDTO(IdentificacaoCidadao identificacaoCidadao){
        IdentificacaoCidadaoDTO identificacaoCidadaoDTO = new IdentificacaoCidadaoDTO();
        BeanUtils.copyProperties(identificacaoCidadao, identificacaoCidadaoDTO);

        return identificacaoCidadaoDTO;
    }
}
