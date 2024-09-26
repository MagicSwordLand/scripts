@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.altskill2totem.AltSkill2Totem
import net.brian.altskill2totem.player.PlayerStats
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.api.players.PlayerSkillProfile
import net.brian.heroesskills.api.players.SkillData
import net.brian.heroesskills.api.skills.AbstractSkill
import net.brian.heroesskills.core.gui.paths.PathElement
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material


abstract class PassiveTotemStatSkill(skillID: String, displayName: String,val statID: String): AbstractSkill(skillID, displayName) {
    override fun onActivate(playerProfile: PlayerSkillProfile, skillData: SkillData) {
        AltSkill2Totem.getInstance().playerManager.getPlayer(playerProfile.uuid).ifPresent {
            it.setStat(statID,getAmount(playerProfile,skillData))
        }
    }
    override fun onDeactivate(playerProfile: PlayerSkillProfile, skillData: SkillData) {
        AltSkill2Totem.getInstance().playerManager.getPlayer(playerProfile.uuid).ifPresent {
            it.setStat(statID,0.0)
        }
    }

    abstract fun getAmount(layerProfile: PlayerSkillProfile, skillData: SkillData): Double;
}

class DurationBonusSkill:PassiveTotemStatSkill(
    "totem-duration-bonus",
    "圖騰時間延長",
    PlayerStats.DURATION_BONUS
){
    init {
        maxLevel = 5;
    }
    override fun getAmount(layerProfile: PlayerSkillProfile, skillData: SkillData): Double {
        return 1+skillData.level*0.1;
    }
    override fun getIcon(profile: PlayerSkillProfile, skillData: SkillData): Icon {
        return Icon()
            .setMaterial(Material.BOOK)
            .setDisplayName("圖騰時間延長(${skillData.level}/$maxLevel)")
            .addLore(
                buildList {
                    add("&a延長圖騰持續的時間")
                    var level = skillData.level
                    if(skillData.level == 0){
                        level = 1;
                    }
                    add("&7圖騰的時間延長x${1+0.1*level}")
                }
            )
    }
}

class EffectiveBonusSkill:PassiveTotemStatSkill(
    "totem-effect-bonus",
    "圖騰效果增益",
    PlayerStats.EFFECT_BONUS
){
    init {
        maxLevel = 5;
    }
    override fun getAmount(layerProfile: PlayerSkillProfile, skillData: SkillData): Double {
        return 1+skillData.level*0.1;
    }
    override fun getIcon(profile: PlayerSkillProfile, skillData: SkillData): Icon {
        return Icon()
            .setMaterial(Material.LECTERN)
            .setDisplayName("圖騰效果增益(${skillData.level}/$maxLevel)")
            .addLore(
                buildList {
                    add("&a增加圖騰的效益")
                    var level = skillData.level
                    if(skillData.level == 0){
                        level = 1;
                    }
                    add("&7圖騰的效益x${1+0.1*level}")
                }
            )
    }
}

class RadiusBonusSkill:PassiveTotemStatSkill(
    "totem-effect-bonus",
    "圖騰範圍擴增",
    PlayerStats.RADIUS_BONUS
){
    init {
        maxLevel = 5;
    }
    override fun getAmount(layerProfile: PlayerSkillProfile, skillData: SkillData): Double {
        return 1+skillData.level*0.1;
    }
    override fun getIcon(profile: PlayerSkillProfile, skillData: SkillData): Icon {
        return Icon()
            .setMaterial(Material.LECTERN)
            .setDisplayName("圖騰範圍增加(${skillData.level}/$maxLevel)")
            .addLore(
                buildList {
                    add("&a增加圖騰的效果範圍")
                    var level = skillData.level
                    if(skillData.level == 0){
                        level = 1;
                    }
                    add("&7圖騰的範圍x${1+0.1*level}")
                }
            )
    }
}
val durationSkill = DurationBonusSkill();
HeroesSkills.getInstance().skillManager.register(durationSkill);
HeroesSkills.getInstance().mainPathGui.registerElement(PathElement(
    durationSkill,
    mapOf("totem" to 5)
))

val effectSkill = EffectiveBonusSkill();
HeroesSkills.getInstance().skillManager.register(effectSkill);
HeroesSkills.getInstance().mainPathGui.registerElement(PathElement(
    effectSkill,
    mapOf("totem" to 6)
))

val radiusSkill = RadiusBonusSkill();
HeroesSkills.getInstance().skillManager.register(radiusSkill);
HeroesSkills.getInstance().mainPathGui.registerElement(PathElement(
    radiusSkill,
    mapOf("totem" to 7)
))
