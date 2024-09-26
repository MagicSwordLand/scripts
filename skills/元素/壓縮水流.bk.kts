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
    "壓縮流水", //同時也是這個技能的id
    "&6壓縮流水",
    3, //最大等級
    55.0, //魔力消耗
    20.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.TUBE_CORAL) // 材質
        .setModelData(0) // model
        .setDisplayName("&6壓縮流水 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f55 魔力")
                add("&7▪ 冷卻：&f20 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f元素精靈的夥伴 Lv.5、平波水紋 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f向前方釋出一圈壓縮的流水五秒")
                add("&f造成範圍內的敵人")
                add("&f每0.5秒 攻擊魔傷*1.2+智慧*1.2 傷害")
                add("&f同時獲得移動緩速III三秒")
                add("&f自身獲得移動加速III三秒")
                add("")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊魔傷*1.5+智慧*1.35")
                add("&7Lv2 ➤ &7技能持續時間增加至七秒")
                add("&7Lv3 ➤ &7技能傷害改為 &f攻擊魔傷*1.8+智慧*1.5")
                add("&7Lv3 ➤ &7技能持續時間增加至十秒")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("element" to 45),
        {it.getSkillData("元素精靈的夥伴").level >=5&&it.getSkillData("平波水紋").level >=1}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);