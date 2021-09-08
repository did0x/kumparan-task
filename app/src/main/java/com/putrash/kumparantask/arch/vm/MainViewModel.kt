package com.putrash.kumparantask.arch.vm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.putrash.common.Constants
import com.putrash.common.format
import com.putrash.data.Api
import com.putrash.data.model.Album
import com.putrash.data.model.Post
import com.putrash.data.model.User
import com.putrash.data.model.User.Address
import com.putrash.data.response.CommentData
import com.putrash.data.response.PhotoData
import com.putrash.kumparantask.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainViewModel(private val api: Api) : BaseViewModel() {

    private val _posts = MutableLiveData(mutableListOf<Post>())
    val posts: LiveData<MutableList<Post>> get() = _posts

    private val _comments = MutableLiveData(mutableListOf<CommentData>())
    val comments: LiveData<MutableList<CommentData>> get() = _comments

    private val _albums = MutableLiveData(mutableListOf<Album>())
    val albums: LiveData<MutableList<Album>> get() = _albums

    private val _photos = MutableLiveData(mutableListOf<PhotoData>())
    val photos: LiveData<MutableList<PhotoData>> get() = _photos

    private val _user = MutableLiveData<User>()
    val user: LiveData<User> get() = _user


    var selectedPost = Post()
    var selectedAlbum = Album()
    var selectedUserId: Int = 0
    var selectedPhoto = PhotoData()

    fun getPosts() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoading()
                val data = mutableListOf<Post>()
                val posts = async { api.getAllPosts() }
                val users = async { api.getAllUsers() }
                posts.await().map { post ->
                    users.await().map { user ->
                        if (post.userId == user.id) {
                            data.add(Post(
                                post.userId, post.id, post.title.format(), post.body.format(),
                                Constants.AVATAR_URL + post.userId, user.name, user.username,
                                user.phone, user.website, user.company.company
                            ))
                        }
                    }
                }
                _posts.postValue(data)
            } catch (t: Throwable) {
                showError(t.message)
            } finally {
                hideLoading()
            }
        }
    }

    fun getPostsByUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoading()
                val data = mutableListOf<Post>()
                val user = api.getUser(selectedPost.userId)
                val posts = api.getPostsByUser(selectedPost.userId)
                posts.map { post ->
                    data.add(Post(
                        post.userId, post.id, post.title.format(), post.body.format(),
                        Constants.AVATAR_URL + post.userId, user.name, user.username,
                        user.phone, user.website, user.company.company
                    ))
                }
                _posts.postValue(data)
            } catch (t: Throwable) {
                showError(t.message)
            } finally {
                hideLoading()
            }
        }
    }

    fun getAlbumsByUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoading()
                val data = mutableListOf<Album>()
                val albums = api.getAlbumsByUser(selectedPost.userId)
                albums.map { album ->
                    val photos = api.getAllPhotos(album.id)
                    data.add(Album(
                        album.userId, album.id, album.title, photos.size, photos[0].thumbnailUrl
                    ))
                }
                _albums.postValue(data)
            } catch (t: Throwable) {
                showError(t.message)
            } finally {
                hideLoading()
            }
        }
    }

    fun getPhotosByAlbum() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoading()
                val data = api.getAllPhotos(selectedAlbum.id)
                _photos.postValue(data)
            } catch (t: Throwable) {
                showError(t.message)
            } finally {
                hideLoading()
            }
        }
    }

    fun getUser() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoading()
                val data = api.getUser(selectedPost.userId)
                _user.postValue(User(
                    data.id, data.name, data.username, data.email, data.phone, data.website,
                    Constants.AVATAR_URL + data.id, data.company.company,
                    Address(data.address.street, data.address.suite, data.address.city)
                ))
            } catch (t: Throwable) {
                showError(t.message)
            } finally {
                hideLoading()
            }
        }
    }

    fun getComments(postId: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                showLoading()
                val data = api.getAllComments(postId)
                _comments.postValue(data)
            } catch (t: Throwable) {
                showError(t.message)
            } finally {
                hideLoading()
            }
        }
    }
}