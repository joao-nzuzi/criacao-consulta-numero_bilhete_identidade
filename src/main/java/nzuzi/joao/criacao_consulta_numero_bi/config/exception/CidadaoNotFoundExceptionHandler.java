package nzuzi.joao.criacao_consulta_numero_bi.config.exception;

public class CidadaoNotFoundExceptionHandler extends RuntimeException {

    public CidadaoNotFoundExceptionHandler(String numeroBilhete) {
        super(String.format("Pessoa com numero de bilhete nº %s não encontrado.", numeroBilhete));
    }
}
