package nzuzi.joao.criacao_consulta_numero_bi.controller;

import nzuzi.joao.criacao_consulta_numero_bi.dto.PessoaDTO;
import nzuzi.joao.criacao_consulta_numero_bi.model.Pessoa;
import nzuzi.joao.criacao_consulta_numero_bi.service.IPessoa;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class PessoaController {

    @Autowired
    private IPessoa pessoaService;
    @PostMapping(value = "/gerar")
    public String gerarNumeroBilhete(@RequestBody Pessoa pessoa){
        return pessoaService.gerarNumeroBilhete(pessoa);
    }

    @GetMapping(value = "/pesquisar")
    public List<PessoaDTO> buscarPessoa(@RequestParam(value = "nome", required = false) String nome,
                                        @RequestParam(value = "numero_bilhete", required = false) String numeroBilhete,
                                        @RequestParam(value = "telefone", required = false) String telefone,
                                        @RequestParam(value = "provincia", required = false) String provincia,
                                        @RequestParam(value = "municipio", required = false) String municipio,
                                        @RequestParam(value = "comuna", required = false) String comuna){
        return pessoaService.findPessoas(nome, numeroBilhete, telefone, provincia, municipio, comuna);
    }
}
