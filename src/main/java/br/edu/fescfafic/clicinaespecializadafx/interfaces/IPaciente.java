package br.edu.fescfafic.clicinaespecializadafx.interfaces;

import br.edu.fescfafic.clicinaespecializadafx.domain.Login;

public interface IPaciente {
    int getId();
    String getNome();
    Login getLogin();
    String getCpf();
    String getTelefonePaciente();
    void setId(int idPaciente);
    void setNome(String nomePaciente);
    void setLogin(Login loginPaciente);
    void setCpf(String cpfPaciente);
    void setTelefonePaciente(String telefonePaciente);
}
