package com.example.examen_diseno_interfaces_primer_trimestre;

import com.example.examen_diseno_interfaces_primer_trimestre.models.Cliente;
import com.example.examen_diseno_interfaces_primer_trimestre.models.Coche;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ComboBox<String> comboModelo;

    @FXML
    private ComboBox<String> comboCliente;
    @FXML
    private DatePicker dateEntrada;
    @FXML
    private DatePicker dateSalida;
    @FXML
    private TextField txtMatricula;
    @FXML
    private Label labelPrecio;
    @FXML
    private TableView<Coche> tvCoche;
    @FXML
    private TableColumn<Coche, String> cMatricula;
    @FXML
    private TableColumn<Coche, String> cModelo;
    @FXML
    private TableColumn<Coche, String> cEntrada;
    @FXML
    private TableColumn<Coche, String> cSalida;
    @FXML
    private TableColumn<Coche, String> cCliente;
    @FXML
    private TableColumn<Coche, String> cTarifa;
    @FXML
    private TableColumn<Coche, String> cCoste;


    private ObservableList<Coche> parking = FXCollections.observableArrayList();
    Coche coche = Sesion.getCocheActual();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Sesion.getCoches().isEmpty()) {
            ArrayList<Coche> coches = new ArrayList<>(0);
            Cliente c1 = new Cliente(0, "Paquillo", "paquillo@gmail.com");
            coches.add(new Coche("0123ABC", "Ford", "Paquillo", "8€", LocalDate.of(2023, 12, 12), LocalDate.of(2023, 12, 13), 8));
            coches.add(new Coche("789ACBC", "Ford", "Paquillo", "8€", LocalDate.of(2023, 12, 12), LocalDate.of(2023, 12, 13), 8));
            coches.add(new Coche("147DCB", "Ford", "Paquillo", "8€", LocalDate.of(2023, 12, 12), LocalDate.of(2023, 12, 13), 8));
            Sesion.setCoches(coches);
        }
        parking.addAll( Sesion.getCoches());

        ObservableList<String> modelos = FXCollections.observableArrayList();
        modelos.addAll("Ford","Fiat","Honda","Ferrari");
        comboModelo.setItems(modelos);
        comboModelo.getSelectionModel().selectFirst();
        ObservableList<String> clientes = FXCollections.observableArrayList();
        clientes.addAll("Juan","María","Pepe","Ana");
        comboCliente.getSelectionModel().selectFirst();

        //Columna Matricula
        cMatricula.setCellValueFactory( (fila) -> {
            String matricula = fila.getValue().getMatricula();
            return new SimpleStringProperty( matricula );
        } );
        //Columna Modelo
        cModelo.setCellValueFactory( (fila) -> new SimpleStringProperty( fila.getValue().getModelo() ));

        //Columna Cliente
        cCliente.setCellValueFactory( (fila) -> new SimpleStringProperty( fila.getValue().getCliente() ));

        //Columna Tarifa
        cTarifa.setCellValueFactory( (fila) -> new SimpleStringProperty( fila.getValue().getTarifa() ));

        //Columnas Fecha de entrada y salida
        dateEntrada.setValue(coche.getFechaEntrada());
        dateSalida.setValue(coche.getFechaSalida());

        //Columna Coste Total
        cCoste.setCellValueFactory(fila ->
                new SimpleStringProperty(Integer.toString(fila.getValue().getCoste()))
        );
    }

    public void add(ActionEvent actionEvent) {
    }

    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }
}