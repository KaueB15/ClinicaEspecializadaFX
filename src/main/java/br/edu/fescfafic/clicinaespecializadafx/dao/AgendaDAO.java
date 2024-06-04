package br.edu.fescfafic.clicinaespecializadafx.dao;

import br.edu.fescfafic.clicinaespecializadafx.domain.Agenda;
import br.edu.fescfafic.clicinaespecializadafx.domain.Agendamento;
import br.edu.fescfafic.clicinaespecializadafx.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;

import java.util.List;

public class AgendaDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void inserirAgenda(Agenda agendamento) {
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().persist(agendamento);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }

    public List<Agenda> listarAgendas() {
        TypedQuery<Agenda> query = getEmc().getEntityManager().createNamedQuery("listarTodasAgendas", Agenda.class);
        return query.getResultList();
    }

    public void removerAgenda(Agenda agenda) {
        var em = getEmc().getEntityManager();
        getEmc().getEntityManager().getTransaction().begin();
        Agenda remover = em.find(Agenda.class, agenda.getId());
        if (remover != null) {
            em.remove(remover);
            System.out.println("Agenda removida com sucesso!");
        } else {
            System.out.println("Agenda não encontrado!");
        }
        getEmc().getEntityManager().getTransaction().commit();
        getEmc().getEntityManager().close();

    }
}
