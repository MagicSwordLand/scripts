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
    "炙熱火蝶", //同時也是這個技能的id
    "&6炙熱火蝶",
    3, //最大等級
    50.0, //魔力消耗
    25.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.FIRE_CORAL_FAN) // 材質
        .setModelData(0) // model
        .setDisplayName("&6炙熱火蝶 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f50 魔力")
                add("&7▪ 冷卻：&f25 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f元素精靈的夥伴 Lv.5、火元素濃縮球 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f向四周釋放隨機飛翔的火蝶")
                add("&f3秒後將引爆造成半徑8格敵人")
                add("&f 攻擊魔傷*2.25+智慧*1.5 傷害")
                add("")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊魔傷*2.5+智慧*2")
                add("&7Lv3 ➤ &7技能傷害改為 &f攻擊魔傷*3+智慧*2.5")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("element" to 53),
        {it.getSkillData("元素精靈的夥伴").level >=5&&it.getSkillData("火元素濃縮球").level >=1}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);