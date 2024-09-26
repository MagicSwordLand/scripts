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
    "水原蛇", //同時也是這個技能的id
    "&6水原蛇",
    3, //最大等級
    30.0, //魔力消耗
    10.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.TWISTING_VINES) // 材質
        .setModelData(0) // model
        .setDisplayName("&6水原蛇 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f30 魔力")
                add("&7▪ 冷卻：&f10 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f元素精靈的信任 Lv.5、滴水衝擊 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f將水元素凝結成水蛇")
                add("&f向多數敵人造成高速衝擊")
                add("&f造成 攻擊魔傷*1.2+智慧 傷害")
                add("&f並且獲得移動緩速II三秒")
                add("")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊魔傷*1.25+智慧*1.25")
                add("&7Lv3 ➤ &7技能傷害改為 &f攻擊魔傷*2.5+智慧*1.75")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("element" to 29),
        {it.getSkillData("元素精靈的信任").level >=5&&it.getSkillData("滴水衝擊").level >=1}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);