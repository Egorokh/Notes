package com.bignerdranch.android.notes.Fragments

import androidx.fragment.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.os.Handler
import android.os.Looper
import android.view.KeyEvent
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.bignerdranch.android.notes.R
import com.bignerdranch.android.notes.databinding.SignUpBinding
import androidx.core.app.ActivityCompat.finishAffinity
import com.google.firebase.auth.FirebaseAuth

class sign_up : Fragment(){

    private lateinit var navController: NavController
    private lateinit var mAuth: FirebaseAuth
    private lateinit var binding: SignUpBinding

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

            if(email.isNotEmpty() && password.isNotEmpty() && verifyPassword.isNotEmpty()){
                if(password == verifyPassword){
                    registrationUser(email, password)
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

    private fun registrationUser(email: String, password: String){
        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener {
            if(it.isSuccessful){
                navController.navigate(R.id.action_sign_up_to_notes_book)
            }
            else{
                Toast.makeText(context, "Пользователь уже имеется с такими данными", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun init(view: View){
        navController = Navigation.findNavController(view)
        mAuth = FirebaseAuth.getInstance()
    }
}