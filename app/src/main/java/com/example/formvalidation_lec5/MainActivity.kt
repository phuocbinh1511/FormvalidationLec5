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

        // Xử lý nút Hủy
        binding.btnHuy.setOnClickListener {
            binding.edtHoTen.setText("")
            binding.edtEmail.setText("")
            binding.edtSdt.setText("")
            binding.edtHoTen.requestFocus()
        }

        // Xử lý nút Đăng ký
        binding.btnDangKy.setOnClickListener {
            val hoTen = binding.edtHoTen.text.toString().trim()
            val email = binding.edtEmail.text.toString().trim()
            val sdt = binding.edtSdt.text.toString().trim()

            // Regex: Cho phép chữ cái (kể cả tiếng Việt) và khoảng trắng
            val nameRegex = Regex("^[a-zA-ZÀÁÂÃÈÉÊÌÍÒÓÔÕÙÚĂĐĨŨƠàáâãèéêìíòóôõùúăđĩũơƯĂẠẢẤẦẨẪẬẮẰẲẴẶẸẺẼỀỀỂưăạảấầẩẫậắằẳẵặẹẻẽềềểỄỆỈỊỌỎỐỒỔỖỘỚỜỞỠỢỤỦỨỪễệỉịọỏốồổỗộớờởỡợụủứừỬỮỰỲỴÝỶỸửữựỳỵỷỹ\\s]+$")

            // Regex: Bắt đầu bằng 0, theo sau là 9 chữ số (tổng cộng 10 số)
            val phoneRegex = Regex("^0\\d{9}$")

            // Kiểm tra từng điều kiện một
            if (hoTen.isEmpty() || email.isEmpty() || sdt.isEmpty()) {
                showError("Vui lòng không để trống bất kỳ trường nào!")
            } else if (hoTen.length < 3 || !hoTen.matches(nameRegex)) {
                showError("Họ tên không hợp lệ (phải từ 3 ký tự và không chứa số)!")
            } else if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                showError("Email không hợp lệ (cần có @ và tên miền)!")
            } else if (!sdt.matches(phoneRegex)) {
                showError("Số điện thoại không hợp lệ (phải bắt đầu bằng 0 và đủ 10 số)!")
            } else {
                // Nếu vượt qua tất cả điều kiện -> Thành công
                val nguoiDung = NguoiDung(hoTen, email, sdt)
                val intent = Intent(this, TrangChuActivity::class.java)
                intent.putExtra("THONG_TIN_USER", nguoiDung)
                startActivity(intent)
            }
        }
    }

    // Hàm phụ giúp hiển thị Snackbar ngắn gọn hơn
    private fun showError(message: String) {
        Snackbar.make(binding.root, message, Snackbar.LENGTH_LONG)
            .setBackgroundTint(resources.getColor(android.R.color.holo_red_dark, theme))
            .show()
    }
}