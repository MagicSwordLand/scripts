@file:RequiredPlugins("AltSkill2", "AltSkill2-ModuleTrigger", "helper", "Reactant", "DynamicScoreboard")

import io.github.clayclaw.altskill2.definition.MountedAbility
import io.github.clayclaw.altskill2.module.trigger.cache.InternalSkillCache
import io.github.clayclaw.altskill2.module.trigger.service.timer.TimerService
import io.github.clayclaw.altskill2.module.trigger.skill.AbstractSkill
import io.github.clayclaw.dynamicscoreboard.service.ScoreboardService
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import io.github.clayclaw.reactant.inject
import me.lucko.helper.Events
import me.lucko.helper.Schedulers
import org.bukkit.entity.Player
import org.bukkit.event.entity.ProjectileHitEvent
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType
import java.util.*

val scoreboardService by lazy { inject<ScoreboardService>()!! }
val timerService by lazy { inject<TimerService>()!! }

class SkillEyeOfTwilight: AbstractSkill(timerService, scoreboardService) {

    override val displayName: String = "暮光之眼"
    override val cooldown: Long = 1L
    override val manaConsume: Double = 0.0

    private val playerSet: HashSet<Player> = HashSet()

    private val disposableSchedulerTask = Schedulers.sync().runRepeating(
        Runnable {
            playerSet.forEach { addBuff(it) }
        },
        1L, 200L)

    override fun onActivate(player: Player, ability: MountedAbility) {
        super.onActivate(player, ability)
        playerSet.add(player)
        addBuff(player)
    }

    override fun onDeactivate(player: Player, ability: MountedAbility) {
        super.onDeactivate(player, ability)
        playerSet.remove(player)
        player.removePotionEffect(PotionEffectType.NIGHT_VISION)
    }

    override fun getScoreboardDisplayLine(player: Player): List<String> {
        return listOf("Eye Skill Activated")
    }

    fun addBuff(player: Player) {
        player.addPotionEffect(PotionEffect(PotionEffectType.NIGHT_VISION, 1200, 1))
    }

    fun onDispose() {
        disposableSchedulerTask.close()
    }

}

class SkillFireEye: AbstractSkill(timerService, scoreboardService) {

    override val displayName: String = "火焰之眼"
    override val cooldown: Long = 1L
    override val manaConsume: Double = 0.0

    private val playerSet: HashSet<Player> = HashSet()

    private val disposableEventTask = Events.subscribe(ProjectileHitEvent::class.java)
        .filter { it.hitEntity != null && it.entity.shooter != null && it.entity.shooter is Player && playerSet.contains(it.entity.shooter)}
        .handler {
            it.hitEntity?.fireTicks = 60
        }

    override fun onActivate(player: Player, ability: MountedAbility) {
        super.onActivate(player, ability)
        playerSet.add(player)
    }

    override fun onDeactivate(player: Player, ability: MountedAbility) {
        super.onDeactivate(player, ability)
        playerSet.remove(player)
    }

    override fun getScoreboardDisplayLine(player: Player): List<String> {
        return listOf("Fire Skill Activated")
    }

    fun onDispose() {
        disposableEventTask.close()
    }

}

val skillMain = SkillEyeOfTwilight()
val skillFire = SkillFireEye()
InternalSkillCache["eye-of-twilight"] = skillMain
InternalSkillCache["eye-of-twilight-fire"] = skillFire

fun onDispose() {
    skillMain.onDispose()
    skillFire.onDispose()
}