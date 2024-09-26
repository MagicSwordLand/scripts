@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")


import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.altskill2totem.AltSkill2Totem
import net.brian.altskill2totem.player.PlayerStats
import net.brian.altskill2totem.totem.Totem
import net.brian.altskill2totem.totem.TotemBuilder
import net.brian.altskill2totem.totem.hooks.HealingHook
import net.brian.altskill2totem.totem.hooks.RingEffectHook
import net.brian.altskill2totem.totem.hooks.SpiralEffectHook
import net.brian.altskill2totem.totem.hooks.StatBuffHook
import net.brian.altskill2totem.totem.owner.EntityTotemCaster
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.api.players.PlayerSkillProfile
import net.brian.heroesskills.api.players.SkillData
import net.brian.heroesskills.api.skills.ActiveSkill
import net.brian.heroesskills.core.gui.paths.PathElement
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material
import org.bukkit.Particle
import org.bukkit.entity.HumanEntity
import org.bukkit.entity.Player
import java.util.UUID


class HealingTotem : ActiveSkill("healing-totem","&a治癒圖騰"){

    init {
        maxLevel = 5;
    }

    val texture = "eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjYyY2ZhOWNkMDY4NTZjZDk0N2VhY2FiNzBjYjQ1OWUzYjE3YTIxY2E0NDc1NTVhYmNiNzczOWJlN2Y1M2UzMiJ9fX0="

    override fun getIcon(player: PlayerSkillProfile, data: SkillData): Icon {
        return Icon()
            .setDisplayName("&a治癒圖騰 Lv${data.level}")
            .setMaterial(Material.PLAYER_HEAD)
            .setSkullTexture(texture)
            .addLore(
                "主動技能",
                "&e召喚一個範圍 5",
                "效果x2",
                "持續60秒的治療圖騰"
            )
    }

    override fun onCast(skillProfile: PlayerSkillProfile,skillData: SkillData) {
        AltSkill2Totem.getInstance().playerManager.getPlayer(skillProfile.uuid).ifPresent {
            val r = 5.0 * (1+it.getStat(PlayerStats.RADIUS_BONUS));
            val effective = 10*(1+it.getStat(PlayerStats.EFFECT_BONUS));
            val duration = 20*60L*(1+it.getStat(PlayerStats.DURATION_BONUS));
            val builder = TotemBuilder()
                .setTexture(texture)
                .addTickHooks(SpiralEffectHook(Particle.TOTEM))
                .setDuration(duration.toLong())
                .addTickHooks(HealingHook(effective,r,20))
                .addTickHooks(RingEffectHook(Particle.TOTEM,r,60,2))
            it.spawnTotem("healing-totem",builder);
        }
    }

    override fun onDeactivate(playerProfile: PlayerSkillProfile,skillData: SkillData) {
        AltSkill2Totem.getInstance().playerManager.getPlayer(playerProfile.uuid).ifPresent {
            it.getTotem("healing-totem").ifPresent { totem->
                totem.removeEntity();
            }
        }
    }
}


val skill = HealingTotem();
HeroesSkills.getInstance().skillManager.register(skill);
HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        skill,
        mutableMapOf("totem" to 4)
    )
)