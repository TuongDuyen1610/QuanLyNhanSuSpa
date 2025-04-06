package vn.edu.tlu.cse.tuongthiduyen.quanlynhansuspa.ui.manager;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import vn.edu.tlu.cse.tuongthiduyen.quanlynhansuspa.R;

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_manager_login);
        // Ánh xạ các phần tử từ layout
        etEmail = findViewById(R.id.et_email);
        etPassword = findViewById(R.id.et_password);
        btnLogin = findViewById(R.id.btn_login);
        cbRememberPassword = findViewById(R.id.cb_remember_password);
        tvErrorMessage = findViewById(R.id.tv_error_message);
        tvForgotPassword = findViewById(R.id.tv_forgot_password);
        tvContactSupport = findViewById(R.id.tv_contact_support);
        progressBar = findViewById(R.id.progress_bar);
        ivShowPassword = findViewById(R.id.iv_show_password);

        // Xử lý sự kiện khi nhấn nút Đăng nhập
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra thông tin nhập vào
                String email = etEmail.getText().toString().trim();
                String password = etPassword.getText().toString().trim();

                // Kiểm tra các trường hợp nhập thiếu hoặc sai
                if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
                    tvErrorMessage.setVisibility(View.VISIBLE);
                    tvErrorMessage.setText("Email và mật khẩu không được để trống.");
                    return;
                }

                // Kiểm tra định dạng email hợp lệ
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                    tvErrorMessage.setVisibility(View.VISIBLE);
                    tvErrorMessage.setText("Email không hợp lệ.");
                    return;
                }

                // Kiểm tra mật khẩu (ví dụ: ít nhất 6 ký tự)
                if (password.length() < 6) {
                    tvErrorMessage.setVisibility(View.VISIBLE);
                    tvErrorMessage.setText("Mật khẩu phải có ít nhất 6 ký tự.");
                    return;
                }

                // Xử lý đăng nhập
                progressBar.setVisibility(View.VISIBLE);
                btnLogin.setEnabled(false);

                // Giả lập đăng nhập thành công sau 2 giây
                new android.os.Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        // Ẩn ProgressBar và cho phép nhấn nút đăng nhập
                        progressBar.setVisibility(View.GONE);
                        btnLogin.setEnabled(true);

                        // Nếu đăng nhập thành công
                        Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();
                    }
                }, 2000);
            }
        });

        // Xử lý sự kiện "Quên mật khẩu"
        tvForgotPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Chuyển sang màn hình khôi phục mật khẩu
                Toast.makeText(LoginActivity.this, "Chuyển đến màn hình khôi phục mật khẩu", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý sự kiện "Liên hệ hỗ trợ"
        tvContactSupport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Mở ứng dụng gọi điện hoặc email để liên hệ hỗ trợ
                Toast.makeText(LoginActivity.this, "Mở liên hệ hỗ trợ", Toast.LENGTH_SHORT).show();
            }
        });

        // Xử lý sự kiện ẩn/hiện mật khẩu
        ivShowPassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPasswordVisible) {
                    // Ẩn mật khẩu
                    etPassword.setInputType(129); // type: textPassword
                    ivShowPassword.setImageResource(R.drawable.ic_show_password);
                } else {
                    // Hiện mật khẩu
                    etPassword.setInputType(144); // type: textVisiblePassword
                    ivShowPassword.setImageResource(R.drawable.ic_hide_password);
                }
                etPassword.setSelection(etPassword.getText().length());
                isPasswordVisible = !isPasswordVisible;
            }
        });
    }
}
