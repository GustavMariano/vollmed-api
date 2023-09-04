package med.voll.api.infra.exception.consulta;

import java.time.DayOfWeek;

import org.springframework.stereotype.Component;

import med.voll.api.dto.AgendamentoConsultaDto;

@Component
public class ValidadorHorarioFuncionamentoClinica implements ValidadorAgendamentoDeConsulta{
    
    public void validar(AgendamentoConsultaDto dados) {
        var dataConsulta = dados.data();

        var domingo = dataConsulta.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var aberturaDaClinica = dataConsulta.getHour() < 7;
        var encerramentoDaClinica = dataConsulta.getHour() > 18;
        if (domingo || aberturaDaClinica || encerramentoDaClinica) {
            throw new ValidacaoException("Consulta fora do horário de funcionamento da clínica");
        }
    }
}
