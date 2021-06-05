package ru.itis.androidcoursekfu2.presentation.main

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import ru.itis.androidcoursekfu2.R
import ru.itis.androidcoursekfu2.Screens
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class HomeFragment : Fragment() {
    private val cicerone = Cicerone.create()
    val mrouter get() = cicerone.router
    val navigatorHolder get() = cicerone.navigatorHolder
    lateinit var router: Router

    private val navigator = object : Navigator {
        override fun applyCommands(commands: Array<out Command>) {
            commands.forEach {
                when (it) {
                    is Forward -> {
                        onForward(it.screen)
                    }
                }
            }
        }

        private fun onForward(screen: Screen) {
            when (screen as SupportAppScreen) {
                is Screens.FirstFragment -> {
                    findNavController().navigate(HomeFragmentDirections.actionHomeFragmentToFirstFragment())
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router = mrouter
        view.findViewById<Button>(R.id.home_to_first).setOnClickListener {
            router.navigateTo(Screens.FirstFragment)
        }
    }
}