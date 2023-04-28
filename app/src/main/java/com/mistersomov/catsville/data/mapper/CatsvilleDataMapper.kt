package com.mistersomov.catsville.data.mapper

import com.mistersomov.catsville.data.network.model.CatDto
import com.mistersomov.catsville.domain.model.Cat

fun CatDto.toCat(): Cat {
    val url: String = this.url ?: ""

    return Cat(url = url)
}