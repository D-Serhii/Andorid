package stu.cn.ua.first_lab.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Student(
    val firstName: String,
    val lastName: String,
    val group: String
) : Parcelable {
    companion object {
        val GROUPS = listOf("KB-212", "KB-211")
        const val MAX_VARIANT = 30
    }

    fun isValid(): Boolean {
        return firstName.isNotEmpty() && lastName.isNotEmpty() && group.isNotEmpty()
    }
}
