package com.example.courscyclopedia.ui.professors.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.courscyclopedia.databinding.FragmentProfessorBinding
import com.example.courscyclopedia.network.RetrofitClient
import com.example.courscyclopedia.repository.SubjectsRepository
import com.example.courscyclopedia.ui.professors.viewmodels.ProfessorViewModel
import com.example.courscyclopedia.ui.professors.viewmodels.ProfessorViewModelFactory
import com.example.courscyclopedia.ui.users.adapter.ProfessorSubjectsAdapter

class ProfessorFragment : Fragment() {
    private var _binding: FragmentProfessorBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: ProfessorViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentProfessorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.rvSubjects.scrollToPosition(0)

        val apiService = RetrofitClient.apiService
        val subjectsRepository = SubjectsRepository(apiService)
        val viewModelFactory = ProfessorViewModelFactory(subjectsRepository)
        viewModel = ViewModelProvider(this, viewModelFactory)[ProfessorViewModel::class.java]

        val adapter = ProfessorSubjectsAdapter(
            onSubjectClick = { subject ->
                val action = ProfessorFragmentDirections.actionProfessorFragmentToSubjectDetailFragment(subject.id)
                findNavController().navigate(action)
            },
            onDeleteClick = { subject ->
                // Implement your delete functionality here
                // For example, you might show a confirmation dialog, and if confirmed, call a ViewModel function to delete the subject
                viewModel.deleteSubject(subject.id)
            }
        )
        binding.rvSubjects.layoutManager = LinearLayoutManager(context)
        binding.rvSubjects.adapter = adapter

        viewModel.subjects.observe(viewLifecycleOwner) { subjects ->
            adapter.submitList(subjects)
        }

        binding.btnAddSubject.setOnClickListener {
            val action = ProfessorFragmentDirections.actionProfessorFragmentToAddSubjectFragment()
            findNavController().navigate(action)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

