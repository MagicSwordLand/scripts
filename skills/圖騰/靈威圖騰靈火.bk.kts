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
    "靈威圖騰靈火", //同時也是這個技能的id
    "&6靈威圖騰•靈火",
    3, //最大等級
    35.0, //魔力消耗
    20.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.BLUE_ORCHID) // 材質
        .setModelData(0) // model
        .setDisplayName("&6靈威圖騰•靈火 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f35 魔力")
                add("&7▪ 冷卻：&f20 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f靈威守護神 Lv.5")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f放置一個靈威圖騰，持續10秒")
                add("&f持續造成10格內隨機3名敵人")
                add("&f攻擊魔傷1.2+智慧*0.5 傷害")
                add("")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊魔傷*1.5+智慧*0.8")
                add("&7Lv3 ➤ &7技能傷害改為 &f攻擊魔傷*1.85+智慧*1.2")
                add("&7Lv3 ➤ &7圖騰結束時，引爆圖騰造成半徑20格敵人")
                add("        &7攻擊魔傷*2.25+智慧*3")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("totem" to 44),
        {it.getSkillData("靈威守護神").level >=5}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);