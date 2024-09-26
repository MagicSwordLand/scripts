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
    "滴水衝擊", //同時也是這個技能的id
    "&6滴水衝擊",
    3, //最大等級
    20.0, //魔力消耗
    10.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.TUBE_CORAL_FAN) // 材質
        .setModelData(0) // model
        .setDisplayName("&6滴水衝擊 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f20 魔力")
                add("&7▪ 冷卻：&f10 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f元素精靈的祝福 Lv.5")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f凝聚大量水滴後向前釋放")
                add("&f造成 攻擊魔傷+智慧*1.2 傷害")
                add("&f被擊中的敵人獲得緩速II三秒")
                add("")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊魔傷*1.25+智慧*1.5")
                add("&7Lv2 ➤ &7水滴衝擊額外多凝聚一次")
                add("&7Lv3 ➤ &7技能傷害改為 &f攻擊魔傷*1.5+智慧*1.75")
                add("&7Lv3 ➤ &7水滴衝擊額外多凝聚一次")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("element" to 21),
        {it.getSkillData("元素精靈的祝福").level >=5}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);