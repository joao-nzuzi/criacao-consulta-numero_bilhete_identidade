package nzuzi.joao.criacao_consulta_numero_bi.config.exception;

public class PessoaNotFoundExceptionHandler extends RuntimeException {

    public PessoaNotFoundExceptionHandler(String numeroBilhete) {
        super(String.format("Pessoa com numero de bilhete nº %s não encontrado.", numeroBilhete));
    }
}
