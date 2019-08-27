/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import bl.dao.usuario.Usuario;
import controller.Controller;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 * FXML Controller class
 *
 * @author Luis
 */
public class FXML_scoreController implements Initializable {

    ArrayList<Usuario> misRecords = new ArrayList();

    @FXML
    TableView tableView = new TableView();
    @FXML
    TableColumn posicion = new TableColumn();
    @FXML
    TableColumn nombreJugador = new TableColumn();
    @FXML
    TableColumn tiempoRecord = new TableColumn();

    public void ingresarRecords() {
        Controller adm = new Controller();
        misRecords = adm.listarRecords();
    }

    public void mostrarRecords() {

        int size = misRecords.size();

        posicion.setCellValueFactory(new PropertyValueFactory("posicion"));
        nombreJugador.setCellValueFactory(new PropertyValueFactory("nombre"));
        tiempoRecord.setCellValueFactory(new PropertyValueFactory("tiempo"));
        for (int i = 0; i < size; i++) {
            tableView.getItems().add(misRecords.get(i));
        }

    }

    //Esta funcion va en el boton de eliminar para que elimine y cargue todo 
    public void loadRecords() {
        tableView.getItems().clear();
        ingresarRecords();
        mostrarRecords();
    }

    public void volver(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/ui/FXML_inicio.fxml"));
            loader.load();
            Parent root = loader.getRoot();
            Platform.runLater(() -> root.requestFocus());
            Scene scene = new Scene(root);
            Stage stage = new Stage();
            stage.setTitle("Clash Royale MEMORY CARD");
            stage.setScene(scene);
            stage.show();
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void eliminar() {
        Controller adm = new Controller();
        adm.eliminarRecords();
        loadRecords();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadRecords();
    }

}
