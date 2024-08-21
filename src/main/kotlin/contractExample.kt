package org.gary

import kotlin.contracts.contract

sealed class User() {
    fun isAuthenticated(): Boolean {
        contract { returns(true) implies (this@User is AuthenticatedUser) }
    }
}