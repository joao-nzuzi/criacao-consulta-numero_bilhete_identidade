package nzuzi.joao.criacao_consulta_numero_bi.controller;

import nzuzi.joao.criacao_consulta_numero_bi.dto.GeradorBilheteDTO;
import nzuzi.joao.criacao_consulta_numero_bi.model.Cidadao;
import nzuzi.joao.criacao_consulta_numero_bi.service.ICidadao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class CidadaoController {

    @Autowired
    private ICidadao pessoaService;
    @PostMapping(value = "/bilhete")
    public ResponseEntity<String> gerarNumeroBilhete(@Valid @RequestBody Cidadao cidadao){
        return new ResponseEntity<>("Bilhete de Identidade Nº. "
                .concat(pessoaService.gerarNumeroBilhete(cidadao)),
                HttpStatus.CREATED);
    }

    @GetMapping(value = "/pessoas")
    public List<GeradorBilheteDTO> buscarPessoa(@RequestParam(value = "nome", required = false) String nome,
                                                @Valid @RequestParam(value = "numero_bilhete", required = false) String numeroBilhete,
                                                @RequestParam(value = "telefone", required = false) String telefone,
                                                @RequestParam(value = "provincia", required = false) String provincia,
                                                @RequestParam(value = "municipio", required = false) String municipio,
                                                @RequestParam(value = "comuna", required = false) String comuna){
        return pessoaService.findPessoas(nome, numeroBilhete, telefone, provincia, municipio, comuna);
    }
}
