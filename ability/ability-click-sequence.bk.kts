@file:RequiredPlugins("AltSkill2", "AltSkill2-ModuleTrigger")

import io.github.clayclaw.altskill2.module.trigger.cache.InternalTriggerSequenceCache
import io.github.clayclaw.altskill2.module.trigger.model.ClickType
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins

InternalTriggerSequenceCache(arrayListOf(
    ClickType.RIGHT, ClickType.LEFT, ClickType.RIGHT
))

InternalTriggerSequenceCache(arrayListOf(
    ClickType.RIGHT, ClickType.RIGHT, ClickType.LEFT
))

InternalTriggerSequenceCache(arrayListOf(
    ClickType.RIGHT, ClickType.RIGHT, ClickType.RIGHT
))

InternalTriggerSequenceCache(arrayListOf(
    ClickType.RIGHT, ClickType.LEFT, ClickType.LEFT
))

fun onDispose() {
    InternalTriggerSequenceCache.clear()
}