package com.bignerdranch.android.notes.Fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import android.widget.ProgressBar
import androidx.navigation.NavController
import androidx.navigation.Navigation
import com.bignerdranch.android.notes.R
import com.bignerdranch.android.notes.databinding.SignUpBinding
import com.bignerdranch.android.notes.ListUser.User
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class sign_up : Fragment(){

    private lateinit var navController: NavController
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: SignUpBinding
    private lateinit var ProgressBar: ProgressBar

    private var doubleBackToExitPressedOnce = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = SignUpBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        view.isFocusableInTouchMode = true
        view.requestFocus()
        view.setOnKeyListener { _, keyCode, _ ->
            if (keyCode == KeyEvent.KEYCODE_BACK) {
                if (doubleBackToExitPressedOnce) {
                    activity?.finishAffinity()
                } else {
                    doubleBackToExitPressedOnce = true

                    Handler(Looper.getMainLooper()).postDelayed({
                        doubleBackToExitPressedOnce = false
                    }, 2000)
                }
                return@setOnKeyListener true
            }
            false
        }

        init(view)

        binding.entry.setOnClickListener {
            navController.navigate(R.id.action_sign_up_to_sign_in)

        }

        var isProcessing = false

        binding.nextButton.setOnClickListener {
            if (isProcessing) {
                return@setOnClickListener
            }

            isProcessing = true

            val email = binding.emailUp.text.toString()
            val password = binding.passwordUp.text.toString()
            val verifyPassword = binding.verifyPasswordUp.text.toString()
            val name = binding.NameUp.text.toString()
            ProgressBar = binding.progressBar!!

            if(email.isNotEmpty() && password.isNotEmpty() && verifyPassword.isNotEmpty() && name.isNotEmpty()){
                if(password == verifyPassword){
                    registrationUser(name, email, password)
                    ProgressBar.visibility = View.VISIBLE
                }
                else{
                    Toast.makeText(context,"Пароли не сходятся",Toast.LENGTH_SHORT).show()
                }
            }
            else{
                Toast.makeText(context, "Не оставляйте пустые поля", Toast.LENGTH_SHORT).show()
            }
            Handler().postDelayed({
                isProcessing = false
            }, 2000)
        }
    }

    private fun registrationUser(name:String, email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                val user = User(userName = name, userPassword = password, userEmail = email)

                FirebaseDatabase.getInstance().getReference("Users")
                    .child(FirebaseAuth.getInstance().currentUser?.uid!!)
                    .setValue(user)
                    .addOnCompleteListener(OnCompleteListener<Void> { task ->
                        if (task.isSuccessful) {
                            navController.navigate(R.id.action_sign_up_to_notes_book)
                        } else {
                            Toast.makeText(context, "Возникла ошибка", Toast.LENGTH_SHORT).show()
                        }
                    })
            }
            else{
                Toast.makeText(context, "Пользователь уже имеется с такими данными", Toast.LENGTH_SHORT).show()
            }
            ProgressBar.visibility = View.GONE
        }
    }

    private fun init(view: View){
        navController = Navigation.findNavController(view)
        mAuth = FirebaseAuth.getInstance()
    }
}