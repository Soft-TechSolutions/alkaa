package com.escodro.alkaa.data.local.model

import android.annotation.SuppressLint
import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.Index
import android.arch.persistence.room.PrimaryKey
import android.os.Parcelable
import kotlinx.android.parcel.IgnoredOnParcel
import kotlinx.android.parcel.Parcelize

/**
 * [Entity] to represent a task.
 */
@SuppressLint("ParcelCreator")
@Parcelize
@Entity(
    foreignKeys = [(ForeignKey(
        entity = Category::class,
        parentColumns = ["category_id"],
        childColumns = ["task_category_id"],
        onDelete = ForeignKey.CASCADE
    ))],
    indices = [(Index(value = ["task_category_id"]))]

)
data class Task(
    @ColumnInfo(name = "task_is_completed") var completed: Boolean = false,
    @ColumnInfo(name = "task_description") var description: String?,
    @ColumnInfo(name = "task_category_id") var categoryId: Long? = null
) : Parcelable {

    @IgnoredOnParcel
    @ColumnInfo(name = "task_id")
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0
}
