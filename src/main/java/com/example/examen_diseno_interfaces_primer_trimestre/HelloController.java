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
import javafx.scene.input.MouseEvent;
import javafx.util.StringConverter;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class HelloController implements Initializable {

    @FXML
    private ComboBox<String> comboModelo;

    @FXML
    private ComboBox<Cliente> comboCliente;
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
    @FXML
    private RadioButton rbStandard;
    @FXML
    private RadioButton rbOferta;
    @FXML
    private RadioButton rbLargaDuracion;

    private ObservableList<Coche> observableListCoche = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        if(Sesion.getCoches().isEmpty()) {
            ObservableList<Cliente> clientes = FXCollections.observableArrayList();
            clientes.addAll(
            new Cliente(0, "Paquillo", "paquillo@gmail.com"),
            new Cliente(1, "Juanillo", "juanillo@gmail.com"),
            new Cliente(2, "Pablillo", "Pablillo@gmail.com"));

            comboCliente.setItems(clientes);
            comboCliente.getSelectionModel().selectFirst();

            ArrayList<Coche> coches = new ArrayList<>(0);
            coches.add(new Coche("0123ABC", "Ford", clientes.get(0), "8€", LocalDate.of(2023,
                    12, 12), LocalDate.of(2023, 12, 15), 8));
            coches.add(new Coche("789ACBC", "Ford",clientes.get(1), "8€", LocalDate.of(2023,
                    12, 12), LocalDate.of(2023, 12, 17), 8));
            coches.add(new Coche("147DCB", "Ford", clientes.get(2), "8€", LocalDate.of(2023,
                    12, 12), LocalDate.of(2023, 12, 11), 8));
            Sesion.setCoches(coches);
        }

        observableListCoche.addAll( Sesion.getCoches());

        tvCoche.setItems(observableListCoche);

        ObservableList<String> modelos = FXCollections.observableArrayList();
        modelos.addAll("Ford","Fiat","Honda","Ferrari");

        comboModelo.setItems(modelos);
        comboModelo.getSelectionModel().selectFirst();

        comboCliente.setConverter(new StringConverter<>() {
            @Override
            public String toString(Cliente cliente) {
                if (cliente != null) return cliente.getNombre();
                else return null;
            }

            @Override
            public Cliente fromString(String s) {
                return null;
            }
        });


        //Columna Matricula
        cMatricula.setCellValueFactory( (fila) -> {
            String matricula = fila.getValue().getMatricula();
            return new SimpleStringProperty(matricula);
        });
        //Columna Modelo
        cModelo.setCellValueFactory( (fila) -> {
            String modelo = fila.getValue().getModelo();
            return new SimpleStringProperty(modelo);
        });

        //Columna Cliente
        cCliente.setCellValueFactory( (fila) -> {
            String cliente = String.valueOf(fila.getValue().getCliente().toString());
            return new SimpleStringProperty(cliente);
        });

        //Columna Tarifa
        cTarifa.setCellValueFactory( (fila) -> {
            String tarifa = fila.getValue().getTarifa();
            return new SimpleStringProperty(tarifa);
        });

        //Columna Fecha de Entrada
        cEntrada.setCellValueFactory( (fila) -> {
            String entrada = String.valueOf(fila.getValue().getFechaEntrada());
            return new SimpleStringProperty(entrada);
        });
        //Columna Fecha de Salida
        cSalida.setCellValueFactory( (fila) -> {
            String salida = String.valueOf(fila.getValue().getFechaSalida());
            return new SimpleStringProperty(salida);
        });

        //Columna Coste Total
        cCoste.setCellValueFactory( (fila) -> {
            String coste = String.valueOf(fila.getValue().getModelo());
            return new SimpleStringProperty(coste);
        });

    }

    public void add(ActionEvent actionEvent) {
        if (!txtMatricula.getText().isEmpty() && comboModelo.getValue() != null && comboCliente.getValue() != null
                && dateEntrada.getValue() != null && dateSalida.getValue() != null
                && (rbStandard.isSelected() || rbOferta.isSelected() || rbLargaDuracion.isSelected())) {

            LocalDate fechaEntrada = dateEntrada.getValue();
            LocalDate fechaSalida = dateSalida.getValue();

            Period periodo = Period.between(fechaEntrada, fechaSalida);
            int dias = periodo.getDays();

            double precioDiario = 0;
            String tarifaSeleccionada = "";

            if (rbStandard.isSelected()) {
                precioDiario = 8;
                tarifaSeleccionada = "Standard";
            } else if (rbOferta.isSelected()) {
                precioDiario = 6;
                tarifaSeleccionada = "Oferta";
            } else if (rbLargaDuracion.isSelected()) {
                precioDiario = 2;
                tarifaSeleccionada = "Larga Duración";
            }

            double coste = dias * precioDiario;

            Coche coche = new Coche();
            coche.setMatricula(txtMatricula.getText());
            coche.setModelo(comboModelo.getValue());
            coche.setFechaEntrada(fechaEntrada);
            coche.setFechaSalida(fechaSalida);
            coche.setCliente(comboCliente.getValue());
            coche.setTarifa(tarifaSeleccionada);
            coche.setCoste((int) coste);

            Sesion.getCoches().add(coche);
            observableListCoche.add(coche);

            labelPrecio.setText(coste + "€");

            clearAll();
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Hay un error en el formulario.");
            alert.setContentText("Falta información por indicar en el formulario");
            alert.showAndWait();
        }
    }

    private void clearAll() {
        txtMatricula.clear();
        comboModelo.setValue(null);
        comboCliente.setValue(null);
        dateEntrada.setValue(null);
        dateSalida.setValue(null);
        rbStandard.setSelected(false);
        rbOferta.setSelected(false);
        rbLargaDuracion.setSelected(false);
    }


    public void salir(ActionEvent actionEvent) {
        System.exit(0);
    }

    public void mostrarMasInfo(MouseEvent mouseEvent) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Creado por");
        alert.setHeaderText("Francisco Díaz Roldán");
        alert.setContentText("Creado por Francisco Díaz Roldán 2ºDAM");
        alert.showAndWait();
    }
}