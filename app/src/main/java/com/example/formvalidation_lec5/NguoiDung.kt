package com.example.formvalidation_lec5

import java.io.Serializable

data class NguoiDung(
    val hoTen: String,
    val email: String,
    val sdt: String
) : Serializable