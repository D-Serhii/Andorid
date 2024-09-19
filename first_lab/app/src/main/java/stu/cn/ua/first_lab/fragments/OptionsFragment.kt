package stu.cn.ua.first_lab.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import stu.cn.ua.first_lab.R
import stu.cn.ua.first_lab.contract.AppContract
import stu.cn.ua.first_lab.model.Student

class OptionsFragment : BaseFragment() {
    private lateinit var firstNameEditText: EditText
    private lateinit var lastNameEditText: EditText
    private lateinit var groupSpinner: Spinner

    companion object {
        private const val ARG_STUDENT = "student"

        fun newInstance(student: Student?): OptionsFragment {
            val fragment = OptionsFragment()
            val args = Bundle()
            args.putParcelable(ARG_STUDENT, student)
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_options, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        firstNameEditText = view.findViewById(R.id.firstNameEditText)
        lastNameEditText = view.findViewById(R.id.lastNameEditText)
        groupSpinner = view.findViewById(R.id.groupSpinner)

        val adapter = ArrayAdapter(
            requireContext(),
            android.R.layout.simple_spinner_item,
            Student.GROUPS
        )
        groupSpinner.adapter = adapter

        val student = arguments?.getParcelable<Student>(ARG_STUDENT)
        student?.let {
            firstNameEditText.setText(it.firstName)
            lastNameEditText.setText(it.lastName)
            val index = Student.GROUPS.indexOf(it.group)
            if (index != -1) groupSpinner.setSelection(index)
        }

        view.findViewById<Button>(R.id.doneButton).setOnClickListener {
            val newStudent = Student(
                firstNameEditText.text.toString(),
                lastNameEditText.text.toString(),
                groupSpinner.selectedItem.toString()
            )
            if (newStudent.isValid()) {
                getAppContract().sendData(newStudent) // Поменяйте на sendData, если хотите передать данные
                getAppContract().cancel()
            } else {
                Toast.makeText(requireContext(), R.string.empty_fields_error, Toast.LENGTH_SHORT).show()
            }
        }

        view.findViewById<Button>(R.id.cancelButton).setOnClickListener {
            getAppContract().cancel()
        }
    }
}
