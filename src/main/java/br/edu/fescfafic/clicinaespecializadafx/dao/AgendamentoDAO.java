package br.edu.fescfafic.clicinaespecializadafx.dao;

import br.edu.fescfafic.clicinaespecializadafx.domain.Agendamento;
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
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().persist(agendamento);
        getEmc().getEntityManager().getTransaction().commit();
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

    //Esse método DEVE impedir que os agendamentos sejam realizados se outro estiver em curso, com um respectivo médico
    public Agendamento verificarDisponibilidadeMarcacao(LocalDateTime dataHoraAtendimento, int idMedico) {
        LocalDateTime inicioAtendimento = dataHoraAtendimento;
        LocalDateTime fimAtendimento = inicioAtendimento.plusMinutes(45);

        try {
            TypedQuery<Agendamento> query = getEmc().getEntityManager().createNamedQuery("valida.agendamento", Agendamento.class);
            query.setParameter("inicioAtendimento", inicioAtendimento);
            query.setParameter("fimAtendimento", fimAtendimento);
            query.setParameter("medico", idMedico);

            List<Agendamento> agendamentos = query.getResultList();
            if (!agendamentos.isEmpty()) {
                System.out.println("Horário já reservado para outro agendamento: " + agendamentos.get(0));
                return null;
            }

            Agendamento novoAgendamento = new Agendamento();
            novoAgendamento.setDataHoraInicio(inicioAtendimento);
            novoAgendamento.setDataHoraFim(fimAtendimento);
            getEmc().getEntityManager().persist(novoAgendamento);

            getEmc().getEntityManager().getTransaction().commit();
            System.out.println("Atendimento agendado para " + inicioAtendimento + " - " + fimAtendimento);
            return novoAgendamento;
        } catch (Exception e) {
            getEmc().getEntityManager().getTransaction().rollback();
            System.out.println("Erro ao agendar: " + e.getMessage());
            return null;
        }
    }

    public void removerAgendamento(Agendamento agendamento) {
        getEmc().getEntityManager().getTransaction().begin();
        List<Agendamento> agendados = listarAgendamentos();
        agendados.remove(agendamento);
        getEmc().getEntityManager().getTransaction().commit();
        System.out.println("Agendamento removido com sucesso!");
    }

    public void atualizarAgendamento(Agendamento agendamento) {
        getEmc().getEntityManager().getTransaction().begin();
        getEmc().getEntityManager().merge(agendamento);
        getEmc().getEntityManager().getTransaction().commit();
        System.out.println("Agendamento atualizado com sucesso!");
    }


}



