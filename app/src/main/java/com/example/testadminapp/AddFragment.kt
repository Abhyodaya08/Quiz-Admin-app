package com.example.testadminapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.testadminapp.databinding.FragmentAddBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class AddFragment : Fragment(R.layout.fragment_add) {

    private var _binding: FragmentAddBinding?= null
    private val binding get() = _binding!!

    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater,container,false)

        binding.btnQuesSubmit.setOnClickListener {
            val user = hashMapOf(
                "question" to binding.etQues.text.toString(),
                "option_a" to binding.etOption1.text.toString(),
                "option_b" to binding.etOption2.text.toString(),
                "option_c" to binding.etOption3.text.toString(),
                "correct_ans" to binding.etAnswer.text.toString(),
                "option_d" to binding.etOption4.text.toString()
            )

            db.collection("users")
                .add(user)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(binding.root.context,"Question Submitted with id: ${documentReference.id}",
                        Toast.LENGTH_LONG).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(binding.root.context,"Error adding document: ${e.localizedMessage}",
                        Toast.LENGTH_LONG).show()
                }
            it.findNavController().navigate(R.id.action_addFragment_to_homeFragment)
        }
        return binding.root
    }
}