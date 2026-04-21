package com.edu.sync.data.api

import com.edu.sync.data.models.*
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Response
import retrofit2.http.*

interface EduSyncApiService {
    
    @GET("api/files")
    suspend fun getFiles(
        @Header("Authorization") token: String,
        @Query("type") type: String // "pdf" or "note"
    ): Response<List<EduSyncFile>>

    @Multipart
    @POST("api/upload")
    suspend fun uploadFile(
        @Header("Authorization") token: String,
        @Part file: MultipartBody.Part,
        @Part("type") type: RequestBody
    ): Response<EduSyncFile>

    @GET("api/videos")
    suspend fun getVideos(
        @Header("Authorization") token: String
    ): Response<List<YouTubeLink>>

    @POST("api/generate-timetable")
    suspend fun generateTimetable(
        @Header("Authorization") token: String,
        @Body request: RequestBody
    ): Response<RequestBody> // Response type depends on actual response structure

    @GET("api/marketplace")
    suspend fun getListings(): Response<List<MarketplaceListing>>

    @POST("api/marketplace")
    suspend fun createListing(
        @Header("Authorization") token: String,
        @Body listing: MarketplaceListing
    ): Response<MarketplaceListing>
}

interface RagApiService {
    @POST("query")
    suspend fun query(
        @Body request: RagQueryRequest
    ): Response<RagQueryResponse>

    @Multipart
    @POST("load-pdf")
    suspend fun loadPdf(
        @Part file: MultipartBody.Part
    ): Response<Unit>
}
