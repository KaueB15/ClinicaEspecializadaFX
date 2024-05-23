package br.edu.fescfafic.clicinaespecializadafx.main;

import br.edu.fescfafic.clicinaespecializadafx.dao.AgendamentoDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.MedicoDAO;
import br.edu.fescfafic.clicinaespecializadafx.dao.PacienteDAO;
import br.edu.fescfafic.clicinaespecializadafx.domain.Login;
import br.edu.fescfafic.clicinaespecializadafx.domain.Medico;
import br.edu.fescfafic.clicinaespecializadafx.domain.Paciente;

public class App2 {
    public static void main(String[] args) {
//        var pacienteDao = new PacienteDAO();
//        var medicoDao = new MedicoDAO();
//        var agendamentoDao = new AgendamentoDAO();
//
//        System.out.println("Criando Tabelas...");
//
//        // Cadastro de paciente
//        Login loginVictor = new Login();
//        loginVictor.setLogin("Victor");
//        loginVictor.setSenha("123");
//
//        var pacienteVictor = new Paciente();
//        pacienteVictor.setNome("Victor");
//        pacienteVictor.setCpf("222222222");
//        pacienteVictor.setTelefonePaciente("(83) 9 8156-9632");
//        pacienteVictor.setLogin(loginVictor);
//
//        pacienteDao.cadastrarPaciente(pacienteVictor);
//
//        // Cadastro de médico
//        var medZack = new Medico();
//        medZack.setCrm(123456);
//        medZack.setNome("Zack");
//        medZack.setCpf("20052024");
//        medZack.setEspecialidade("Embriologista");
//
//        Login loginZack = new Login();
//        loginZack.setLogin("Zack");
//        loginZack.setSenha("Zack22");
//
//        medZack.setLogin(loginZack);
//
//        medicoDao.cadastrarMedico(medZack);

//        // Agendamento
//        var agendamento = new Agendamento();
//        agendamento.setDataHora(LocalDateTime.of(2024, 2, 25, 10, 30));
//        agendamento.setIdMedico(medZack);
//        agendamento.setIdPaciente(pacienteVictor);
//
//        agendamentoDao.agendarHorario(agendamento);
        PacienteDAO pacienteDAO = new PacienteDAO();
        pacienteDAO.getEmc().getEntityManager().clear();

        System.out.println("Tabelas Geradas com Sucesso!!!!!");
    }
}


