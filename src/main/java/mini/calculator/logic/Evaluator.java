package mini.calculator.logic;


import java.util.regex.*;
import java.util.*;

public class Evaluator {

  public String evaluate (String line) {
    String
      digit = "[\\-|\\+]?\\d*\\.?\\d*",
      // match expression enclosed in brackets
      pattern1 = "(\\(([\\+|\\-|\\*|/]?\\d*\\.?\\d*)+\\))",
      // matches expression sep by "*"
      prod = "\\*",
      // matches expression sep by "/"
      div = "/",
      // matches expression sep by "+"
      sum = "\\+",
      // matches expression sep by "-"
      diff = "\\-",
      // combines two patterns
      pd = "("+digit+"["+prod+"|"+div+"]"+digit +")",
      as = "("+digit+"["+sum+"|"+diff+"]"+digit +")",
      bracket  = parse (line, pattern1, "b"),
      prodDiv  = parse (bracket, pd, "a"),
      addSub   = parse (prodDiv, as, "a");
    return convert (addSub);
  }

  private String parse (String value, String regex, String flag) {
    String line = value;
    Pattern r   = Pattern.compile (regex);
    Matcher m   = r.matcher(line);
    boolean p   = !Pattern.matches ( "[\\+|\\-]?\\d*\\.?\\d*"
                                    , line);
    while (m.find() && p) {
      String
        group  = m.group (),
        result =
          flag == "a"
            ? eval (group)
            : evaluate (group.substring(1, group.length() - 1));
      line = Pattern.matches ("\\b\\-|\\+\\S*", group)
                ? m.replaceFirst (group.substring (0, 1) + result)
                : m.replaceFirst (result);
      m    = r.matcher (line);
      p    = !Pattern.matches ("[\\+|\\-]?\\d*\\.?\\d*", line);
    }

    return line;
  }


  private String eval (String value) {
    Pattern re = Pattern.compile("[^(\\d*\\.?\\d*)]|[\\*]"),
            re2 = Pattern.compile ("[^\\*|\\+|\\-|/]");
    ArrayDeque
      arr = new ArrayDeque (Arrays.asList(re2.split(value))),
      operend = new ArrayDeque (Arrays.asList(re.split(value)));

    String last = (String) arr.getLast();
    String init = (String) arr.getFirst();
    if (!init.equals("")) operend.removeFirst();
    Double
      num1   =
        Double.parseDouble (init+(String) operend.getFirst()),
      num2   =
        Double.parseDouble ((String) operend.getLast()),
      result =
      (last.equals("+") || last.equals ("--"))
        ? num1 + num2
        : (last.equals("-") || last.equals ("+-"))
            ? num1 - num2
            : (last.equals ("/"))
                ? num1 / num2
                : (last.equals ("/-"))
                    ? (-(num1/num2))
                    : (last.equals ("*-"))
                        ? (-(num1 * num2))
                        : num1 * num2;;

    return result.toString();
  }

  private String convert (String value) {
    return Pattern.matches ("[\\+|\\-]?\\d*\\.{1,1}0", value)
      ? value.substring (0,value.length() - 2)
      : value;
  }

}

