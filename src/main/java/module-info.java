module congmonj.c482_software_i {
    requires javafx.controls;
    requires javafx.fxml;


    opens congmonj.c482_software_i to javafx.fxml;
    exports congmonj.c482_software_i;
    exports congmonj.c482_software_i.controller;
    exports congmonj.c482_software_i.model;
    opens congmonj.c482_software_i.controller to javafx.fxml;
}