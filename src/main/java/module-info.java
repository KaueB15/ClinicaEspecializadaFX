module br.edu.fescfafic.clicinaespecializadafx {
    requires javafx.controls;
    requires javafx.fxml;
    requires jakarta.persistence;
    requires static lombok;
    requires twilio;


    opens br.edu.fescfafic.clicinaespecializadafx to javafx.fxml;
    exports br.edu.fescfafic.clicinaespecializadafx;
}