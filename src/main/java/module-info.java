module com.example.examen_diseno_interfaces_primer_trimestre {
    requires javafx.controls;
    requires javafx.fxml;


    opens com.example.examen_diseno_interfaces_primer_trimestre to javafx.fxml;
    exports com.example.examen_diseno_interfaces_primer_trimestre;
    exports com.example.examen_diseno_interfaces_primer_trimestre.controller;
    opens com.example.examen_diseno_interfaces_primer_trimestre.controller to javafx.fxml;
}