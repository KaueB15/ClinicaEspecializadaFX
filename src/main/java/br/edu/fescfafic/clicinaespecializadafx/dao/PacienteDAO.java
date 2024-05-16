package br.edu.fescfafic.clicinaespecializadafx.dao;

import br.edu.fescfafic.clicinaespecializadafx.domain.Paciente;
import br.edu.fescfafic.clicinaespecializadafx.persistence.EntityManagerConnection;

public class PacienteDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void cadastrarPaciente(Paciente paciente){
        // Inicia a transação com o BD
        getEmc().getEntityManager().getTransaction().begin();
        // Realiza a percistencia na tabela
        getEmc().getEntityManager().persist(paciente);
        // Confirmação da transação
        getEmc().getEntityManager().getTransaction().commit();
    }
}
