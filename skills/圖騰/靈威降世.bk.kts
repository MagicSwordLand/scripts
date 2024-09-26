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
    "靈威降世", //同時也是這個技能的id
    "&6靈威降世",
    3, //最大等級
    50.0, //魔力消耗
    25.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.HEART_OF_THE_SEA) // 材質
        .setModelData(0) // model
        .setDisplayName("&6靈威降世 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f50 魔力")
                add("&7▪ 冷卻：&f22 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f圖騰之主 Lv.5、靈威圖騰•靈火 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f10秒內自身增加魔法傷害加成10%")
                add("&f且每秒對周遭10格敵人造成")
                add("&f攻擊魔傷*1.25+智慧*0.8 傷害")
                add("&f同時敵人因為靈威傷害減少20%")
                add("")
                add("&7Lv2 ➤ &7自身增加魔法傷害改為20%")
                add("&7Lv2 ➤ &7傷害改為 &f攻擊魔傷*1.5+智慧*1.2")
                add("&7Lv3 ➤ &7自身增加魔法傷害改為30%")
                add("&7Lv3 ➤ &7傷害改為 &f攻擊魔傷*2+智慧*1.8")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("totem" to 53),
        {it.getSkillData("圖騰之主").level >=5&&it.getSkillData("靈威圖騰靈火").level >=1}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);