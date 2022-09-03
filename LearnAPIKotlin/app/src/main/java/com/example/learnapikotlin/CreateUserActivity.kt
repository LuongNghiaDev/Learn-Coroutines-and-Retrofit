package com.example.learnapikotlin

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.activity_create_user.*

class CreateUserActivity : AppCompatActivity() {

    lateinit var viewModel: CreateUserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_user)

        val user_id = intent.getStringExtra("user_id")
        initViewModel()
        createUserObserverable()

        if(user_id != null) {
            loadUserId(user_id)
        }

        btnCreateUser.setOnClickListener {
            createUser(user_id)
        }

        btnDeleteUser.setOnClickListener {
            deleteUser(user_id)
        }

    }

    private fun deleteUser(user_id: String?) {
        viewModel.getDeleteObserverable().observe(this, Observer <UserResponse?>{
            if(it == null) {
                Toast.makeText(this@CreateUserActivity, "Failed to delete user...", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@CreateUserActivity, "Successfully deleted user...", Toast.LENGTH_LONG).show()
                finish()
            }
        })
        viewModel.deleteUser(user_id)
    }

    private fun loadUserId(user_id: String?) {
        viewModel.getLoadUserObserverable().observe(this, Observer <UserResponse?>{
            if(it != null) {
                edtName.setText(it.data?.name)
                edtEmail.setText(it.data?.email)
                btnCreateUser.setText("Update")
                btnDeleteUser.visibility = View.VISIBLE
            }
        })
        viewModel.getUserData(user_id)
    }

    private fun createUser(user_id: String?) {
        val user = User("", edtName.text.toString(), edtEmail.text.toString(), "Activity",
        "")

        if(user_id == null)
            viewModel.createUser(user)
        else
            viewModel.updateUser(user_id,user)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this).get(CreateUserViewModel::class.java)
    }

    private fun createUserObserverable() {
        viewModel.getCreateUserObserverable().observe(this, Observer<UserResponse> {
            if(it == null) {
                Toast.makeText(this@CreateUserActivity, "Faild create/update user", Toast.LENGTH_LONG).show()
            } else {
                Toast.makeText(this@CreateUserActivity, "Create/Update success", Toast.LENGTH_LONG).show()
                finish()
            }
        })
    }

}