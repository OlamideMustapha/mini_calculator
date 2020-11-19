package mini.calculator.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.scene.shape.Circle;
import javafx.scene.Node;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;

import java.io.IOException;
import java.util.regex.*;

public class Theme extends HBox {
  @FXML private Circle default_theme;
  @FXML private Circle white_theme;
  @FXML private Circle pink_theme;
  @FXML private Circle blue_theme;

  public Theme () {

    FXMLLoader fxmlLoader = new FXMLLoader (
        getClass()
        .getResource("/theme_component.fxml"));

    fxmlLoader.setRoot (this);
    fxmlLoader.setController (this);

    try {
      fxmlLoader.load();
    } catch (IOException exception) {
      throw new RuntimeException (exception);
    }
    initializeHandlers ();
  }

  private void initializeHandlers () {
    EventHandler<MouseEvent> defaultTheme = (MouseEvent e) -> {
      Scene scene   = this.getScene ();
      Main mainNode = (Main) scene.lookup ("#main");
      String id = ((Circle) e.getSource ()).getId ();
      mainNode.getStylesheets()
        .replaceAll(style -> "default_theme.css");
    };
    EventHandler<MouseEvent> whiteTheme = (MouseEvent e) -> {
      Scene scene   = this.getScene ();
      Main mainNode = (Main) scene.lookup ("#main");
      String id = ((Circle) e.getSource ()).getId ();
      mainNode.getStylesheets()
        .replaceAll(style -> "white_theme.css");
    };
    EventHandler<MouseEvent> pinkTheme = (MouseEvent e) -> {
      Scene scene   = this.getScene ();
      Main mainNode = (Main) scene.lookup ("#main");
      String id = ((Circle) e.getSource ()).getId ();
      mainNode.getStylesheets()
        .replaceAll(style -> "pink_theme.css");
    };

    EventHandler<MouseEvent> blueTheme = (MouseEvent e) -> {
      Scene scene   = this.getScene ();
      Main mainNode = (Main) scene.lookup ("#main");
      String id = ((Circle) e.getSource ()).getId ();
      mainNode.getStylesheets()
        .replaceAll(style -> "blue_theme.css");
    };

    //Registering the event filter
    blue_theme.addEventFilter (  MouseEvent.MOUSE_CLICKED
                              ,  blueTheme
                              );
    pink_theme.addEventFilter (  MouseEvent.MOUSE_CLICKED
                              ,  pinkTheme
                              );
    white_theme.addEventFilter(  MouseEvent.MOUSE_CLICKED
                              ,  whiteTheme
                              );
    default_theme.addEventFilter( MouseEvent.MOUSE_CLICKED
                                , defaultTheme
                                );
  }

}