package fr.lewon.dofus.bot.core.manager.d2o.managers

import fr.lewon.dofus.bot.core.manager.VldbManager
import fr.lewon.dofus.bot.core.manager.d2o.D2OUtil
import fr.lewon.dofus.bot.core.model.charac.DofusCharacteristic

object EffectManager : VldbManager {

    private lateinit var characteristicByEffectId: Map<Double, DofusCharacteristic?>

    override fun initManager() {
        characteristicByEffectId = D2OUtil.getObjects("Effects").associate {
            val id = it["id"].toString().toDouble()
            val characteristicId = it["characteristic"].toString().toDouble()
            id to CharacteristicManager.getCharacteristic(characteristicId)
        }
    }

    override fun getNeededManagers(): List<VldbManager> {
        return listOf(CharacteristicManager)
    }

    fun getCharacteristicByEffectId(effectId: Double): DofusCharacteristic? {
        return characteristicByEffectId[effectId]
    }

}