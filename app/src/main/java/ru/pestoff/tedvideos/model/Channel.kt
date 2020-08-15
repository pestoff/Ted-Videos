package ru.pestoff.tedvideos.model

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "channel", strict = false)
data class Channel(
    @SerializedName("item")
    @field:ElementList(inline = true, name = "item")
    var items: MutableList<Item>
) {
    constructor() : this (mutableListOf<Item>())
}
