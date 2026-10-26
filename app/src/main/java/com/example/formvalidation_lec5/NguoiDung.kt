package com.example.tenduan // Nhớ giữ nguyên tên package của bạn

import java.io.Serializable

data class NguoiDung(
    val hoTen: String,
    val email: String,
    val sdt: String
) : Serializable