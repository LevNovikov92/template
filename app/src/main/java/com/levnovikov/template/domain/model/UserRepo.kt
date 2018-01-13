package com.levnovikov.template.domain.model

/**
 * Author: lev.novikov
 * Date: 12/1/18.
 */
data class UserRepo(val id: String, val name: String, val desc: String?, val userName: String) {

    val url = "/$userName/$name"
}