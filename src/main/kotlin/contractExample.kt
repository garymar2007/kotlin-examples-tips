package org.gary

import kotlin.contracts.ExperimentalContracts
import kotlin.contracts.contract

sealed class User() {
    @OptIn(ExperimentalContracts::class)
    fun requireNotNull(id: Any?) {
        contract {
            returns() implies (id != null)
        }
        if (id == null) {
            throw IllegalArgumentException("ID cannot be null")
        }
    }
}