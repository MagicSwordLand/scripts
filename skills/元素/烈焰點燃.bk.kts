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
    "烈焰點燃", //同時也是這個技能的id
    "&6烈焰點燃",
    3, //最大等級
    30.0, //魔力消耗
    12.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.RED_DYE) // 材質
        .setModelData(0) // model
        .setDisplayName("&6烈焰點燃 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f30 魔力")
                add("&7▪ 冷卻：&f12 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f元素精靈的信任 Lv.5、炙熱火球 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f點燃一名敵人5秒")
                add("&f造成每秒 攻擊魔傷*0.8+智慧*1.25 傷害")
                add("&f5秒後引爆敵人體內的火元素")
                add("&f造成目標半徑3格內所有敵人")
                add("&f引燃傷害*2")
                add("")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊魔傷*1.5+智慧*1.25")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊魔傷*1.85+智慧*1.5")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("element" to 33),
        {it.getSkillData("元素精靈的信任").level >=5&&it.getSkillData("炙熱火球").level >=1}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);