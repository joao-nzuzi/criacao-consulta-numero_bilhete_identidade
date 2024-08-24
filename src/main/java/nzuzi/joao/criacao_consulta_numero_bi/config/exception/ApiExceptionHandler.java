package nzuzi.joao.criacao_consulta_numero_bi.config.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.util.Arrays;
import java.util.List;
@Getter @Setter
public class ApiExceptionHandler {

    private HttpStatus status;
    private List<String> errors;

    public ApiExceptionHandler(HttpStatus status, List<String> errors) {
        super();
        this.status = status;
        this.errors = errors;
    }

    public ApiExceptionHandler(HttpStatus status, String errors) {
        super();
        this.status = status;
        this.errors = Arrays.asList(errors);
    }


}
