module TheBigDev {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;
    requires org.hibernate.orm.core;
    requires java.sql;
    requires java.naming;
    requires java.persistence;

    // Abre tu paquete de entidades a Hibernate
    opens TheBigDev.modelo to org.hibernate.orm.core;

    opens TheBigDev to javafx.fxml;
    exports TheBigDev;
}