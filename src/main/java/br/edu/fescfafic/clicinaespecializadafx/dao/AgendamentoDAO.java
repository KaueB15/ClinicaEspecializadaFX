package br.edu.fescfafic.clicinaespecializadafx.dao;

import br.edu.fescfafic.clicinaespecializadafx.domain.Agendamento;
import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.persistence.EntityManagerConnection;
import jakarta.persistence.TypedQuery;

import java.time.LocalDateTime;
import java.util.List;

public class AgendamentoDAO {
    private EntityManagerConnection emc = new EntityManagerConnection();

    public EntityManagerConnection getEmc() {
        return emc;
    }

    public void inserirAgendamento(Agendamento agendamento) {
        try {
            getEmc().getEntityManager().getTransaction().begin();
            getEmc().getEntityManager().persist(agendamento);
            getEmc().getEntityManager().getTransaction().commit();
        }finally {
            getEmc().getEntityManager().close();
        }
    }


    public List<Agendamento> listarAgendamentos() {
        TypedQuery<Agendamento> query = getEmc().getEntityManager().createNamedQuery("listarTodos", Agendamento.class);
        return query.getResultList();

    }

    public boolean verificarDisponibilidade(LocalDateTime dataHora) {
        TypedQuery<Agendamento> query = getEmc().getEntityManager().createNamedQuery("verificarDisponibilidade", Agendamento.class);
        query.setParameter("dataHora", dataHora);
        List<Agendamento> horario = query.getResultList();
        return horario.isEmpty();
    }

    public int getByHora(LocalDateTime horarioDisponivel) {
        if (verificarDisponibilidade(horarioDisponivel)) {
            System.out.println("Horario disponível!");
        } else {
            var hora = horarioDisponivel.getHour();
            var minutos = horarioDisponivel.getMinute();
            System.out.println("O horário das " + hora + "h" + minutos + "m já está reservado!");

        }
        return 0;
    }

    public void removerAgendamento(Agendamento agendamento) {
            var em = getEmc().getEntityManager();
            getEmc().getEntityManager().getTransaction().begin();
            Agendamento remover = em.find(Agendamento.class, agendamento.getId());
            if (remover != null) {
                em.remove(remover);
                System.out.println("Agendamento removido com sucesso!");
            } else {
                System.out.println("Agendamento não encontrado!");
            }
            getEmc().getEntityManager().getTransaction().commit();
            getEmc().getEntityManager().close();

        }

    public void atualizarAgendamento(Agendamento agendamento) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().merge(agendamento);
        getEmc().getEntityManager().getTransaction().commit();
        getEmc().getEntityManager().close();
        System.out.println("Agendamento atualizado com sucesso!");
    }


}



