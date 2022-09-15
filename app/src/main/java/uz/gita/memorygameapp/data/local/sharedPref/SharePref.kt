package uz.gita.memorygameapp.data.local.sharedPref

import android.content.Context
import android.content.SharedPreferences
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPref @Inject constructor() {
    companion object {
        private lateinit var pref: SharedPreferences
        fun init(context: Context) {
            pref = context.getSharedPreferences("MemoryGame", Context.MODE_PRIVATE)
        }
    }

    var attempt: Int
        get() = pref.getInt("attempt", 1)
        set(value) = pref.edit().putInt("attempt", value).apply()

    var easyLevel: Int
        get() = pref.getInt("easy", 1)
        set(value) = pref.edit().putInt("easy", value).apply()

    var mediumLevel: Int
        get() = pref.getInt("medium", 1)
        set(value) = pref.edit().putInt("medium", value).apply()

    var hardLevel: Int
        get() = pref.getInt("hard",1)
        set(value) = pref.edit().putInt("hard", value).apply()


}