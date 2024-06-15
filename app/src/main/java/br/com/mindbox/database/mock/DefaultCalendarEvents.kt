package br.com.mindbox.database.mock

import br.com.mindbox.model.calendar.CalendarEvent
import br.com.mindbox.util.date.DateUtils

class DefaultCalendarEvents {
    companion object {
        fun get(): List<CalendarEvent> {
            val formatter = DateUtils.getDateTimeFormat()
            return listOf(
                CalendarEvent(
                    title = "Reunião de Projeto",
                    description = "Vamos nos reunir para discutir o projeto na sexta-feira.",
                    startDate = formatter.parse("2024-06-15 09:00:00"),
                    endDate = formatter.parse("2024-06-15 11:30:00"),
                    creatorId = 1L
                ),
                CalendarEvent(
                    title = "Relatório Financeiro",
                    description = "Por favor, revise o relatório financeiro para o trimestre.",
                    startDate = formatter.parse("2024-06-16 08:30:00"),
                    endDate = formatter.parse("2024-06-16 11:00:00"),
                    creatorId = 2L
                ),
                CalendarEvent(
                    title = "Brainstorm de Ideias",
                    description = "Vamos realizar uma sessão de brainstorm para gerar novas ideias.",
                    startDate = formatter.parse("2024-06-17 10:00:00"),
                    endDate = formatter.parse("2024-06-17 12:30:00"),
                    creatorId = 3L
                ),
                CalendarEvent(
                    title = "Apresentação de Vendas",
                    description = "Apresentação dos resultados de vendas do mês.",
                    startDate = formatter.parse("2024-06-18 09:30:00"),
                    endDate = formatter.parse("2024-06-18 12:00:00"),
                    creatorId = 4L
                ),
                CalendarEvent(
                    title = "Conferência de Marketing",
                    description = "Participação na conferência sobre estratégias de marketing.",
                    startDate = formatter.parse("2024-06-19 08:00:00"),
                    endDate = formatter.parse("2024-06-19 10:30:00"),
                    creatorId = 1L
                ),
                CalendarEvent(
                    title = "Entrega do Projeto",
                    description = "Entrega final do projeto para o cliente.",
                    startDate = formatter.parse("2024-06-21 10:30:00"),
                    endDate = formatter.parse("2024-06-21 13:00:00"),
                    creatorId = 6L
                ),
                CalendarEvent(
                    title = "Sessão de Treinamento",
                    description = "Treinamento para novos funcionários.",
                    startDate = formatter.parse("2024-06-22 08:30:00"),
                    endDate = formatter.parse("2024-06-22 11:00:00"),
                    creatorId = 2L
                ),
                CalendarEvent(
                    title = "Planejamento Estratégico",
                    description = "Revisão do plano estratégico para o próximo trimestre.",
                    startDate = formatter.parse("2024-06-23 09:00:00"),
                    endDate = formatter.parse("2024-06-23 11:30:00"),
                    creatorId = 5L
                ),
                CalendarEvent(
                    title = "Avaliação de Desempenho",
                    description = "Reunião para avaliação de desempenho dos funcionários.",
                    startDate = formatter.parse("2024-06-24 08:00:00"),
                    endDate = formatter.parse("2024-06-24 10:30:00"),
                    creatorId = 1L
                ),
                CalendarEvent(
                    title = "Lançamento de Produto",
                    description = "Lançamento do novo produto no mercado.",
                    startDate = formatter.parse("2024-06-25 09:30:00"),
                    endDate = formatter.parse("2024-06-25 12:00:00"),
                    creatorId = 3L
                ),
                CalendarEvent(
                    title = "Conferência de Tecnologia",
                    description = "Participação na conferência sobre novas tecnologias.",
                    startDate = formatter.parse("2024-06-26 10:00:00"),
                    endDate = formatter.parse("2024-06-26 12:30:00"),
                    creatorId = 4L
                ),
                CalendarEvent(
                    title = "Reunião de Equipe",
                    description = "Reunião semanal da equipe para alinhamento de atividades.",
                    startDate = formatter.parse("2024-06-27 08:30:00"),
                    endDate = formatter.parse("2024-06-27 11:00:00"),
                    creatorId = 5L
                ),
                CalendarEvent(
                    title = "Entrega de Relatório",
                    description = "Entrega do relatório de análise de mercado.",
                    startDate = formatter.parse("2024-06-29 08:00:00"),
                    endDate = formatter.parse("2024-06-29 10:30:00"),
                    creatorId = 1L
                ),
                CalendarEvent(
                    title = "Sessão de Feedback",
                    description = "Sessão de feedback entre líderes e suas equipes.",
                    startDate = formatter.parse("2024-06-30 10:30:00"),
                    endDate = formatter.parse("2024-06-30 13:00:00"),
                    creatorId = 3L
                ),
                CalendarEvent(
                    title = "Auditoria Interna",
                    description = "Auditoria interna para avaliação de processos.",
                    startDate = formatter.parse("2024-07-01 08:30:00"),
                    endDate = formatter.parse("2024-07-01 11:00:00"),
                    creatorId = 5L
                ),
                CalendarEvent(
                    title = "Curso de Aperfeiçoamento",
                    description = "Participação em curso de aperfeiçoamento profissional.",
                    startDate = formatter.parse("2024-07-02 09:00:00"),
                    endDate = formatter.parse("2024-07-02 11:30:00"),
                    creatorId = 2L
                ),
                CalendarEvent(
                    title = "Sessão de Planejamento",
                    description = "Sessão de planejamento para definição de metas trimestrais.",
                    startDate = formatter.parse("2024-07-03 08:00:00"),
                    endDate = formatter.parse("2024-07-03 10:30:00"),
                    creatorId = 1L
                ),
                CalendarEvent(
                    title = "Entrevistas de Emprego",
                    description = "Realização de entrevistas para novas contratações.",
                    startDate = formatter.parse("2024-07-02 09:00:00"),
                    endDate = formatter.parse("2024-07-02 11:30:00"),
                    creatorId = 3L
                ),
                CalendarEvent(
                    title = "Apresentação de Projetos",
                    description = "Apresentação dos projetos em andamento para a diretoria.",
                    startDate = formatter.parse("2024-07-03 08:00:00"),
                    endDate = formatter.parse("2024-07-03 10:30:00"),
                    creatorId = 5L
                ),
                CalendarEvent(
                    title = "Conferência de RH",
                    description = "Conferência sobre novas práticas de Recursos Humanos.",
                    startDate = formatter.parse("2024-07-04 14:00:00"),
                    endDate = formatter.parse("2024-07-04 16:30:00"),
                    creatorId = 4L
                ),
                CalendarEvent(
                    title = "Workshop de Inovação",
                    description = "Workshop para discutir novas ideias e práticas inovadoras.",
                    startDate = formatter.parse("2024-07-05 09:00:00"),
                    endDate = formatter.parse("2024-07-05 12:00:00"),
                    creatorId = 1L
                ),
                CalendarEvent(
                    title = "Sessão de Coaching",
                    description = "Sessão de coaching para desenvolvimento pessoal e profissional.",
                    startDate = formatter.parse("2024-07-06 14:00:00"),
                    endDate = formatter.parse("2024-07-06 16:30:00"),
                    creatorId = 3L
                ),
                CalendarEvent(
                    title = "Feira de Tecnologia",
                    description = "Participação na feira de tecnologia para conhecer novas tendências.",
                    startDate = formatter.parse("2024-07-07 10:00:00"),
                    endDate = formatter.parse("2024-07-07 12:30:00"),
                    creatorId = 4L
                ),
                CalendarEvent(
                    title = "Curso de Liderança",
                    description = "Participação no curso de desenvolvimento de liderança.",
                    startDate = formatter.parse("2024-07-08 08:30:00"),
                    endDate = formatter.parse("2024-07-08 11:00:00"),
                    creatorId = 2L
                ),
                CalendarEvent(
                    title = "Reunião de Estratégia",
                    description = "Reunião para discutir a estratégia de expansão internacional.",
                    startDate = formatter.parse("2024-07-09 09:30:00"),
                    endDate = formatter.parse("2024-07-09 12:00:00"),
                    creatorId = 1L
                ),
                CalendarEvent(
                    title = "Apresentação de Resultados",
                    description = "Apresentação dos resultados do último trimestre para investidores.",
                    startDate = formatter.parse("2024-07-10 08:00:00"),
                    endDate = formatter.parse("2024-07-10 10:30:00"),
                    creatorId = 4L
                ),
                CalendarEvent(
                    title = "Sessão de Networking",
                    description = "Sessão de networking com profissionais da área de tecnologia.",
                    startDate = formatter.parse("2024-07-11 14:00:00"),
                    endDate = formatter.parse("2024-07-11 16:30:00"),
                    creatorId = 3L
                ),
                CalendarEvent(
                    title = "Conferência de Negócios",
                    description = "Participação na conferência sobre novas práticas de negócios.",
                    startDate = formatter.parse("2024-07-12 09:00:00"),
                    endDate = formatter.parse("2024-07-12 11:30:00"),
                    creatorId = 2L
                ),
                CalendarEvent(
                    title = "Sessão de Debates",
                    description = "Sessão de debates sobre ética no ambiente corporativo.",
                    startDate = formatter.parse("2024-07-13 08:30:00"),
                    endDate = formatter.parse("2024-07-13 11:00:00"),
                    creatorId = 5L
                ),
                CalendarEvent(
                    title = "Workshop de Criatividade",
                    description = "Workshop para estimular a criatividade e inovação na equipe.",
                    startDate = formatter.parse("2024-07-14 10:00:00"),
                    endDate = formatter.parse("2024-07-14 12:30:00"),
                    creatorId = 1L
                )
            )
        }
    }
}


