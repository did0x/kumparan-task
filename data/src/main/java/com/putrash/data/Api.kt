package com.putrash.data

import com.putrash.data.response.*
import retrofit2.http.GET
import retrofit2.http.Path

interface Api {
    @GET("posts")
    suspend fun getAllPosts(): ArrayList<PostData>

    @GET("posts/{postId}/comments")
    suspend fun getAllComments(@Path("postId") postId: Int): ArrayList<CommentData>

    @GET("users")
    suspend fun getAllUsers(): ArrayList<UserData>

    @GET("users/{userId}")
    suspend fun getUser(@Path("userId") userId: Int): UserData

    @GET("users/{userId}/posts")
    suspend fun getPostsByUser(@Path("userId") userId: Int ): ArrayList<PostData>

    @GET("users/{userId}/comments")
    suspend fun getCommentsByUser(@Path("userId") userId: Int ): ArrayList<CommentData>

    @GET("users/{userId}/albums")
    suspend fun getAlbumsByUser(@Path("userId") userId: Int): ArrayList<AlbumData>

    @GET("albums/{albumId}/photos")
    suspend fun getAllPhotos(@Path("albumId") albumId: Int): ArrayList<PhotoData>
}