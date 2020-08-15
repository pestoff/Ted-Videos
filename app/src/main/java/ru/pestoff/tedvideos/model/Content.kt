package ru.pestoff.tedvideos.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import org.simpleframework.xml.Attribute
import org.simpleframework.xml.Root

@Parcelize
@Root(name = "content", strict = false)
data class Content(

    @field:Attribute(name = "url")
    var url: String,

    @field:Attribute(name = "bitrate")
    var bitrate: String
) : Parcelable {
    constructor() : this("", "")
}
