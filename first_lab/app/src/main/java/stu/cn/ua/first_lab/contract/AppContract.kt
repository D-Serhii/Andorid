package stu.cn.ua.first_lab.contract

import androidx.fragment.app.Fragment
import stu.cn.ua.first_lab.model.Student

interface AppContract {
    /**
     * Переход на экран настроек (OptionsFragment)
     * @param target фрагмент, который запускает экран настроек
     * @param student данные о студенте для отображения
     */
    fun toOptionsScreen(target: Fragment, student: Student?)

    /**
     * Переход на экран результатов (ResultsFragment)
     * @param target фрагмент, который запускает экран результатов
     * @param student данные для расчета варианта
     */
    fun toResultsScreen(target: Fragment, student: Student)

    /**
     * Завершение текущего экрана
     */
    fun cancel()

    /**
     * Отправка данных между экранами
     * @param data данные, которые нужно передать
     */
    fun <T> sendData(data: T)

    /**
     * Регистрация слушателя для получения данных
     * @param fragment фрагмент, ожидающий данные
     * @param listener обработчик для получения данных
     */
    fun <T> registerListener(fragment: Fragment, listener: ResponseListener<T>)

    /**
     * Отключение всех слушателей
     */
    fun unregisterListeners(fragment: Fragment)

    /**
     * Публикация данных о студенте
     * @param student данные о студенте для публикации
     */
    fun publish(student: Student)
}
