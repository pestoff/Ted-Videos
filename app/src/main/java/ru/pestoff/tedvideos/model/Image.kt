package ru.pestoff.tedvideos.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Parcelize
@Root(name = "image", strict = false)
data class Image(
    @field:Attribute(name = "url")
    var url: String
) : Parcelable {
    constructor() : this ("")
}
