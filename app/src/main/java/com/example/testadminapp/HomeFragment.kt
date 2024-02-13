package com.example.testadminapp

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testadminapp.databinding.FragmentHomeBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore


class HomeFragment : Fragment(R.layout.fragment_home) {

    private var _binding: FragmentHomeBinding?=null
    private val homeBinding  get() = _binding!!

    val db = Firebase.firestore

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater,container,false)
        return homeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var list = arrayListOf<User>()
        var rvAdapter = QuizAdapter(requireContext() ,list)
        homeBinding.recyclerView.layoutManager = LinearLayoutManager(activity)
        homeBinding.recyclerView.adapter = rvAdapter

        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                list.clear()
                for (document in result) {
                    val user = document.toObject(User::class.java)
                    user.id = document.id
                    list.add(user)
                }
                rvAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener {
                Toast.makeText(homeBinding.root.context,"Error loading data",
                    Toast.LENGTH_LONG).show()
            }

        homeBinding.addNoteFab.setOnClickListener {
            it.findNavController().navigate(R.id.action_homeFragment_to_addFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}