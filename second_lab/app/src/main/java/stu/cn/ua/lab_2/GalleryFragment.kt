package stu.cn.ua.lab_2

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import stu.cn.ua.lab_2.databinding.FragmentGalleryBinding
import kotlinx.coroutines.*

class GalleryFragment : Fragment() {
    private var _binding: FragmentGalleryBinding? = null
    private val binding get() = _binding!!
    private var progressJob: Job? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGalleryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Імітація завантаження зображень
        loadImagesWithProgress()
    }

    private fun loadImagesWithProgress() {
        val images = listOf(
            "https://drive.google.com/uc?export=view&id=1U8tuxTdZmlPU2TWCpGj4-GY6GL8T0kI-",
            "https://drive.google.com/uc?export=view&id=1aPRQ2nU7wGj8kmiGLnrYcEECbmhsqP8I",
            "https://drive.google.com/uc?export=view&id=1tgNPIhH7GGc0YFRFU-nNbyG_wx3SWwkX",
            "https://drive.google.com/uc?export=view&id=1hSHTqeDW-0K8Ot20RpKZBClGm7SIR7xb",
            "https://drive.google.com/uc?export=view&id=1KkD0PT3qgExegu56je8KxI5i2psCGB-v",
            "https://drive.google.com/uc?export=view&id=1xdvN4l1k3I-6Fp2V76JqYe4Nl9NohGLr"
        )

        // Спочатку показуємо прогрес
        binding.loadingLayout.visibility = View.VISIBLE
        binding.galleryRecyclerView.visibility = View.GONE

        progressJob = CoroutineScope(Dispatchers.Main).launch {
            var progress = 0
            val total = images.size

            for (i in images.indices) {
                delay(500) // Імітація завантаження одного зображення
                progress = ((i + 1) * 100) / total
                updateProgress(progress)
            }

            // Після завершення завантаження
            binding.loadingLayout.visibility = View.GONE
            binding.galleryRecyclerView.visibility = View.VISIBLE

            binding.galleryRecyclerView.layoutManager = GridLayoutManager(requireContext(), 2)
            binding.galleryRecyclerView.adapter = GalleryAdapter(images) { imageUrl ->
                (activity as MainActivity).showImageFragment(imageUrl)
            }
        }
    }

    private fun updateProgress(progress: Int) {
        binding.progressBar.progress = progress
        binding.progressText.text = "Завантаження: $progress%"
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        progressJob?.cancel() // Завершуємо роботу корутини, якщо фрагмент знищено
    }
}
