module com.example.ep_p4 {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.eclipse.paho.client.mqttv3;
    requires java.net.http;
    requires java.sql;
    requires com.rabbitmq.client;

    opens com.example.ep_p4 to javafx.fxml;
    opens com.example.ep_p4.model to javafx.base;
    exports com.example.ep_p4;
}