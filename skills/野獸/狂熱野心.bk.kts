@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.core.gui.paths.PathElement
import net.brian.heroesskills.core.gui.paths.conditions.SkillLevelCondition
import net.brian.heroesskills.core.skills.MythicActiveSkill
import net.brian.heroesskills.core.skills.PassiveMultipleStatSkill
import net.brian.heroesskills.core.skills.PassiveStatSkill
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material
import java.util.function.BiFunction

var activeSkill = MythicActiveSkill(
    "狂熱野心", //同時也是這個技能的id
    "&6狂熱野心",
    3, //最大等級
    20.0, //魔力消耗
    16.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.HEART_OF_THE_SEA) // 材質
        .setModelData(0) // model
        .setDisplayName("&6狂熱野心 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f20 魔力")
                add("&7▪ 冷卻：&f16 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f野獸同化 Lv.5")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f壓縮體內的狂暴氣息後")
                add("&f向周遭5格大量釋放")
                add("&f造成 攻擊傷害*1.35+體魄*1.5 傷害")
                add("&f且10秒內受到傷害增加20%")
                add("")
                add("&7Lv2 ➤ &7範圍增加至8格")
                add("&7Lv3 ➤ &7技能傷害改為 &f攻擊傷害*1.75+體魄*2")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("wildbeast" to 33),
        {it.getSkillData("野獸同化").level >=5&&it.getSkillData("狂暴怒吼").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);