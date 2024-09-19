package stu.cn.ua.first_lab.fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import stu.cn.ua.first_lab.R
import stu.cn.ua.first_lab.model.Student
import stu.cn.ua.first_lab.contract.ResponseListener

class MenuFragment : BaseFragment() {
    private var student: Student? = null
    private lateinit var getVariantButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getVariantButton = view.findViewById(R.id.getVariantButton)

        getVariantButton.setOnClickListener {
            student?.let {
                if (it.isValid()) {
                    getAppContract().toResultsScreen(this, it)
                } else {
                    Toast.makeText(requireContext(), "Invalid student data", Toast.LENGTH_SHORT).show()
                }
            }
        }

        view.findViewById<Button>(R.id.optionsButton).setOnClickListener {
            getAppContract().toOptionsScreen(this, student)
        }

        view.findViewById<Button>(R.id.quitButton).setOnClickListener {
            getAppContract().cancel()
        }

        updateView()
    }

    // Обновляем вид и активность кнопки "Get Variant"
    private fun updateView() {
        getVariantButton.isEnabled = student?.isValid() == true
    }

    // Слушатель для получения данных студента
    private val studentListener = ResponseListener<Student> {
        student = it
        updateView()  // Обновляем кнопку после получения данных
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Регистрируем слушателя данных студента
        registerListener(studentListener)
    }
}
