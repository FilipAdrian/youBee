package com.android.app.youbee.entity

import android.os.Parcel
import android.os.Parcelable

data class Recipe(
    val image: String,
    val label: String,
    val yield: Float,
    val url: String,
    val ingredientLines: List<String>
) : Parcelable {
    constructor(parcel: Parcel) : this(
        parcel.readString().toString(),
        parcel.readString().toString(),
        parcel.readFloat(),
        parcel.readString().toString(),
        parcel.readArrayList(String::class.java.classLoader) as List<String>
    ) {
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(image)
        parcel.writeString(label)
        parcel.writeFloat(`yield`)
        parcel.writeString(url)
        parcel.writeList(ingredientLines)
    }

    fun ingredientsToString(): String {
        var str = ""
        for (ing: String in ingredientLines) str += "$ing\n"
        return str
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Recipe> {
        override fun createFromParcel(parcel: Parcel): Recipe {
            return Recipe(parcel)
        }

        override fun newArray(size: Int): Array<Recipe?> {
            return arrayOfNulls(size)
        }
    }
}
