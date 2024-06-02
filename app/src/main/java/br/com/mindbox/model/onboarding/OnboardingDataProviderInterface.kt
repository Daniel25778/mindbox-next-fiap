package br.com.mindbox.model.onboarding

interface OnboardingDataProviderInterface {
        fun getItems(): List<OnboardingItem>
}