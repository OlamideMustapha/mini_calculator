package mini.calculator.controllers;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXML;

// import mini.calculator.controllers.Main;

import java.io.IOException;

public class Buttons extends GridPane {
  @FXML private GridPane btnGrid;

  public Buttons () {

    FXMLLoader fxmlLoader =
      new FXMLLoader (
        getClass ()
        .getResource ("/btn_component.fxml")
        );
    fxmlLoader.setRoot (this);
    fxmlLoader.setController (this);
    try {
        fxmlLoader.load ();
    } catch (IOException exception) {
        throw new RuntimeException (exception);
    }
  }

  public GridPane getView () {
    return btnGrid;
  }


  @FXML
  public void handleButtonAction (ActionEvent e) {

    Scene scene   = this.getScene ();
    Main mainNode = (Main) scene.lookup ("#main");
    String id     = ((Node) e.getSource ()).getId ();

    mainNode.updateDisplay (id);
  }

}
