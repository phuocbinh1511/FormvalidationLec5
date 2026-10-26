package com.example.formvalidation_lec5

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.formvalidation_lec5.databinding.ActivityTrangChuBinding

class TrangChuActivity : AppCompatActivity() {
    private lateinit var binding: ActivityTrangChuBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTrangChuBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val nguoiDung = intent.getSerializableExtra("THONG_TIN_USER") as? NguoiDung


        if (nguoiDung != null) {
            binding.tvThongTin.text = """
                Thông tin người dùng:
                - Họ và tên: ${nguoiDung.hoTen}
                - Email: ${nguoiDung.email}
                - SĐT: ${nguoiDung.sdt}
            """.trimIndent()
        }
    }
}