@file:RequiredPlugins("AltSkill2", "Reactant")

import dev.reactant.reactant.extensions.translateChatColor
import io.github.clayclaw.altskill2.AltSkill2
import io.github.clayclaw.altskill2.cache.InternalAbilityCache
import io.github.clayclaw.altskill2.dsl.AbilityPathBuilder
import io.github.clayclaw.altskill2.dsl.abilityPath
import io.github.clayclaw.altskill2.dsl.descByPattern
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import org.bukkit.Bukkit
import org.bukkit.Material
import java.util.HashSet

// 注冊天賦路徑
fun registerPath() {
    path("strength") {
        displayName = "戰鬥"
        displayMaterial = Material.IRON_SWORD
        displayDescription = descByPattern()
    }
    path("magic") {
        displayName = "法術"
        displayMaterial = Material.ENCHANTED_BOOK
        displayDescription = descByPattern()
    }
    path("relic") {
        displayName = "遺物"
        displayMaterial = Material.ENDER_EYE
        displayDescription = descByPattern()
    }
    path("totem") {
        displayName = "圖騰"
        displayMaterial = Material.NETHER_STAR
        displayDescription = descByPattern()
    }

    // Print loaded paths
    println("Loaded ability paths: ${registeredPaths.joinToString(", ")}")
}

// 下面的東西不要動
private val registeredPaths: HashSet<String> = HashSet()

fun path(id: String, building: AbilityPathBuilder.() -> Unit) {
    abilityPath(id, building)
    registeredPaths.add(id)
}

Bukkit.getScheduler().runTaskLater(AltSkill2.instance, Runnable {
    registerPath()
}, 1)

fun onDispose() {
    registeredPaths.forEach {
        InternalAbilityCache.unregisterAbilityPath(it)
    }
}
