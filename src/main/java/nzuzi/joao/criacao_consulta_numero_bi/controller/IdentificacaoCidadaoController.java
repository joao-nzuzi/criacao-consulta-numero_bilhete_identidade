package nzuzi.joao.criacao_consulta_numero_bi.controller;

import nzuzi.joao.criacao_consulta_numero_bi.dto.IdentificacaoCidadaoDTO;
import nzuzi.joao.criacao_consulta_numero_bi.model.IdentificacaoCidadao;
import nzuzi.joao.criacao_consulta_numero_bi.service.IIdentificacaoCidadao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api")
public class IdentificacaoCidadaoController {

    @Autowired
    private IIdentificacaoCidadao pessoaService;
    @PostMapping(value = "/bilhete")
    public ResponseEntity<String> generateBINumber(@Valid @RequestBody IdentificacaoCidadao identificacaoCidadao){
        return new ResponseEntity<>( "Nº de Bilhete de Identidade "
                .concat(pessoaService.generateBINumber(identificacaoCidadao))
                .concat(" gerado com sucesso"), HttpStatus.CREATED);
    }

    @GetMapping(value = "/cidadaos")
    public List<IdentificacaoCidadaoDTO> findCitizens(@RequestParam(value = "nome", required = false) String nome,
                                                      @RequestParam(value = "numero_bilhete", required = false) String numeroBilhete,
                                                      @RequestParam(value = "telefone", required = false) String telefone,
                                                      @RequestParam(value = "provincia", required = false) String provincia,
                                                      @RequestParam(value = "municipio", required = false) String municipio,
                                                      @RequestParam(value = "comuna", required = false) String comuna){
        return pessoaService.findCitizens(nome, numeroBilhete, telefone, provincia, municipio, comuna);
    }
}
