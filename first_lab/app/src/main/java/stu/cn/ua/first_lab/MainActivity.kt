package stu.cn.ua.first_lab

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import stu.cn.ua.first_lab.contract.AppContract
import stu.cn.ua.first_lab.contract.ResponseListener
import stu.cn.ua.first_lab.fragments.MenuFragment
import stu.cn.ua.first_lab.fragments.OptionsFragment
import stu.cn.ua.first_lab.fragments.ResultsFragment
import stu.cn.ua.first_lab.model.Student

class MainActivity : AppCompatActivity(), AppContract {
    private var studentListener: ResponseListener<Student>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainer, MenuFragment())
                .commit()
        }
    }

    override fun toOptionsScreen(target: Fragment, student: Student?) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, OptionsFragment.newInstance(student))
            .addToBackStack(null)
            .commit()
    }

    override fun toResultsScreen(target: Fragment, student: Student) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, ResultsFragment.newInstance(student))
            .addToBackStack(null)
            .commit()
    }

    override fun cancel() {
        supportFragmentManager.popBackStack()
    }

    override fun <T> sendData(data: T) {
        studentListener?.onResults(data as Student)
    }

    override fun <T> registerListener(fragment: Fragment, listener: ResponseListener<T>) {
        if (fragment is MenuFragment) {
            studentListener = listener as? ResponseListener<Student>
        }
    }

    override fun unregisterListeners(fragment: Fragment) {
        studentListener = null
    }

    override fun publish(student: Student) {
        // Реализуйте логику публикации данных здесь
        // Например, передача данных между фрагментами или сохранение
        // В данном случае можно использовать sendData
        sendData(student)
    }
}
