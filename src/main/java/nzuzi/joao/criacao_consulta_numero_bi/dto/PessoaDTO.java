package nzuzi.joao.criacao_consulta_numero_bi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import nzuzi.joao.criacao_consulta_numero_bi.model.Contacto;
import nzuzi.joao.criacao_consulta_numero_bi.model.Endereco;
import nzuzi.joao.criacao_consulta_numero_bi.model.Pessoa;
import org.springframework.beans.BeanUtils;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
@Getter @Setter
public class PessoaDTO implements Serializable {

    private String nomeCompleto;
    private String numeroBilhete;
    private String nomePai;
    private String nomeMae;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataNascimento;
    private String genero;
    private String estadoCivil;
    @JsonFormat(pattern = "yyyy-MM-dd", shape = JsonFormat.Shape.STRING)
    private LocalDate dataEmissao;
    private String dataValidade;
    private Endereco endereco;
    private List<Contacto> contactos;

    public static PessoaDTO convertToPessoaDTO(Pessoa pessoa){
        PessoaDTO pessoaDTO = new PessoaDTO();
        BeanUtils.copyProperties(pessoa, pessoaDTO);

        return pessoaDTO;
    }
}
