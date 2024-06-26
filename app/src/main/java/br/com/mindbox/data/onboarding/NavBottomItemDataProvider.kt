package br.com.mindbox.data.onboarding

import br.com.mindbox.R
import br.com.mindbox.model.navbottom.NavBottomDataProviderInterface
import br.com.mindbox.model.navbottom.NavBottomItem

class NavBottomItemDataProvider: NavBottomDataProviderInterface {
    override fun getItems(): List<NavBottomItem> {
        return listOf(
            NavBottomItem(
                title = "Início",
                url = "dashboard",
                selectedIcon = null,
                unselectedIcon = null,
                hasNews = false,
                painterResourceSelectedIcon = R.drawable.home_selected_icon,
                painterResourceUnselectedIcon = R.drawable.home_unselected_icon
            ),
            NavBottomItem(
                title = "Calendário",
                url = "calendar",
                selectedIcon = null,
                unselectedIcon = null,
                hasNews = false,
                painterResourceSelectedIcon = R.drawable.calendar_selected_icon,
                painterResourceUnselectedIcon = R.drawable.calendar_unselected_icon
            ),
            NavBottomItem(
                title = "Chat",
                url = "chatOnboarding",
                selectedIcon = null,
                unselectedIcon = null,
                hasNews = false,
                painterResourceSelectedIcon = R.drawable.chat_selected_icon,
                painterResourceUnselectedIcon = R.drawable.chat_unselected_icon
            ),
            NavBottomItem(
                title = "Novo",
                url = "new-email",
                selectedIcon = null,
                unselectedIcon = null,
                hasNews = false,
                painterResourceSelectedIcon = R.drawable.add_selected_icon,
                painterResourceUnselectedIcon = R.drawable.add_unselected_icon
            ),

            NavBottomItem(
                title = "Categoria",
                url = "category",
                selectedIcon = null,
                unselectedIcon = null,
                hasNews = false,
                painterResourceSelectedIcon = R.drawable.category_selected,
                painterResourceUnselectedIcon = R.drawable.category_unselected
            ),
        )
    }
}



