package com.example.testadminapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testadminapp.databinding.FragmentEditBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class EditFragment : Fragment(R.layout.fragment_edit) {

    private var editBinding: FragmentEditBinding?=null
    private val binding get() = editBinding!!

    val db = Firebase.firestore

    private lateinit var currentItem: User
    private val args: EditFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        editBinding = FragmentEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        currentItem = args.currentItem

        binding.updateQues.setText(currentItem.question)
        binding.updateOption1.setText(currentItem.option_a)
        binding.updateOption2.setText(currentItem.option_b)
        binding.updateOption3.setText(currentItem.option_c)
        binding.updateOption4.setText(currentItem.option_d)
        binding.updateAnswer.setText(currentItem.correct_ans)

        binding.updatebtnQuesSubmit.setOnClickListener {

            val user = hashMapOf(
                "question" to binding.updateQues.text.toString(),
                "option_a" to binding.updateOption1.text.toString(),
                "option_b" to binding.updateOption2.text.toString(),
                "option_c" to binding.updateOption3.text.toString(),
                "correct_ans" to binding.updateAnswer.text.toString(),
                "option_d" to binding.updateOption4.text.toString()
            )

            db.collection("users")
                .document(currentItem.id)
                .set(user)

            it.findNavController().navigate(R.id.action_editFragment_to_homeFragment)
        }
    }
}