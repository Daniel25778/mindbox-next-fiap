package br.com.mindbox.model.navbottom

import androidx.compose.ui.graphics.painter.Painter

class NavBottomItem(
    val title: String,
    val selectedIcon: Painter?,
    val unselectedIcon: Painter?,
    val url: String,
    val painterResourceUnselectedIcon: Int,
    val painterResourceSelectedIcon : Int,
    val hasNews: Boolean,
    val badgeCount: Int? = null
)