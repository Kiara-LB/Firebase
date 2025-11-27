package ar.edu.unlam.mobile.scaffolding.data.remoteconfig

import com.google.firebase.Firebase
import com.google.firebase.remoteconfig.remoteConfig
import com.google.firebase.remoteconfig.remoteConfigSettings

object RemoteConfigManager {

    private val remoteConfig = Firebase.remoteConfig

    fun init(onFetchComplete: () -> Unit) {
        val configSettings = remoteConfigSettings {
            minimumFetchIntervalInSeconds = 0
        }

        remoteConfig.setConfigSettingsAsync(configSettings)

        remoteConfig.setDefaultsAsync(
            mapOf("show_publish_button" to true)
        )

        remoteConfig.fetchAndActivate()
            .addOnCompleteListener { task ->
                if (task.isSuccessful) {
                    onFetchComplete()  //  se llama cuando ya hay valores ACTUALIZADOS
                } else {
                    onFetchComplete()  // igual se llama, pero usando defaults
                }
            }
    }


    fun isPublishedEnabled(): Boolean {
        return remoteConfig.getBoolean("show_publish_button")
    }

    fun getAppTitle(): String {
        return remoteConfig.getString("app_title")
    }

}
