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
    "聖靈之兆", //同時也是這個技能的id
    "&6聖靈之兆",
    3, //最大等級
    55.0, //魔力消耗
    25.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.TOTEM_OF_UNDYING) // 材質
        .setModelData(0) // model
        .setDisplayName("&6聖靈之兆 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f60 魔力")
                add("&7▪ 冷卻：&f22 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f圖騰之主 Lv.5、聖靈圖騰•聖滅 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f聖靈守護神賜於強大的力量")
                add("&f開啟後自身獲得1秒無敵")
                add("&f10秒內每秒恢復周遭十格玩家")
                add("&f攻擊魔傷*0.8+智慧*0.6 血量")
                add("&f同時對周遭十格敵人發射聖靈彈")
                add("&f造成 攻擊魔傷*0.6+智慧*0.8 傷害")
                add("")
                add("&7Lv2 ➤ &7恢復改為 攻擊魔傷*1.2+智慧*1")
                add("&7Lv2 ➤ &7傷害改為 攻擊魔傷*0.8+智慧*1")
                add("&7Lv2 ➤ &7自身無敵時間增加至3秒")
                add("&7Lv3 ➤ &7恢復改為 攻擊魔傷*1.6+智慧*1.2")
                add("&7Lv3 ➤ &7傷害改為 攻擊魔傷*1.2+智慧*1.2")
                add("&7Lv2 ➤ &7自身無敵時間增加至5秒")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("totem" to 45),
        {it.getSkillData("圖騰之主").level >=5&&it.getSkillData("聖靈圖騰聖滅").level >=1}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);