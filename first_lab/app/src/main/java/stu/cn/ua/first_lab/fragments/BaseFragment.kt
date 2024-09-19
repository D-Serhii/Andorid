package stu.cn.ua.first_lab.fragments

import androidx.fragment.app.Fragment
import android.content.Context
import stu.cn.ua.first_lab.contract.AppContract
import stu.cn.ua.first_lab.contract.ResponseListener

open class BaseFragment : Fragment() {
    private var appContract: AppContract? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        appContract = context as? AppContract
    }

    override fun onDetach() {
        super.onDetach()
        appContract?.unregisterListeners(this)
        appContract = null
    }

    /**
     * Получение контракта приложения
     */
    open fun getAppContract(): AppContract {
        return appContract ?: throw IllegalStateException("AppContract not attached")
    }

    /**
     * Регистрация слушателя без необходимости указывать класс
     */
    fun <T> registerListener(listener: ResponseListener<T>) {
        getAppContract().registerListener(this, listener)
    }
}
