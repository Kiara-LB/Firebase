package ar.edu.unlam.mobile.scaffolding.analytics

import android.content.Context
import android.os.Bundle
import com.google.firebase.analytics.FirebaseAnalytics

/**
 * Singleton para gestionar
 * el registro de eventos
 * de Firebase Analytics.
*/
object AnalyticsManager {


    private lateinit var firebaseAnalytics: FirebaseAnalytics
    fun init (context: Context){
        firebaseAnalytics = FirebaseAnalytics.getInstance(context)

    }
    /**
     * Evento que se registra cuando un usuario
     * inicia sesión exitosamente.
     * Usamos el evento estándar "LOGIN" de Firebase.
     * @param userId El ID único del usuario que ha iniciado sesión.
     */
fun logLoginEvent(userId: String){
//Establece el ID de usuario para los eventos futuros
//en esta sesión queden asosciados al mismo usuario.

firebaseAnalytics.setUserId(userId)
    //Crea un bundle para añadir parámetros extra

    val bundle = Bundle().also{
        it.putString(FirebaseAnalytics.Param.METHOD,"Email/Password")
        it.putString("user_id", userId)
    }
    //Registra el evento login.
        firebaseAnalytics.logEvent(FirebaseAnalytics.Event.LOGIN,bundle)

}
fun logPostCreated(){
    val bundle= Bundle().also{

        it.putString("post_type","missing")

    }
    firebaseAnalytics.logEvent("post_created",bundle)


}

}
