package br.edu.fescfafic.clicinaespecializadafx.dao;

import br.edu.fescfafic.clicinaespecializadafx.domain.Paciente;
import br.edu.fescfafic.clicinaespecializadafx.persistence.EntityManagerConnection;

import java.util.List;

public class PacienteDAO {

    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void cadastrarPaciente(Paciente paciente) {
        try {
            // Inicia a transação com o BD
            getEmc().getEntityManager().getTransaction().begin();
            // Realiza a percistencia na tabela
            getEmc().getEntityManager().persist(paciente);
            // Confirmação da transação
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }

    public List<Paciente> listarPacientes() {
        getEmc().getEntityManager().getTransaction().begin();
        var query = getEmc().getEntityManager().createNamedQuery("pacientes.getAll");
        return query.getResultList();
    }

    public Paciente buscarPorCpf(String cpf) {
        getEmc().getEntityManager().getTransaction().begin();
        var query = getEmc().getEntityManager().createNamedQuery("pacientes.getByCpf");
        query.setParameter("cpf", cpf);
        return (Paciente) query.getSingleResult();
    }

    public Paciente findById(int id) {
        getEmc().getEntityManager().getTransaction().begin();
        return getEmc().getEntityManager().find(Paciente.class, id);
    }

    public void deletarPorCpf(String cpf) {
        var paciente = buscarPorCpf(cpf);
        getEmc().getEntityManager().remove(paciente);
        getEmc().getEntityManager().getTransaction().commit();
        getEmc().getEntityManager().close();
    }

    public void deletarPorId(int id) {
        var paciente = findById(id);
        getEmc().getEntityManager().remove(paciente);
        getEmc().getEntityManager().getTransaction().commit();
        getEmc().getEntityManager().close();
    }
    public void atualizarPaciente(Paciente paciente) {
        // Inicia a transação com o BD
        getEmc().getEntityManager().getTransaction().begin();
        // Realiza a percistencia na tabela
        getEmc().getEntityManager().merge(paciente);
        // Confirmação da transação
        getEmc().getEntityManager().getTransaction().commit();
        getEmc().getEntityManager().close();
    }

}