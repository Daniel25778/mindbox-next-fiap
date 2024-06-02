package br.com.mindbox.data.onboarding

import br.com.mindbox.R
import br.com.mindbox.model.onboarding.OnboardingDataProviderInterface
import br.com.mindbox.model.onboarding.OnboardingItem

class ChatOnboardingDataProvider : OnboardingDataProviderInterface {
    override fun getItems(): List<OnboardingItem> {
        return listOf(
            OnboardingItem(
                R.raw.chat_onboarding_stars,
                "Chat com IA generativa",
                "Nosso chat com IA está pronto para tornar sua comunicação por e-mail mais inteligente e eficiente.",
            ),
            OnboardingItem(
                R.raw.chat_onboarding_locaweb_creators,
                "Homenagem aos criadores",
                "O nome de nossa assistente virtual é uma homenagem aos visionários que inspiraram nossa jornada: Gilberto Mautner e Claudio Gora, fundadores da Locaweb.",
            ),
            OnboardingItem(
                R.raw.chat_onboarding_goma,
                "Goma. Assistente virtual",
                "O nome de nossa assistente virtual é uma homenagem aos visionários que inspiraram nossa jornada: Gilberto Mautner e Claudio Gora, fundadores da Locaweb.",
            ),
        )
    }
}