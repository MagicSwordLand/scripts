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
    "狂暴怒吼", //同時也是這個技能的id
    "&6狂暴怒吼",
    3, //最大等級
    10.0, //魔力消耗
    12.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.SEAGRASS) // 材質
        .setModelData(0) // model
        .setDisplayName("&6狂暴怒吼 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f10 魔力")
                add("&7▪ 冷卻：&f12 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f野獸之軀 Lv.5")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f釋放帶有狂暴氣息的怒吼")
                add("&f周遭10格敵人會因為怒吼對你發狂")
                add("&f敵人因為發狂，受到傷害的傷害提高10%")
                add("&f自身會因為狂暴氣息降低移動速度")
                add("&f但短暫獲得減傷20%")
                add("&f所有效果將持續5秒")
                add("")
                add("&7Lv2 ➤ &7效果持續時間提高至8秒")
                add("&7Lv3 ➤ &7氣息擴散範圍提高至15格")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("wildbeast" to 23),
        {it.getSkillData("野獸之軀").level >=5}
    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);