package io.github.aj8gh.bot.domain.betting.exceptions;

import java.util.Map;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class JSONRPCException extends RuntimeException {

  private static final Map<Integer, String> EXCEPTION_MAP = Map.of(
      -32700,
      "Invalid JSON was received by the server. An error occurred on the server while parsing the JSON text.",
      -32603, "Internal JSON-RPC error",
      -32602, "Problem parsing the parameters, or a mandatory parameter was not found",
      -32601, "Method not found"
  );

  private final Integer errorCode;
  private final String message;

  public JSONRPCException(Integer errorCode, String message) {
    this.errorCode = errorCode;
    this.message = message;
  }

  public JSONRPCException(String message, Integer errorCode, String message1) {
    super(message);
    this.errorCode = errorCode;
    this.message = message1;
  }
}
