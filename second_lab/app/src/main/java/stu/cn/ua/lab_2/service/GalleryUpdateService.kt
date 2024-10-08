package stu.cn.ua.lab_2.service

import android.app.job.JobParameters
import android.app.job.JobService
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.util.Log

class GalleryUpdateService : JobService() {
    override fun onStartJob(params: JobParameters?): Boolean {
        // Перевірка мережі
        if (isConnectedToUnmeteredNetwork()) {
            // Завантажуємо нові зображення
            Log.d("GalleryUpdateService", "Завантаження нових зображень")
            // Оновлення UI після завантаження
        }
        return true // Робота виконується у фоні
    }

    override fun onStopJob(params: JobParameters?): Boolean {
        return false
    }

    private fun isConnectedToUnmeteredNetwork(): Boolean {
        val connectivityManager = getSystemService(CONNECTIVITY_SERVICE) as ConnectivityManager
        val network = connectivityManager.activeNetwork ?: return false
        val networkCapabilities = connectivityManager.getNetworkCapabilities(network)
        return networkCapabilities?.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED) == true
    }
}
