package nutrition.exception;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by andrius on 3/28/17.
 */

public class DataMappingException extends RuntimeException {

    private List<String> errors;

    public DataMappingException(String s, List<ObjectError> allErrors) {
        super(s);
        this.errors = mapErrors(allErrors);
    }

    private static List<String> mapErrors(List<ObjectError> allErrors) {
        return allErrors.stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
    }

}
