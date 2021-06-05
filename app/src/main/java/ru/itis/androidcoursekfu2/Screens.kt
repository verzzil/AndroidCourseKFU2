package ru.itis.androidcoursekfu2

import androidx.fragment.app.Fragment
import ru.itis.androidcoursekfu2.presentation.main.AnotherFragment
import ru.itis.androidcoursekfu2.presentation.main.BaseFragment
import ru.itis.androidcoursekfu2.presentation.main.HomeFragment
import ru.itis.androidcoursekfu2.presentation.main.cicerone.FirstFragment
import ru.itis.androidcoursekfu2.presentation.main.cicerone.FourthFragment
import ru.itis.androidcoursekfu2.presentation.main.cicerone.SecondFragment
import ru.itis.androidcoursekfu2.presentation.main.cicerone.ThirdFragment
import ru.terrakok.cicerone.android.support.SupportAppScreen

object Screens {

    object FirstFragment : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return FirstFragment()
        }
    }
    object SecondFragment : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return SecondFragment()
        }
    }

    object ThirdFragment : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return ThirdFragment()
        }
    }

    object FourthFragment : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return FourthFragment()
        }
    }

    object AnotherFragment : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return AnotherFragment()
        }
    }

    object BaseFragment : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return BaseFragment()
        }
    }

    object HomeFragment : SupportAppScreen() {
        override fun getFragment(): Fragment {
            return HomeFragment()
        }
    }
}