package stu.cn.ua.first_lab.contract

fun interface ResponseListener<T> {
    fun onResults(results: T)
}