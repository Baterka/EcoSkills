package com.willfp.ecoskills.effects

import com.willfp.eco.core.EcoPlugin
import com.willfp.eco.core.config.interfaces.Config
import com.willfp.ecoskills.EcoSkillsPlugin
import org.bukkit.NamespacedKey
import org.bukkit.event.Listener
import java.util.*

abstract class Effect(
    val id: String
): Listener {
    protected val plugin: EcoPlugin = EcoSkillsPlugin.getInstance()

    val key: NamespacedKey
    val uuid: UUID
    val config: Config
    lateinit var name: String
    lateinit var description: String

    init {
        update()
        key = plugin.namespacedKeyFactory.create(id)
        uuid = UUID.nameUUIDFromBytes(id.toByteArray())
        config = plugin.configYml.getSubsection("effects.$id")

        Effects.registerNewEffect(this)
    }

    fun update() {
        name = plugin.langYml.getString("effects.$id.name")
        description = plugin.langYml.getString("effects.$id.description")
    }
}