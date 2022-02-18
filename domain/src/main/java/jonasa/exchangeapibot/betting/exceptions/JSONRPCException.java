package jonasa.exchangeapibot.betting.exceptions;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JSONRPCException {

    private static final Map<Integer, String> exceptionMap = Map.of(
            -32700, "Invalid JSON was received by the server. An error occurred on the server while parsing the JSON text.",
            -32603, "Internal JSON-RPC error",
            -32602, "Problem parsing the parameters, or a mandatory parameter was not found",
            -32601, "Method not found"
    );

    private Integer errorCode;

    private String message;
}
