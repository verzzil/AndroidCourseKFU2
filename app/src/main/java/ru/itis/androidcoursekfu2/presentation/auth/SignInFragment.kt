package ru.itis.androidcoursekfu2.presentation.auth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import ru.itis.androidcoursekfu2.R

class SignInFragment : Fragment() {

    private lateinit var navController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        navController = (activity as AuthActivity).navController
        return inflater.inflate(R.layout.fragment_sign_in, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val email = view.findViewById<EditText>(R.id.sign_in_login)
        val password = view.findViewById<EditText>(R.id.sign_in_password)
        email.setText("test")
        password.setText("test")
        view.findViewById<Button>(R.id.sign_in_btn).setOnClickListener {
            navController.navigate(
                SignInFragmentDirections.actionSignInFragmentToMainActivity(
                    email.text.toString(),
                    password.text.toString()
                )
            )
        }
    }
}