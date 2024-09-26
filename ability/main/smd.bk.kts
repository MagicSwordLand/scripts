@file:RequiredPlugins("AltSkill2", "AltSkill2-ModuleTrigger", "Reactant")

import io.github.clayclaw.altskill2.cache.InternalAbilityCache
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import io.github.clayclaw.altskill2.dsl.*
import io.github.clayclaw.altskill2.module.trigger.dsl.mythicActiveHook
import io.github.clayclaw.lightcargo.kts.definition.annotation.Import
import org.bukkit.Material

main("smd") {
    it.availableOnPaths = setOf("strength")

    it.displayName = "SMD TEST"
    it.displayMaterial = Material.WOODEN_SWORD
    it.displayDescription = it.descByPattern(
        mapOf(
            "description" to listOf(
                "This is a place for you to put the props",
                "you can type anything you want here!",
                "this is skill description, will be used to display."
            )
        )
    )

    // actual skills goes here
    it.skillHooks = listOf(it.mythicActiveHook("SMD", 1.0, 20L))
}

fun onDispose() {
    InternalAbilityCache.unregisterAbility("entry-sword-art")
}
