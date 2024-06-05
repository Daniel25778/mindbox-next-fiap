package br.com.mindbox.components

import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import br.com.mindbox.model.navbottom.NavBottomItem

@Composable
fun loadNavBottomItemsWithIcons(items: List<NavBottomItem>): List<NavBottomItem> {
    return items.map { item ->
        NavBottomItem(
            title = item.title,
            url = item.url,
            selectedIcon = painterResource(id = item.painterResourceSelectedIcon),
            unselectedIcon = painterResource(id = item.painterResourceUnselectedIcon),
            hasNews = item.hasNews,
            painterResourceSelectedIcon = item.painterResourceSelectedIcon,
            painterResourceUnselectedIcon = item.painterResourceUnselectedIcon
        )
    }
}