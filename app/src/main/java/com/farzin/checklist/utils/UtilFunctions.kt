package com.farzin.checklist.utils

import com.farzin.checklist.navGraph.Screens
import com.farzin.checklist.viewModel.AuthenticationViewModel

object UtilFunctions {

    fun getStartDestination(authvm: AuthenticationViewModel): String =
        if (authvm.hasUser())
            Screens.HomeScreen.route
        else
            Screens.CreateUserScreen.route


}