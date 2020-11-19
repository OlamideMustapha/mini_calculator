package mini.calculator.logic;

import java.util.*;

public class Expression {

  List <String> numericKeys =
    List.of ( "1","2","3"
            , "4","5","6"
            , "7","8","9","0");

  Map <String, String> arithmeticKeys =
    Map.of  ( "add", "+"
            , "sub", "-"
            , "prod", "*"
            , "div", "/");
  Map <String, String> brackets =
    Map.of  ( "open_bracket", "("
            , "close_bracket", ")");


  private String clean (String expression, String value) {
    int length = expression.length ();
    return value.equals ("C")
              ? ""
              : expression.isEmpty ()
                  ? expression
                  : expression.substring (0, length - 1);

  }

  private String appendOperator (String expression, String value)
  {
    int length = expression.length ();
    String
      operator = arithmeticKeys.get (value),
      last     = expression.isEmpty ()
                    ? expression.substring (length)
                    : expression.substring (length - 1);

    return arithmeticKeys.containsValue (last)
              ? (clean (expression,"AC")).concat (operator)
              : (last.equals("."))
                  ? expression.concat ("0".concat (operator))
                  : expression.concat (operator);
  }


  private String appendNumb (String expression, String value) {
    boolean length = expression.length () == 1,
            end    = expression.endsWith ("*")
                  || expression.endsWith ("/")
                  || expression.endsWith ("+");

    return expression.isBlank ()
              ?  expression.concat (value)
              :  length && end
                    ? value
                    : expression.concat (value);
  }

  private String dotOperator (String expression, String value) {
    return expression.endsWith (".")
              ? expression
              : expression.concat (".");
  }


  private String appendBracket (String expression, String value) {
    return expression.concat( brackets.get (value));
  }

  public String build (String expr, String value) {
    return
      (value.equals ("C") || value.equals ("AC"))
        ? clean (expr, value)
        : (arithmeticKeys.containsKey (value))
            ? appendOperator (expr, value)
            : (numericKeys.contains (value))
                ? appendNumb (expr, value)
                : (value.equals ("dot"))
                    ? dotOperator (expr, value)
                    : appendBracket (expr, value);
  }
}