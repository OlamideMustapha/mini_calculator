package mini.calculator.controllers;

import javafx.beans.property.StringProperty;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.Scene;
import javafx.scene.Node;
import javafx.fxml.FXML;

import java.io.IOException;
import java.net.*;

public class Display extends HBox {

  @FXML private HBox display;
  @FXML private ScrollPane label_wrapper;
  @FXML private Label label_display;

  public Display () {
    FXMLLoader fxmlLoader =
      new FXMLLoader (
        getClass ()
        .getResource ("/display_component.fxml")
        );
    fxmlLoader.setRoot (this);
    fxmlLoader.setController (this);
    try {
        fxmlLoader.load ();
    } catch (IOException exception) {
        throw new RuntimeException (exception);
    }

  }

  public Node getdisplay () {
    return display;
  }

  public String getText () {
    return textProperty ().get ();
  }

  public void setText (String value) {
    textProperty ().set (value);
  }

  public StringProperty textProperty () {
    return label_display.textProperty ();
  }

}
