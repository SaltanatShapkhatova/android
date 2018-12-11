package com.example.selti06.finalproject.Model

import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "contacts", foreignKeys = arrayOf(ForeignKey(entity = Contact::class,
    parentColumns = arrayOf("id"),
    childColumns = arrayOf("gId"),
    onDelete = ForeignKey.CASCADE)))
data class Contact(
    @ColumnInfo(name = "name")
    var name: String,
    @ColumnInfo(name = "mobile")
    var mobile: String,
    @ColumnInfo(name = "home")
    var home: String,
    @ColumnInfo(name = "work")
    var work: String,
    @ColumnInfo(name = "gId")
    var gId: Int,
    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var image: ByteArray? = null
)
{
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
}