package stu.cn.ua.lab_2

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import stu.cn.ua.lab_2.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Використання View Binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Ініціалізація стартового фрагменту
        if (savedInstanceState == null) {
            supportFragmentManager.commit {
                replace(R.id.fragmentContainer, GalleryFragment())
            }
        }
    }

    fun showImageFragment(imageUrl: String) {
        // Перехід на фрагмент перегляду фото
        supportFragmentManager.commit {
            replace(R.id.fragmentContainer, ImageFragment.newInstance(imageUrl))
            addToBackStack(null)
        }
    }
}
