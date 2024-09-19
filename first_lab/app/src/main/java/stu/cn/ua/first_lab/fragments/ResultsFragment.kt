package stu.cn.ua.first_lab.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import stu.cn.ua.first_lab.R
import stu.cn.ua.first_lab.model.Student
import java.util.Random

class ResultsFragment : BaseFragment() {
    private lateinit var firstNameTextView: TextView
    private lateinit var lastNameTextView: TextView
    private lateinit var groupTextView: TextView
    private lateinit var variantTextView: TextView

    companion object {
        private const val ARG_STUDENT = "student"

        fun newInstance(student: Student): ResultsFragment {
            val fragment = ResultsFragment()
            val args = Bundle().apply {
                putParcelable(ARG_STUDENT, student)
            }
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstNameTextView = view.findViewById(R.id.firstNameTextView)
        lastNameTextView = view.findViewById(R.id.lastNameTextView)
        groupTextView = view.findViewById(R.id.groupTextView)
        variantTextView = view.findViewById(R.id.variantTextView)

        val student = arguments?.getParcelable<Student>(ARG_STUDENT)
        student?.let {
            firstNameTextView.text = it.firstName
            lastNameTextView.text = it.lastName
            groupTextView.text = it.group
            variantTextView.text = (Random().nextInt(Student.MAX_VARIANT) + 1).toString()
        }

        view.findViewById<Button>(R.id.doneButton).setOnClickListener {
            getAppContract().cancel()
        }
    }
}
