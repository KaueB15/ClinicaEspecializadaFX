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
}
