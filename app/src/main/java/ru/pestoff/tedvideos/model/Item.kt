package ru.pestoff.tedvideos.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Parcelize
@Root(name = "item", strict = false)
data class Item(
    @field:Element(name = "title")
    var title: String,

    @field:Element(name = "link")
    var link: String,

    @field:Element(name = "description")
    var description: String,

    @field:Element(name = "image")
    var image: Image,

    @field:Element(name = "duration")
    var duration: String,

    @field:Element(name = "group")
    var group: Group
) : Parcelable {
    constructor() : this("", "", "", Image(), "", Group())
}
