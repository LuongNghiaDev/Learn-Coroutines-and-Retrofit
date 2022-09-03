package com.example.learnapikotlin

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CreateUserViewModel: ViewModel() {

    lateinit var createUserLiveData: MutableLiveData<UserResponse>
    lateinit var loadUserId: MutableLiveData<UserResponse>
    lateinit var deleteUserLiveData: MutableLiveData<UserResponse>

    init {
        createUserLiveData = MutableLiveData()
        loadUserId = MutableLiveData()
        deleteUserLiveData = MutableLiveData()
    }

    fun getCreateUserObserverable(): MutableLiveData<UserResponse> {
        return createUserLiveData
    }

    fun getLoadUserObserverable(): MutableLiveData<UserResponse> {
        return loadUserId
    }

    fun getDeleteObserverable(): MutableLiveData<UserResponse> {
        return deleteUserLiveData
    }

    fun createUser(user: User) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.createUser(user)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if(response.isSuccessful) {
                    createUserLiveData.postValue(response.body())
                } else {
                    createUserLiveData.postValue(null)
                }
            }
        })
    }

    fun getUserData(user_id: String?) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.getUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                loadUserId.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if(response.isSuccessful) {
                    loadUserId.postValue(response.body())
                } else {
                    loadUserId.postValue(null)
                }
            }
        })
    }

    fun updateUser(user_id: String, user: User) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.updateUser(user_id, user)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                createUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if(response.isSuccessful) {
                    createUserLiveData.postValue(response.body())
                } else {
                    createUserLiveData.postValue(null)
                }
            }
        })
    }

    fun deleteUser(user_id: String?) {
        val retroInstance = RetroInstance.getRetroInstance().create(RetroService::class.java)
        val call = retroInstance.deleteUser(user_id!!)
        call.enqueue(object : Callback<UserResponse?> {
            override fun onFailure(call: Call<UserResponse?>, t: Throwable) {
                deleteUserLiveData.postValue(null)
            }

            override fun onResponse(call: Call<UserResponse?>, response: Response<UserResponse?>) {
                if(response.isSuccessful) {
                    deleteUserLiveData.postValue(response.body())
                } else {
                    deleteUserLiveData.postValue(null)
                }
            }
        })
    }

}