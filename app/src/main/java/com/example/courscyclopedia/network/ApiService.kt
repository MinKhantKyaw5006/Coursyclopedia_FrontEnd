package com.example.courscyclopedia.network

import com.example.courscyclopedia.model.CreateSubjectResponse
import com.example.courscyclopedia.model.FacultyResponse
import com.example.courscyclopedia.model.LikeRequest
import com.example.courscyclopedia.model.LikeResponse
import com.example.courscyclopedia.model.MajorResponse
import com.example.courscyclopedia.model.NewSubjectRequest
import com.example.courscyclopedia.model.SubjectDetailResponse
import com.example.courscyclopedia.model.SubjectResponse
import com.example.courscyclopedia.model.SubjectUpdateRequest
import com.example.courscyclopedia.model.UpdateSubjectResponse
import com.example.courscyclopedia.model.UserData
import com.example.courscyclopedia.model.UserDetails
import com.example.courscyclopedia.model.UserList
import com.example.courscyclopedia.model.UserResponse
import retrofit2.Response
import retrofit2.http.*

interface ApiService {
    @GET("api/faculties/getallfaculties")
    suspend fun getAllFaculties(): Response<FacultyResponse>

    @GET("api/majors/getallmajors")
    suspend fun getAllMajors(): Response<MajorResponse>

    @GET("api/faculties/getamjorforfaculty/{id}")
    suspend fun getMajorsForFaculty(@Path("id") facultyId: String): Response<MajorResponse>

    @GET("api/majors/getsubjectsforeachmajor/{id}")
    suspend fun getSubjectsForMajor(@Path("id") majorId: String): Response<SubjectResponse>

    @GET("api/subjects/geteachsubject/{id}")
    suspend fun getSubjectById(@Path("id") subjectId: String): Response<SubjectDetailResponse>
    // User Functions
    @GET("api/users/getallusers")
    suspend fun getAllUsers(): Response<UserList>
    @POST("api/users/createoneuser")
    suspend fun createUser(@Body user: UserData): Response<UserResponse>
    @GET("api/users/getoneuser/{id}")
    suspend fun getUserById(@Path("id") userId: String): Response<UserResponse>

    @PUT("api/users/updateoneuser/{id}")
    suspend fun updateUserById(@Path("id") userId: String, @Body user: UserData): Response<UserResponse>

    @PUT("api/users/updateoneuser/{id}")
    suspend fun updateUserById2(@Path("id") userId: String, @Body user: UserDetails): Response<UserResponse>



    @GET("api/users/getuserbyemail/{email}")
    suspend fun getUserbyEmail(@Path("email") email: String): Response<UserResponse>

    @GET("api/users/getuserbyemail/{email}")
    suspend fun getUserByEmail2(@Path("email") email: String): Response<UserDetails>


    @GET("api/subjects/getallsubjects")
    suspend fun getAllSubjects(): Response<SubjectResponse>

    @POST("api/subjects/createsubject")
    suspend fun createSubject(@Body newSubject: NewSubjectRequest): Response<CreateSubjectResponse>

    @DELETE("api/subjects/deletesubject/{id}")
    suspend fun deleteSubjectById(@Path("id") subjectId: String): Response<Unit>

    @PUT("api/subjects/updatelikes/{id}")
    suspend fun addLikeByEmail(@Path("id") subjectId: String, @Body likeRequest: LikeRequest): Response<LikeResponse>

    @PUT("api/subjects/updatesubject/{id}")
    suspend fun updateSubject(@Path("id") subjectId: String, @Body updateRequest: SubjectUpdateRequest): Response<UpdateSubjectResponse>

}


