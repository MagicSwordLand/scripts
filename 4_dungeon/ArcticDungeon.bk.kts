@file:RequiredPlugins("Reactant", "ArcticDungeon")

import dev.reactant.reactant.extensions.locationOf
import io.github.clayclaw.clawrpg.dungeon.common.DungeonLocation
import io.github.clayclaw.clawrpg.dungeon.std.legacy.ActionBossService
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import io.github.clayclaw.reactant.inject
import org.bukkit.util.Vector

val actionBossService = inject<ActionBossService>()!!

actionBossService.create {
    it.dungeonId = "ArcticDungeon"
    it.dungeonDisplayName = "鳳凰"
    it.bossDisplayName = "&c鳳凰"
    it.bossMythicId = "UnknownZombie"

    it.bossSpawnLocation = DungeonLocation(9969.0, 235.0, 35.0)
    it.playerSpawnLocation = DungeonLocation(9931.0, 238.0, 34.0)

    it.cornerA = Vector(3.0, 180.0, 4.0) // lazy to modify here, but its the same anyway
    it.cornerB = Vector(115.0, 255.0, 118.0)

    it.playerRespawnLocation = locationOf("2k", -312.0, 64.0, 483.0)
}