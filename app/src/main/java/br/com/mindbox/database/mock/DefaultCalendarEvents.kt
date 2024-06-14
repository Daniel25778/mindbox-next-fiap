package br.com.mindbox.database.mock

import br.com.mindbox.model.calendar.CalendarEvent
import java.util.Date

class DefaultCalendarEvents {
    companion object {
        fun get(): List<CalendarEvent> {
            return listOf(
                CalendarEvent(
                    title = "Reunião de Projeto",
                    description = "Vamos nos reunir para discutir o projeto na sexta-feira.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(1L, 2L),
                    creatorId = 1L
                ),
                CalendarEvent(
                    title = "Relatório Financeiro",
                    description = "Por favor, revise o relatório financeiro para o trimestre.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(3L, 4L),
                    creatorId = 2L
                ),
                // Adicione mais eventos conforme necessário
                CalendarEvent(
                    title = "Brainstorm de Ideias",
                    description = "Vamos realizar uma sessão de brainstorm para gerar novas ideias.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(1L, 3L, 5L),
                    creatorId = 3L
                ),
                CalendarEvent(
                    title = "Apresentação de Vendas",
                    description = "Apresentação dos resultados de vendas do mês.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(2L, 4L),
                    creatorId = 4L
                ),
                CalendarEvent(
                    title = "Conferência de Marketing",
                    description = "Participação na conferência sobre estratégias de marketing.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(1L, 2L, 3L, 4L, 5L),
                    creatorId = 1L
                ),
                CalendarEvent(
                    title = "Entrega do Projeto",
                    description = "Entrega final do projeto para o cliente.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(2L, 4L, 6L, 3L),
                    creatorId = 6L
                ),
                CalendarEvent(
                    title = "Sessão de Treinamento",
                    description = "Treinamento para novos funcionários.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(1L, 3L),
                    creatorId = 2L
                ),
                CalendarEvent(
                    title = "Planejamento Estratégico",
                    description = "Revisão do plano estratégico para o próximo trimestre.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(2L, 4L, 5L),
                    creatorId = 5L
                ),
                CalendarEvent(
                    title = "Avaliação de Desempenho",
                    description = "Reunião para avaliação de desempenho dos funcionários.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(1L, 3L, 4L),
                    creatorId = 1L
                ),
                CalendarEvent(
                    title = "Lançamento de Produto",
                    description = "Lançamento do novo produto no mercado.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(2L, 3L, 5L),
                    creatorId = 3L
                ),
                CalendarEvent(
                    title = "Conferência de Tecnologia",
                    description = "Participação na conferência sobre novas tecnologias.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(1L, 4L),
                    creatorId = 4L
                ),
                CalendarEvent(
                    title = "Reunião de Equipe",
                    description = "Reunião semanal da equipe para alinhamento de atividades.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(1L, 2L, 3L, 4L, 5L),
                    creatorId = 5L
                ),
                CalendarEvent(
                    title = "Entrega de Relatório",
                    description = "Entrega do relatório de análise de mercado.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(2L, 3L),
                    creatorId = 1L
                ),
                CalendarEvent(
                    title = "Sessão de Feedback",
                    description = "Sessão de feedback entre líderes e suas equipes.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(1L, 2L, 3L, 4L, 5L),
                    creatorId = 3L
                ),
                CalendarEvent(
                    title = "Auditoria Interna",
                    description = "Auditoria interna para avaliação de processos.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(1L, 4L),
                    creatorId = 5L
                ),
                CalendarEvent(
                    title = "Curso de Aperfeiçoamento",
                    description = "Participação em curso de aperfeiçoamento profissional.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(2L, 3L),
                    creatorId = 2L
                ),
                CalendarEvent(
                    title = "Sessão de Planejamento",
                    description = "Sessão de planejamento para definição de metas trimestrais.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(1L, 2L, 3L, 4L, 5L),
                    creatorId = 1L
                ),
                CalendarEvent(
                    title = "Entrevistas de Emprego",
                    description = "Realização de entrevistas para novas contratações.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(3L, 4L),
                    creatorId = 3L
                ),
                CalendarEvent(
                    title = "Apresentação de Projetos",
                    description = "Apresentação dos projetos em andamento para a diretoria.",
                    startDate = Date(),
                    endDate = Date(),
                    participantsId = listOf(1L, 2L, 4L),
                    creatorId = 5L
                ),
            )
        }
    }
}


