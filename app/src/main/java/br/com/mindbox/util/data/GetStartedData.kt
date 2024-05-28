package br.com.mindbox.util.data

import androidx.annotation.RawRes
import br.com.mindbox.R

class GetStartedData(@RawRes val resId: Int, val title: String, val desc: String)

val listData = listOf(
    GetStartedData(
        R.raw.apresentation1,
        "Define Your Goal",
        "Aliquam pharetra tortor nec odio pellentesque dignissim. Etiam ultricies sollicitudin est sit amet rutrum.",
    ),
    GetStartedData(
        R.raw.app_logo_horizontal,
        "Green Marketing",
        "Maecenas gravida, ipsum eget ultricies cursus, nisl lectus ullamcorper mi, egestas blandit mi sem vitae lorem.",
    ),
    GetStartedData(
        R.raw.app_logo_horizontal,
        "Secured Storage",
        "Vivamus convallis odio posuere, tempus nulla eget, ullamcorper erat. In ut tortor consequat, pharetra ex id, interdum mauris.",
    ),
)