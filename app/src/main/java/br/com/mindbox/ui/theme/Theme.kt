package br.com.mindbox.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val DarkColorScheme = darkColorScheme(
    primary =LayerMid, // Cor principal para elementos primários (TopAppBar, botões principais)
    secondary = Grey350, // Cor secundária para elementos que complementam os principais (botões secundários)
    tertiary = PurpleMid, // Cor terciária para acentos adicionais e elementos menos importantes

    background = Primary, // Cor de fundo da aplicação
    surface = LayerMid, // Cor das superfícies de componentes (cartões, folhas de fundo)
    onPrimary = Color.White, // Cor do texto/ícones sobre a cor principal
    onSecondary = Color.White, // Cor do texto/ícones sobre a cor secundária
    onTertiary = Color.White, // Cor do texto/ícones sobre a cor terciária
    onBackground = Layer, // Cor do texto/ícones sobre a cor de fundo
    onSurface = Layer, // Cor do texto/ícones sobre a cor das superfícies

    // agora vamos la, ó cache limpinho, mas o cara ja ta logado deixa o zap no outro minitor
)

private val LightColorScheme = lightColorScheme(
    primary = LayerMid, // Cor principal para elementos primários (TopAppBar, botões principais)
    secondary = Grey350, // Cor secundária para elementos que complementam os principais (botões secundários)
    tertiary = PurpleMid, // Cor terciária para acentos adicionais e elementos menos importantes

    background = Primary, // Cor de fundo da aplicação
    surface = LayerMid, // Cor das superfícies de componentes (cartões, folhas de fundo)
    onPrimary = Color.White, // Cor do texto/ícones sobre a cor principal
    onSecondary = Color.White, // Cor do texto/ícones sobre a cor secundária
    onTertiary = Color.White, // Cor do texto/ícones sobre a cor terciária
    onBackground = Layer, // Cor do texto/ícones sobre a cor de fundo
    onSurface = Layer, // Cor do texto/ícones sobre a cor das superfícies
)


@Composable
fun Theme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}