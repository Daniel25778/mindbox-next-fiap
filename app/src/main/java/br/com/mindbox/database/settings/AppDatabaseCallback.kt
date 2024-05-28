import android.annotation.SuppressLint
import android.content.Context
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import br.com.mindbox.database.settings.ConfigDb
import br.com.mindbox.dto.email.SendEmailDTO
import br.com.mindbox.dto.emailTask.CreateEmailTaskDTO
import br.com.mindbox.dto.user.RegisterUserDTO
import br.com.mindbox.model.email.EmailCategory
import br.com.mindbox.model.email.EmailCategoryName
import br.com.mindbox.model.email.EmailTask
import br.com.mindbox.service.AuthorizationService
import br.com.mindbox.service.EmailService
import br.com.mindbox.util.date.DateUtils
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class AppDatabaseCallback(private val context: Context) : RoomDatabase.Callback() {

    override fun onCreate(db: SupportSQLiteDatabase) {
        super.onCreate(db)
        populateDatabase()
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun populateDatabase() {
        val database = ConfigDb.getDatabase(context)
        val authorizationService = AuthorizationService(context)
        val emailService = EmailService(context)

        GlobalScope.launch {
            database.emailCategoryDAO().insertAll(getDefaultCategories())
            val registerUserDTOs = getDefaultRegisterUserDTOs()
            registerUserDTOs.forEach { registerUserDTO ->
                authorizationService.registerUser(registerUserDTO)
            }

            val sendEmailDTOs = getDefaultSendEmailDTOs()
            sendEmailDTOs.forEach { sendEmailDTO ->
                emailService.sendMail(sendEmailDTO)
            }
        }
    }

    companion object {
        @SuppressLint("PrivateResource")
        private fun getDefaultCategories(): List<EmailCategory> {
            return listOf(
                EmailCategory(
                    name = EmailCategoryName.EDUCATION,
                    iconResId = androidx.core.R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.WORK,
                    iconResId = androidx.core.R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.PERSONAL,
                    iconResId = androidx.core.R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.PROMOTION,
                    iconResId = androidx.core.R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.SOCIAL,
                    iconResId = androidx.core.R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.FINANCE,
                    iconResId = androidx.core.R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.HEALTH,
                    iconResId = androidx.core.R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.TRAVEL,
                    iconResId = androidx.core.R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.SHOPPING,
                    iconResId = androidx.core.R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.NEWSLETTER,
                    iconResId = androidx.core.R.drawable.ic_call_decline
                ), EmailCategory(
                    name = EmailCategoryName.OTHERS,
                    iconResId = androidx.core.R.drawable.ic_call_decline
                )
            )
        }

        private fun getDefaultRegisterUserDTOs(): List<RegisterUserDTO> {
            val format = DateUtils.getFormat()
            return listOf(
                RegisterUserDTO(
                    email = "gilberto@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Gilberto Mautner",
                    birthDate = format.parse("1980-02-01")!!
                ),
                RegisterUserDTO(
                    email = "claudio@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Claudio Gora",
                    birthDate = format.parse("1980-02-01")!!
                ),
                RegisterUserDTO(
                    email = "gabriel@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Gabriel Tavares",
                    birthDate = format.parse("1980-02-01")!!
                ),
                RegisterUserDTO(
                    email = "daniel@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Daniel Wilson",
                    birthDate = format.parse("1990-04-22")!!
                ),
                RegisterUserDTO(
                    email = "alexia@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Alexia",
                    birthDate = format.parse("1978-11-30")!!
                ),
                RegisterUserDTO(
                    email = "gui@locaweb.com.br",
                    password = "Senha@123",
                    fullName = "Ivanor Guilherme",
                    birthDate = format.parse("1978-11-30")!!
                ),
            )
        }

        private fun getDefaultSendEmailDTOs(): List<SendEmailDTO> {
            return listOf(
                SendEmailDTO(
                    senderId = 1,
                    recipientIds = listOf(2L, 3L),
                    subject = "Reunião de Projeto",
                    text = "Olá, vamos nos reunir para discutir o projeto na sexta-feira.",
                    tasks = listOf(
                        CreateEmailTaskDTO(
                            description = "Preparar apresentação do projeto",
                        ), CreateEmailTaskDTO(
                            description = "Revisar documentos do projeto",
                        )
                    ),
                ), SendEmailDTO(
                    senderId = 2,
                    recipientIds = listOf(1L, 3L),
                    subject = "Relatório Financeiro",
                    text = "Por favor, revise o relatório financeiro para o trimestre.",
                    tasks = listOf(
                        CreateEmailTaskDTO(
                            description = "Revisar relatório de vendas",
                        ), CreateEmailTaskDTO(
                            description = "Analisar despesas trimestrais",
                        )
                    ),
                ), SendEmailDTO(
                    senderId = 3,
                    recipientIds = listOf(1L, 2L),
                    subject = "Evento de Networking",
                    text = "Convido vocês para um evento de networking na próxima semana.",
                    tasks = listOf(
                        CreateEmailTaskDTO(
                            description = "Confirmar presença no evento",
                        ), CreateEmailTaskDTO(
                            description = "Preparar cartões de visita",
                        )
                    ),
                ), SendEmailDTO(
                    senderId = 4,
                    recipientIds = listOf(1L, 2L),
                    subject = "Atualização de Projeto",
                    text = "Gostaria de compartilhar algumas atualizações importantes sobre o projeto.",
                    tasks = listOf(
                        CreateEmailTaskDTO(
                            description = "Apresentar novas ideias para o projeto",
                        ), CreateEmailTaskDTO(
                            description = "Solicitar feedback dos membros da equipe",
                        )
                    ),
                ), SendEmailDTO(
                    senderId = 5,
                    recipientIds = listOf(3L, 4L),
                    subject = "Feedback de Apresentação",
                    text = "Por favor, forneça seu feedback sobre a apresentação de vendas realizada na semana passada.",
                    tasks = listOf(
                        CreateEmailTaskDTO(
                            description = "Analisar feedback dos clientes",
                        ), CreateEmailTaskDTO(
                            description = "Identificar áreas de melhoria",
                        )
                    ),
                ), SendEmailDTO(
                    senderId = 1,
                    recipientIds = listOf(2L, 5L),
                    subject = "Planejamento de Projeto",
                    text = "Vamos revisar o plano do projeto e fazer os ajustes necessários.",
                    tasks = listOf(
                        CreateEmailTaskDTO(
                            description = "Revisar cronograma do projeto",
                        ), CreateEmailTaskDTO(
                            description = "Atribuir tarefas atualizadas aos membros da equipe",
                        )
                    ),
                ), SendEmailDTO(
                    senderId = 3,
                    recipientIds = listOf(1L, 4L),
                    subject = "Convite para Conferência",
                    text = "Você está convidado para participar da conferência sobre inovação tecnológica.",
                    tasks = listOf(
                        CreateEmailTaskDTO(
                            description = "Registrar-se para a conferência",
                        ), CreateEmailTaskDTO(
                            description = "Preparar materiais de apresentação",
                        )
                    ),
                ), SendEmailDTO(
                    senderId = 2,
                    recipientIds = listOf(3L, 5L),
                    subject = "Feedback de Produto",
                    text = "Estamos buscando feedback dos usuários sobre o novo produto lançado.",
                    tasks = listOf(
                        CreateEmailTaskDTO(
                            description = "Coletar opiniões dos clientes",
                        ), CreateEmailTaskDTO(
                            description = "Analisar dados de utilização do produto",
                        )
                    ),
                ), SendEmailDTO(
                    senderId = 4,
                    recipientIds = listOf(1L, 3L),
                    subject = "Convite para Almoço",
                    text = "Convido você para um almoço informal para discutir novas ideias.",
                    tasks = listOf(
                        CreateEmailTaskDTO(
                            description = "Confirmar disponibilidade para o almoço",
                        ), CreateEmailTaskDTO(
                            description = "Selecionar local e horário",
                        )
                    ),
                ), SendEmailDTO(
                    senderId = 5,
                    recipientIds = listOf(2L, 4L),
                    subject = "Discussão de Estratégia",
                    text = "Vamos discutir a estratégia de marketing para o próximo trimestre.",
                    tasks = listOf(
                        CreateEmailTaskDTO(
                            description = "Brainstorm de ideias para campanhas",
                        ), CreateEmailTaskDTO(
                            description = "Definir objetivos de vendas",
                        )
                    ),
                )
            )
        }

    }
}
