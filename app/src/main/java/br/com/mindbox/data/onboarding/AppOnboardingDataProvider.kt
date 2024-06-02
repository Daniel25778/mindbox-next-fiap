package br.com.mindbox.data.onboarding

import br.com.mindbox.R
import br.com.mindbox.model.onboarding.OnboardingDataProviderInterface
import br.com.mindbox.model.onboarding.OnboardingItem

class AppOnboardingDataProvider : OnboardingDataProviderInterface {
    override fun getItems(): List<OnboardingItem> {
        return listOf(
            OnboardingItem(
                R.raw.app_onboarding_image_1,
                "Define Your Goal",
                "Desperte para a revolução do e-mail: sua caixa de entrada nunca foi tão inteligente e moderna.",
            ),
            OnboardingItem(
                R.raw.app_onboarding_image_2,
                "Green Marketing",
                "Deixe a inteligência artificial organizar sua vida digital: simplificando sua caixa de entrada, um e-mail de cada vez.",
            ),
            OnboardingItem(
                R.raw.app_onboarding_image_3,
                "Secured Storage",
                "Converse com eficiência: nosso chat com IA está sempre pronto para  ajudar a redigir suas respostas de e-mail de forma inteligente e rápida.",
            ),
        )
    }
}