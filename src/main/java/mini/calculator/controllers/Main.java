package mini.calculator.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;
import javafx.fxml.FXML;

import java.net.*;
import java.io.IOException;

import mini.calculator.logic.*;

public class Main extends BorderPane {

  @FXML private Display displayBox;
  private Expression expr = new Expression ();
  private Evaluator eval = new Evaluator ();


  public Main () {
    FXMLLoader fxmlLoader =
      new FXMLLoader (
        getClass ()
        .getResource ("/main.fxml")
        );
    fxmlLoader.setRoot (this);
    fxmlLoader.setController (this);

    try {
        fxmlLoader.load ();
    } catch (IOException exception) {
        throw new RuntimeException (exception);
    }
  }

  public void updateDisplay (String value) {
    String  text       = displayBox.getText (),
            expression = (value.equals ("equal_to"))
                            ? eval.evaluate(text)
                            : expr.build (text, value);
      displayBox.setText (expression);

  }
}