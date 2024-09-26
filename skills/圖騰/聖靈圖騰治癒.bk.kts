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
    "聖靈圖騰治癒", //同時也是這個技能的id
    "&6聖靈圖騰•治癒",
    3, //最大等級
    20.0, //魔力消耗
    0.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.GOLDEN_CARROT) // 材質
        .setModelData(0) // model
        .setDisplayName("&6聖靈圖騰•治癒 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f15 魔力")
                add("&7▪ 冷卻：&f15 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f圖騰信仰 Lv.5")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f信仰神聖的聖靈們")
                add("&f聖靈將恢復自身及周遭5格隊友")
                add("&f每秒 智慧*0.5 血量 持續5秒")
                add("")
                add("&7Lv2 ➤ &7恢復血量改為 &f智慧*0.7")
                add("&7Lv3 ➤ &7恢復血量改為 &f智慧*1")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("totem" to 21),
        {it.getSkillData("圖騰信仰").level >=5}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);