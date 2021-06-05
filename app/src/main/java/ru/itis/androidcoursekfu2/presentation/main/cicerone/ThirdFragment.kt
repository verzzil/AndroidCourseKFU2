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
import ru.terrakok.cicerone.Cicerone
import ru.terrakok.cicerone.Navigator
import ru.terrakok.cicerone.Router
import ru.terrakok.cicerone.Screen
import ru.terrakok.cicerone.android.support.SupportAppScreen
import ru.terrakok.cicerone.commands.BackTo
import ru.terrakok.cicerone.commands.Command
import ru.terrakok.cicerone.commands.Forward

class ThirdFragment : Fragment() {

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
                is Screens.FourthFragment -> {
                    findNavController().navigate(ThirdFragmentDirections.actionThirdFragmentToFourthFragment())
                }
            }
        }

        private fun onBack(screen: Screen) {
            when (screen as SupportAppScreen) {
                is Screens.SecondFragment -> {
                    findNavController().navigate(ThirdFragmentDirections.actionThirdFragmentToSecondFragment())
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
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_third, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        router = mrouter
        view.findViewById<Button>(R.id.third_to_fourth).setOnClickListener {
            router.navigateTo(Screens.FourthFragment)
        }
        view.findViewById<Button>(R.id.third_to_second).setOnClickListener {
            router.navigateTo(Screens.SecondFragment)
        }
    }
}