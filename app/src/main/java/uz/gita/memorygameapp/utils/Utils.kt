package uz.gita.memorygameapp.utils

import timber.log.Timber

fun <T> T.scope(block : T.() -> Unit){
    block(this)
}
fun myLog(tag : String = "TTT", message : String){
    Timber.tag(tag).d(message)
}

fun Int.getEnum(): LevelEnum {
    when (this) {
        0 -> {
            return LevelEnum.EASY
        }
        1 -> {
            return LevelEnum.MEDIUM
        }
        else -> {
            return LevelEnum.HARD
        }
    }
}