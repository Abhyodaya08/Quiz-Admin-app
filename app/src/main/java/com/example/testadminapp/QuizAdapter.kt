package com.example.testadminapp

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.testadminapp.databinding.RvItemBinding
import com.google.firebase.Firebase
import com.google.firebase.firestore.firestore

class QuizAdapter(val context: Context, var list: ArrayList<User>):
    RecyclerView.Adapter<QuizAdapter.ViewHolder>() {

    class ViewHolder(var itemBinding: RvItemBinding) : RecyclerView.ViewHolder(itemBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RvItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = list[position]
        holder.itemBinding.itemQuestion.text = currentItem.question
        holder.itemBinding.itemOption1.text = currentItem.option_a
        holder.itemBinding.itemOption2.text = currentItem.option_b
        holder.itemBinding.itemOption3.text = currentItem.option_c
        holder.itemBinding.itemOption4.text = currentItem.option_d
        holder.itemBinding.itemCorrectAns.text = currentItem.correct_ans

        holder.itemBinding.btnItemDelete.setOnClickListener {
            val db = Firebase.firestore
            db.collection("users").document(currentItem.id)
                .delete()
                .addOnSuccessListener {
                    Toast.makeText(context,"Deleted ", Toast.LENGTH_LONG).show()
                    list.removeAt(position)
                    notifyDataSetChanged()
                }
        }
        holder.itemBinding.btnItemUpdate.setOnClickListener {
            val direction = HomeFragmentDirections.actionHomeFragmentToEditFragment(currentItem)
            it.findNavController().navigate(direction)
        }
    }
}