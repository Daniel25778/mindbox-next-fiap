package br.com.mindbox.util.data

import androidx.annotation.RawRes
import br.com.mindbox.R

val listData = listOf(
    GetStartedData(
        R.raw.apresentation1,
        "Define Your Goal",
        "Desperte para a revolução do e-mail: sua caixa de entrada nunca foi tão inteligente e moderna.",
    ),
    GetStartedData(
        R.raw.apresentation2,
        "Green Marketing",
        "Deixe a inteligência artificial organizar sua vida digital: simplificando sua caixa de entrada, um e-mail de cada vez.",
    ),
    GetStartedData(
        R.raw.apresentation3,
        "Secured Storage",
        "Converse com eficiência: nosso chat com IA está sempre pronto para  ajudar a redigir suas respostas de e-mail de forma inteligente e rápida.",
    ),
)

class GetStartedData(@RawRes val resId: Int, val title: String, val desc: String)
