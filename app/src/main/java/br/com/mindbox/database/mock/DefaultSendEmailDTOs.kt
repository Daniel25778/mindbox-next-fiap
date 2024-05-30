package br.com.mindbox.database.mock

import br.com.mindbox.dto.email.SendEmailDTO
import br.com.mindbox.dto.emailTask.CreateEmailTaskDTO

class DefaultSendEmailDTOs {
    companion object {
        fun get(): List<SendEmailDTO> {
            return listOf(
                // Education Emails
                SendEmailDTO(
                    senderId = 1,
                    recipientIds = listOf(2L, 3L, 5L),
                    subject = "Escola - Aula de Matemática",
                    text = "Lembrete: Aula de matemática amanhã às 10h.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Revisar lição de casa"),
                        CreateEmailTaskDTO(description = "Trazer livro de matemática")
                    )
                ),
                SendEmailDTO(
                    senderId = 2,
                    recipientIds = listOf(1L, 3L, 6L),
                    subject = "Seminário de Educação",
                    text = "Participe do seminário sobre novas técnicas de ensino.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Inscrever-se no seminário"),
                        CreateEmailTaskDTO(description = "Preparar apresentação")
                    )
                ),
                SendEmailDTO(
                    senderId = 3,
                    recipientIds = listOf(1L, 2L),
                    subject = "Exame Final",
                    text = "Data do exame final da universidade foi marcada para a próxima segunda-feira.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Estudar capítulos 1 a 5"),
                        CreateEmailTaskDTO(description = "Revisar notas de aula")
                    )
                ),
                SendEmailDTO(
                    senderId = 4,
                    recipientIds = listOf(1L, 2L),
                    subject = "Palestra sobre Educação Inclusiva",
                    text = "Convidamos todos para a palestra sobre educação inclusiva na quarta-feira.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Confirmar presença"),
                        CreateEmailTaskDTO(description = "Preparar perguntas")
                    )
                ),
                SendEmailDTO(
                    senderId = 5,
                    recipientIds = listOf(3L, 4L),
                    subject = "Workshop de Aprendizagem Ativa",
                    text = "Participe do workshop de aprendizagem ativa neste sábado.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Levar materiais necessários"),
                        CreateEmailTaskDTO(description = "Praticar atividades prévias")
                    )
                ),
                // Work Emails
                SendEmailDTO(
                    senderId = 1,
                    recipientIds = listOf(2L, 3L),
                    subject = "Reunião de Projeto",
                    text = "Olá, vamos nos reunir para discutir o projeto na sexta-feira.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Preparar apresentação do projeto"),
                        CreateEmailTaskDTO(description = "Revisar documentos do projeto")
                    )
                ),
                SendEmailDTO(
                    senderId = 2,
                    recipientIds = listOf(1L, 3L),
                    subject = "Relatório Semanal",
                    text = "Por favor, envie o relatório semanal até quinta-feira.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Coletar dados de vendas"),
                        CreateEmailTaskDTO(description = "Escrever resumo do relatório")
                    )
                ),
                SendEmailDTO(
                    senderId = 3,
                    recipientIds = listOf(1L, 2L),
                    subject = "Planejamento de Sprint",
                    text = "Vamos definir as tarefas para a próxima sprint na reunião de segunda-feira.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Listar tarefas prioritárias"),
                        CreateEmailTaskDTO(description = "Atualizar backlog do projeto")
                    )
                ),
                SendEmailDTO(
                    senderId = 4,
                    recipientIds = listOf(1L, 2L),
                    subject = "Treinamento de Equipe",
                    text = "Lembrete: Treinamento de equipe na sexta-feira às 14h.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Preparar materiais de treinamento"),
                        CreateEmailTaskDTO(description = "Enviar convites para a equipe")
                    )
                ),
                SendEmailDTO(
                    senderId = 5,
                    recipientIds = listOf(3L, 4L),
                    subject = "Avaliação de Desempenho",
                    text = "A avaliação de desempenho do trabalho será realizada na próxima semana.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Preencher formulário de autoavaliação"),
                        CreateEmailTaskDTO(description = "Agendar reunião com o gestor")
                    )
                ),
                // Personal Emails
                SendEmailDTO(
                    senderId = 1,
                    recipientIds = listOf(2L, 3L, 5L, 6L),
                    subject = "Festa de Aniversário",
                    text = "Você está convidado para a minha festa de aniversário neste sábado.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Confirmar presença"),
                        CreateEmailTaskDTO(description = "Trazer presente")
                    )
                ),
                SendEmailDTO(
                    senderId = 2,
                    recipientIds = listOf(1L, 3L, 5L, 6L),
                    subject = "Jantar com Amigos",
                    text = "Vamos organizar um jantar com amigos na próxima sexta-feira.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Escolher menu"),
                        CreateEmailTaskDTO(description = "Enviar convites")
                    )
                ),
                SendEmailDTO(
                    senderId = 5,
                    recipientIds = listOf(1L, 2L),
                    subject = "Viagem de Férias",
                    text = "Estou planejando uma viagem de férias para o próximo mês.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Reservar voos"),
                        CreateEmailTaskDTO(description = "Fazer itinerário de viagem")
                    )
                ),
                SendEmailDTO(
                    senderId = 6,
                    recipientIds = listOf(1L, 2L),
                    subject = "Almoço em Família",
                    text = "Lembrete: Almoço em família no domingo ao meio-dia.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Preparar prato principal"),
                        CreateEmailTaskDTO(description = "Trazer sobremesa")
                    )
                ),
                SendEmailDTO(
                    senderId = 5,
                    recipientIds = listOf(3L, 4L),
                    subject = "Passeio no Parque",
                    text = "Vamos fazer um passeio no parque no sábado à tarde.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Levar lanches"),
                        CreateEmailTaskDTO(description = "Organizar atividades ao ar livre")
                    )
                ),
                // Promotion Emails
                SendEmailDTO(
                    senderId = 1,
                    recipientIds = listOf(2L, 3L),
                    subject = "Promoção de Verão",
                    text = "Aproveite nossa promoção de verão com descontos de até 50%.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Divulgar promoção nas redes sociais"),
                        CreateEmailTaskDTO(description = "Atualizar site com preços promocionais")
                    )
                ),
                SendEmailDTO(
                    senderId = 2,
                    recipientIds = listOf(1L, 3L),
                    subject = "Black Friday",
                    text = "Preparamos ofertas imperdíveis para a Black Friday.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Enviar newsletter"),
                        CreateEmailTaskDTO(description = "Configurar descontos no e-commerce")
                    )
                ),
                SendEmailDTO(
                    senderId = 3,
                    recipientIds = listOf(1L, 2L),
                    subject = "Liquidação de Inverno",
                    text = "Nossa liquidação de inverno começa na próxima semana.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Preparar material de marketing"),
                        CreateEmailTaskDTO(description = "Organizar produtos em promoção")
                    )
                ),
                SendEmailDTO(
                    senderId = 4,
                    recipientIds = listOf(1L, 2L),
                    subject = "Desconto para Novos Clientes",
                    text = "Novos clientes recebem 20% de desconto na primeira compra.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Criar código de desconto"),
                        CreateEmailTaskDTO(description = "Enviar e-mail para novos assinantes")
                    )
                ),
                SendEmailDTO(
                    senderId = 5,
                    recipientIds = listOf(3L, 4L),
                    subject = "Promoção de Aniversário",
                    text = "Celebre nosso aniversário com descontos especiais.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Lançar campanha de aniversário"),
                        CreateEmailTaskDTO(description = "Oferecer brindes para compras acima de $100")
                    )
                ),
                // Finance Emails
                SendEmailDTO(
                    senderId = 1,
                    recipientIds = listOf(2L, 3L),
                    subject = "Resumo de Conta",
                    text = "Seu resumo do dinheiro na conta do mês está disponível.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Revisar resumo de conta"),
                        CreateEmailTaskDTO(description = "Verificar transações recentes")
                    )
                ),
                SendEmailDTO(
                    senderId = 2,
                    recipientIds = listOf(1L, 3L),
                    subject = "Investimentos de Alto Rendimento",
                    text = "Descubra nossas opções de investimentos de alto rendimento.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Analisar opções de investimento"),
                        CreateEmailTaskDTO(description = "Agendar consulta com consultor financeiro")
                    )
                ),
                SendEmailDTO(
                    senderId = 3,
                    recipientIds = listOf(1L, 2L),
                    subject = "Planejamento Financeiro",
                    text = "Oferecemos serviços de planejamento financeiro personalizado.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Preencher questionário financeiro"),
                        CreateEmailTaskDTO(description = "Agendar reunião com consultor")
                    )
                ),
                SendEmailDTO(
                    senderId = 4,
                    recipientIds = listOf(1L, 2L),
                    subject = "Relatório de Desempenho de Investimentos",
                    text = "Seu relatório de desempenho de investimentos está disponível.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Revisar relatório"),
                        CreateEmailTaskDTO(description = "Ajustar portfólio de investimentos")
                    )
                ),
                SendEmailDTO(
                    senderId = 5,
                    recipientIds = listOf(3L, 4L),
                    subject = "Atualização de Financeiro",
                    text = "Atualize suas informações de conta corrente para evitar interrupções.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Enviar documentação necessária"),
                        CreateEmailTaskDTO(description = "Atualizar dados no sistema")
                    )
                ),
                // Health Emails
                SendEmailDTO(
                    senderId = 1,
                    recipientIds = listOf(2L, 3L),
                    subject = "Consulta Médica",
                    text = "Lembrete: Você tem uma consulta médica agendada para amanhã às 15h.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Confirmar consulta"),
                        CreateEmailTaskDTO(description = "Trazer resultados de exames")
                    )
                ),
                SendEmailDTO(
                    senderId = 2,
                    recipientIds = listOf(1L, 3L),
                    subject = "Campanha de Vacinação",
                    text = "Participe da campanha de vacinação contra a gripe neste sábado.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Agendar vacinação"),
                        CreateEmailTaskDTO(description = "Preencher formulário de vacinação")
                    )
                ),
                SendEmailDTO(
                    senderId = 3,
                    recipientIds = listOf(1L, 2L),
                    subject = "Exames Preventivos",
                    text = "É importante realizar exames preventivos regularmente.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Agendar exames preventivos"),
                        CreateEmailTaskDTO(description = "Levar histórico médico")
                    )
                ),
                SendEmailDTO(
                    senderId = 4,
                    recipientIds = listOf(1L, 2L),
                    subject = "Programa de Fitness",
                    text = "Inscreva-se no nosso programa de fitness para uma vida mais saudável.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Inscrever-se no programa"),
                        CreateEmailTaskDTO(description = "Trazer roupa de ginástica")
                    )
                ),
                SendEmailDTO(
                    senderId = 5,
                    recipientIds = listOf(3L, 4L),
                    subject = "Workshop de Nutrição",
                    text = "Participe do nosso workshop de nutrição saudável na próxima quarta-feira.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Inscrever-se no workshop"),
                        CreateEmailTaskDTO(description = "Preparar perguntas para o nutricionista")
                    )
                ),
                // Travel Emails
                SendEmailDTO(
                    senderId = 1,
                    recipientIds = listOf(2L, 3L),
                    subject = "Planejamento de Viagem",
                    text = "Vamos planejar nossa viagem de férias para o próximo mês.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Reservar voos"),
                        CreateEmailTaskDTO(description = "Selecionar hotéis")
                    )
                ),
                SendEmailDTO(
                    senderId = 2,
                    recipientIds = listOf(1L, 3L),
                    subject = "Roteiro de Viagem",
                    text = "Confira nosso roteiro de viagem para a próxima semana.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Imprimir itinerário"),
                        CreateEmailTaskDTO(description = "Confirmar reservas")
                    )
                ),
                SendEmailDTO(
                    senderId = 3,
                    recipientIds = listOf(1L, 2L),
                    subject = "Excursão Turística",
                    text = "Participe da nossa excursão turística no próximo fim de semana.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Inscrever-se na excursão"),
                        CreateEmailTaskDTO(description = "Levar câmera fotográfica")
                    )
                ),
                SendEmailDTO(
                    senderId = 4,
                    recipientIds = listOf(1L, 2L),
                    subject = "Férias na Praia",
                    text = "Vamos passar as férias na praia em janeiro.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Reservar hotel na praia"),
                        CreateEmailTaskDTO(description = "Preparar lista de atividades")
                    )
                ),
                SendEmailDTO(
                    senderId = 5,
                    recipientIds = listOf(3L, 4L),
                    subject = "Aventura de Caminhada",
                    text = "Participe da nossa aventura de caminhada nas montanhas.",
                    tasks = listOf(
                        CreateEmailTaskDTO(description = "Comprar equipamento de caminhada"),
                        CreateEmailTaskDTO(description = "Trazer lanche e água")
                    )
                ),
                //Newsletter Emails
                SendEmailDTO(
                    senderId = 1,
                    recipientIds = listOf(2L, 3L),
                    subject = "Últimas Notícias da Semana",
                    text = "Confira as últimas notícias da semana em nossa newsletter!",
                    tasks = emptyList()
                ),
                SendEmailDTO(
                    senderId = 2,
                    recipientIds = listOf(1L, 3L),
                    subject = "Novidades e Atualizações",
                    text = "Fique por dentro das novidades e atualizações em nossa newsletter mensal.",
                    tasks = emptyList()
                ),
                SendEmailDTO(
                    senderId = 3,
                    recipientIds = listOf(1L, 2L),
                    subject = "Destaques da Indústria",
                    text = "Os principais destaques da indústria em nossa newsletter quinzenal.",
                    tasks = emptyList()
                ),
                SendEmailDTO(
                    senderId = 4,
                    recipientIds = listOf(1L, 2L, 3L),
                    subject = "Resumo Semanal",
                    text = "Confira nosso resumo semanal com as notícias mais importantes.",
                    tasks = emptyList()
                ),
                SendEmailDTO(
                    senderId = 5,
                    recipientIds = listOf(2L, 4L),
                    subject = "Melhores Artigos do Mês",
                    text = "Veja os melhores artigos do mês em nossa newsletter especial.",
                    tasks = emptyList()
                )
            )
        }
    }
}