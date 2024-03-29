package com.example.courscyclopedia.repository

import com.example.courscyclopedia.model.CreateSubjectResponse
import com.example.courscyclopedia.model.LikeRequest
import com.example.courscyclopedia.model.LikeResponse
import com.example.courscyclopedia.model.Major
import com.example.courscyclopedia.model.NewSubjectRequest
import com.example.courscyclopedia.model.Subject
import com.example.courscyclopedia.model.SubjectResponse
import com.example.courscyclopedia.model.SubjectUpdateRequest
import com.example.courscyclopedia.network.ApiService
import com.example.courscyclopedia.ui.util.Result
import retrofit2.Response


class SubjectsRepository(private val apiService: ApiService) {

    // Function to fetch majors for a given faculty ID
    suspend fun getMajorsForFaculty(facultyId: String): List<Major> {
        val response = apiService.getMajorsForFaculty(facultyId)
        if (response.isSuccessful) {
            // The response is expected to be a MajorResponse object, which contains a List<Major>
            return response.body()?.data ?: emptyList()
        } else {
            throw Exception("Error fetching majors: ${response.errorBody()?.string()}")
        }
    }

    // Function to fetch subjects for a given major ID
    suspend fun getSubjectsForMajor(majorId: String): List<Subject> {
        val response = apiService.getSubjectsForMajor(majorId)
        if (response.isSuccessful) {
            return response.body()?.data ?: emptyList()
        } else {
            throw Exception("Error fetching subjects: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getSubjectById(subjectId: String): Subject? {
        val response = apiService.getSubjectById(subjectId)
        if (response.isSuccessful) {
            return response.body()?.data
        } else {
            throw Exception("Error fetching subject: ${response.errorBody()?.string()}")
        }
    }


    suspend fun getAllSubjects(): Response<SubjectResponse> {
        return apiService.getAllSubjects()
    }

    suspend fun createSubject(newSubjectRequest: NewSubjectRequest): CreateSubjectResponse {
        val response = apiService.createSubject(newSubjectRequest)
        if (response.isSuccessful) {
            // If the response is successful, return the response body which is a CreateSubjectResponse object
            return response.body() ?: throw Exception("Response body is null")
        } else {
            // If the response is not successful, throw an exception with the error message
            throw Exception("Error creating subject: ${response.errorBody()?.string()}")
        }
    }

    suspend fun getMajors(): List<Major> {
        val response = apiService.getAllMajors()
        if (response.isSuccessful) {
            return response.body()?.data ?: emptyList()
        } else {
            throw Exception("Error fetching majors: ${response.errorBody()?.string()}")
        }
    }

    suspend fun addLikeToSubject(subjectId: String, userEmail: String): Response<LikeResponse> {
        return apiService.addLikeByEmail(subjectId, LikeRequest(userEmail))
    }

    suspend fun deleteSubject(subjectId: String): Response<Unit> {
        return apiService.deleteSubjectById(subjectId)
    }


    suspend fun updateSubject(subjectId: String, updateRequest: SubjectUpdateRequest): Result<String> {
        return try {
            val response = apiService.updateSubject(subjectId, updateRequest)
            if (response.isSuccessful && response.body() != null) {
                // Assuming the response body only contains a message field.
                Result.Success(response.body()!!.message)
            } else {
                Result.Error(Exception("Failed to update subject: ${response.code()} ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }





}

