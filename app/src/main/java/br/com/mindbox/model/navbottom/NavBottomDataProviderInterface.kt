package br.com.mindbox.model.navbottom

import androidx.compose.runtime.Composable

interface NavBottomDataProviderInterface {
    fun getItems(): List<NavBottomItem>
}