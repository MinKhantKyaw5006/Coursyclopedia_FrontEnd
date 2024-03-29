package com.example.courscyclopedia.ui.users.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.courscyclopedia.repository.FacultyRepository


class FacultyViewModelFactory(private val facultyRepository: FacultyRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(FacultyViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return FacultyViewModel(facultyRepository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}