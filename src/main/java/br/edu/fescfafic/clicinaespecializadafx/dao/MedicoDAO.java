package br.edu.fescfafic.clicinaespecializadafx.dao;

import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
import br.edu.fescfafic.clicinaespecializadafx.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;

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
        getEmc().getEntityManager().close();
    }

    public List<Medico> listarMedico() {
        getEmc().getEntityManager().getTransaction().begin();
        var query = getEmc().getEntityManager().createNamedQuery("medicos.getAll");
        return query.getResultList();
    }

    public Medico buscarPorCrm(int crm) {
        TypedQuery<Medico> query = getEmc().getEntityManager().createNamedQuery("medicos.getByCrm", Medico.class);
        getEmc().getEntityManager().getTransaction().begin();
        query.setParameter("crm", crm);
        return query.getSingleResult();
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
        getEmc().getEntityManager().close();
    }

    public void deletarPorId(int id) {
        var medico = findById(id);
        getEmc().getEntityManager().remove(medico);
        getEmc().getEntityManager().getTransaction().commit();
        getEmc().getEntityManager().close();
    }

    public void atualizarMedico(Medico medico) {
        // Inicia a transação com o BD
        getEmc().getEntityManager().getTransaction().begin();
        // Realiza a percistencia na tabela
        getEmc().getEntityManager().merge(medico);
        // Confirmação da transação
        getEmc().getEntityManager().getTransaction().commit();
        getEmc().getEntityManager().close();
    }


}
