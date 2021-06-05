package ru.itis.androidcoursekfu2.presentation.main.cicerone

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.fragment.findNavController
import ru.itis.androidcoursekfu2.R
import ru.itis.androidcoursekfu2.Screens
import ru.itis.androidcoursekfu2.presentation.main.HomeFragmentDirections
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class FirstFragment : Fragment() {

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
                    is BackTo -> {
                        onBack(it.screen)
                    }
                }
            }
        }

        private fun onForward(screen: Screen) {
            when (screen as SupportAppScreen) {
                is Screens.SecondFragment -> {
                    findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment())
                }
            }
        }

        private fun onBack(screen: Screen) {
            when (screen as SupportAppScreen) {
                is Screens.HomeFragment -> {
                    findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToHomeFragment())
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
        return inflater.inflate(R.layout.fragment_first, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router = mrouter
        view.findViewById<Button>(R.id.first_to_second).setOnClickListener {
            router.navigateTo(Screens.SecondFragment)
        }
        view.findViewById<Button>(R.id.first_to_home).setOnClickListener {
            router.navigateTo(Screens.HomeFragment)
        }
    }
}