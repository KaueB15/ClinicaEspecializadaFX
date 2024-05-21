package interfaces;

import br.edu.fescfafic.clicinaespecializadafx.domain.Login;

public interface IMedico {
    int getId();
    int getCrm();
    String getNome();
    String getEspecialidade();
    Login getLogin();
    String getCpf();
    void setId(int idMedico);
    void setCrm(int crm);
    void setNome(String nomeMedico);
    void setEspecialidade(String especialidade);
    void setLogin(Login loginMedico);
    void setCpf(String cpfMedico);
}
