//package com.example.courscyclopedia.ui.users.adapter
//
//import android.util.Log
//import android.view.LayoutInflater
//import android.view.View
//import android.view.ViewGroup
//import android.widget.TextView
//import androidx.recyclerview.widget.RecyclerView
//import com.example.courscyclopedia.R
//import com.example.courscyclopedia.model.Subject
//
//class ProfessorSubjectsAdapter(private val onSubjectClick: (Subject) -> Unit) : RecyclerView.Adapter<ProfessorSubjectsAdapter.SubjectViewHolder>() {
//
//    private var subjectsList: List<Subject> = listOf()
//
//    class SubjectViewHolder(itemView: View, val onSubjectClick: (Subject) -> Unit) : RecyclerView.ViewHolder(itemView) {
//        private val textViewSubjectName: TextView = itemView.findViewById(R.id.textViewSubjectName)
//        private val textviewSubjectCode: TextView = itemView.findViewById(R.id.textviewSubjectCode)
//
//        fun bind(subject: Subject) {
//            Log.d("SubjectsAdapter", "Binding subject: ${subject.subjectname}")
//            textViewSubjectName.text = subject.subjectname
//            textviewSubjectCode.text = subject.subjectCode
//            itemView.setOnClickListener {
//                onSubjectClick(subject) // Invoke the click listener, passing the subject
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subject, parent, false)
//        return SubjectViewHolder(view, onSubjectClick)
//    }
//
//    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
//        holder.bind(subjectsList[position])
//    }
//
//    override fun getItemCount() = subjectsList.size
//
//    fun submitList(subjects: List<Subject>) {
//        this.subjectsList = subjects
//        notifyDataSetChanged()
//    }
//}


package com.example.courscyclopedia.ui.users.adapter

import android.view.View
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.courscyclopedia.R
import com.example.courscyclopedia.model.Subject

class ProfessorSubjectsAdapter(private val onSubjectClick: (Subject) -> Unit, private val onDeleteClick: (Subject) -> Unit) : RecyclerView.Adapter<ProfessorSubjectsAdapter.SubjectViewHolder>() {

    private var subjectsList: List<Subject> = listOf()

    class SubjectViewHolder(itemView: View, val onSubjectClick: (Subject) -> Unit, val onDeleteClick: (Subject) -> Unit) : RecyclerView.ViewHolder(itemView) {
        private val textViewSubjectName: TextView = itemView.findViewById(R.id.textViewSubjectName)
        private val textviewSubjectCode: TextView = itemView.findViewById(R.id.textviewSubjectCode)
        private val deleteIcon: ImageView = itemView.findViewById(R.id.deleteIcon)

        fun bind(subject: Subject) {
            textViewSubjectName.text = subject.subjectname
            textviewSubjectCode.text = subject.subjectCode
            itemView.setOnClickListener {
                onSubjectClick(subject)
            }
            deleteIcon.setOnClickListener {
                onDeleteClick(subject)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SubjectViewHolder {
        // Make sure to use item_subject_professor.xml here
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_subject_professor, parent, false)
        return SubjectViewHolder(view, onSubjectClick, onDeleteClick)
    }

    override fun onBindViewHolder(holder: SubjectViewHolder, position: Int) {
        holder.bind(subjectsList[position])
    }

    override fun getItemCount() = subjectsList.size

    fun submitList(subjects: List<Subject>) {
        this.subjectsList = subjects
        notifyDataSetChanged()
    }
}
