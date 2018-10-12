package com.example.saltanat.actorkotlin

import android.os.Parcel
import android.os.Parcelable

class  Actor : Parcelable {
    var name: String? = null
    var film: String? = null

    constructor(name: String, film: String) {
        this.name = name
        this.film = film
    }

    constructor(source: Parcel) {
        this.name = source.readString()
        this.film = source.readString()
    }

    override fun describeContents(): Int {
        return 0
    }

    override fun writeToParcel(parcel: Parcel, i: Int) {
        parcel.writeString(name)
        parcel.writeString(film)
    }

    companion object {

        val CREATOR: Parcelable.Creator<Actor> = object : Parcelable.Creator<Actor> {
            override fun createFromParcel(source: Parcel): Actor {
                return Actor(source)
            }

            override fun newArray(size: Int): Array<Actor?> {
                return arrayOfNulls(size)
            }
        }
    }
}