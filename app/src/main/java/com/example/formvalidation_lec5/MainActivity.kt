package com.example.formvalidation_lec5

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.formvalidation_lec5.databinding.ActivityMainBinding
import com.google.android.material.snackbar.Snackbar

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHuy.setOnClickListener {
            binding.edtHoTen.setText("")
            binding.edtEmail.setText("")
            binding.edtSdt.setText("")
            binding.edtHoTen.requestFocus()
        }

        binding.btnDangKy.setOnClickListener {
            val hoTen = binding.edtHoTen.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val sdt = binding.edtSdt.text.toString().trim()

            if (hoTen.isEmpty() || email.isEmpty() || sdt.isEmpty()) {
                Snackbar.make(binding.root, "Vui lòng nhập đầy đủ thông tin!", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(resources.getColor(android.R.color.holo_red_dark, theme))
                    .show()
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                Snackbar.make(binding.root, "Email không hợp lệ!", Snackbar.LENGTH_LONG)
                    .setBackgroundTint(resources.getColor(android.R.color.holo_red_dark, theme))
                    .show()
            } else {
                val nguoiDung = NguoiDung(hoTen, email, sdt)
                val intent = Intent(this, TrangChuActivity::class.java)
                intent.putExtra("THONG_TIN_USER", nguoiDung)
                startActivity(intent)
            }
        }
    }
}