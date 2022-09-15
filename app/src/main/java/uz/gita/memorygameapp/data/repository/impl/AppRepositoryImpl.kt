package uz.gita.memorygameapp.data.repository.impl

import uz.gita.memorygameapp.R
import uz.gita.memorygameapp.data.models.CardData
import uz.gita.memorygameapp.utils.LevelEnum
import uz.gita.memorygameapp.data.repository.AppRepository
import javax.inject.Inject

class AppRepositoryImpl @Inject constructor() : AppRepository {
    val listOfCardData = arrayListOf(
        CardData(R.drawable.image_1,1),
        CardData(R.drawable.image_2,2),
        CardData(R.drawable.image_3,3),
        CardData(R.drawable.image_4,4),
        CardData(R.drawable.image_5,5),
        CardData(R.drawable.image_6,6),
        CardData(R.drawable.image_7,7),
        CardData(R.drawable.image_8,8),
        CardData(R.drawable.image_9,9),
        CardData(R.drawable.image_10,10),
        CardData(R.drawable.image_11,11),
        CardData(R.drawable.image_12,12),
        CardData(R.drawable.image_13,13),
        CardData(R.drawable.image_14,14),
        CardData(R.drawable.image_15,15),
        CardData(R.drawable.image_16,16),
        CardData(R.drawable.image_17,17),
        CardData(R.drawable.image_18,18),
        CardData(R.drawable.image_19,19),
        CardData(R.drawable.image_20,20),
        CardData(R.drawable.image_21,21),
        CardData(R.drawable.image_22,22),
        CardData(R.drawable.image_23,23),
        CardData(R.drawable.image_24,24),
        CardData(R.drawable.image_25,25)
    )
    override fun getCardDatas(levelEnum: LevelEnum) : List<CardData> {
        val result = ArrayList<CardData>()
        val count = levelEnum.x * levelEnum.y / 2
        listOfCardData.shuffle()
        for (i in 0 until count){
            result.add(listOfCardData[i])
        }
        return result
    }

    override fun getAllCards(): List<CardData> {
        return listOfCardData
    }
}