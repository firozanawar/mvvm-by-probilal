package com.firozanwar.mvvmbybilal.data.network.responses

import com.firozanwar.mvvmbybilal.data.db.entities.User

/**
 * Parse our json response to Kotlin classes/object.
 */
data class AuthResponse(
    val isSuccessful: Boolean?,
    val message: String?,
    val user: User?
)