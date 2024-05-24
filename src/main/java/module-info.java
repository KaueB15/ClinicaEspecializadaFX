module br.edu.fescfafic.clicinaespecializadafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires static lombok;
    requires twilio;
    requires org.hibernate.orm.core;


    opens br.edu.fescfafic.clicinaespecializadafx to javafx.fxml;
    exports br.edu.fescfafic.clicinaespecializadafx.main to javafx.graphics;
    exports br.edu.fescfafic.clicinaespecializadafx.view;
    opens br.edu.fescfafic.clicinaespecializadafx.view to javafx.fxml;

    exports br.edu.fescfafic.clicinaespecializadafx.domain;
    opens br.edu.fescfafic.clicinaespecializadafx.domain;
}