package br.edu.fescfafic.clicinaespecializadafx.dao;

import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
import br.edu.fescfafic.clicinaespecializadafx.persistence.EntityManagerConnection;

import java.util.List;

public class MedicoDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void cadastrarMedico(Medico medico) {
        // Inicia a transação com o BD
        getEmc().getEntityManager().getTransaction().begin();
        // Realiza a percistencia na tabela
        getEmc().getEntityManager().persist(medico);
        // Confirmação da transação
        getEmc().getEntityManager().getTransaction().commit();
    }

    public List<Medico> listarMedico() {
        getEmc().getEntityManager().getTransaction().begin();
        var query = getEmc().getEntityManager().createNamedQuery("medicos.getAll");
        return query.getResultList();
    }

    public Medico buscarPorCrm(int crm) {
        getEmc().getEntityManager().getTransaction().begin();
        var query = getEmc().getEntityManager().createNamedQuery("medicos.getByCrm");
        query.setParameter("crm", crm);
        return (Medico) query.getSingleResult();
    }

    public Medico findById(int id) {
        getEmc().getEntityManager().getTransaction().begin();
        return getEmc().getEntityManager().find(Medico.class, id);
    }

    public List<Medico> listarPorEspecialidade(String especialidade) {
        getEmc().getEntityManager().getTransaction().begin();
        var query = getEmc().getEntityManager().createNamedQuery("medicos.getByEspecialidade");
        return query.getResultList();
    }

    public void deletarPorCrm(int crm) {
        var medico = buscarPorCrm(crm);
        getEmc().getEntityManager().remove(medico);
        getEmc().getEntityManager().getTransaction().commit();
    }

    public void deletarPorId(int id) {
        var medico = findById(id);
        getEmc().getEntityManager().remove(medico);
        getEmc().getEntityManager().getTransaction().commit();
    }
    //TODO:Ainda falta definir o metodo de atualizar, pois pretendo disponibilizar atualização de dados individuais

}
