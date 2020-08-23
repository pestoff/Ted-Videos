package ru.pestoff.tedvideos.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Parcelize
@Root(name = "group", strict = false)
data class Group(
    @field:ElementList(inline = true, name = "content")
    var contents: MutableList<Content>
) : Parcelable {
    constructor() : this (mutableListOf<Content>())
}
