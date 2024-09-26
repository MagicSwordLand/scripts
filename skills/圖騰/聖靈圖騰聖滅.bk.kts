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
    "聖靈圖騰聖滅", //同時也是這個技能的id
    "&6聖靈圖騰•聖滅",
    3, //最大等級
    40.0, //魔力消耗
    0.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.BLAZE_ROD) // 材質
        .setModelData(0) // model
        .setDisplayName("&6聖靈圖騰•聖滅 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f40 魔力")
                add("&7▪ 冷卻：&f25 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f聖靈守護神 Lv.5")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f放置一個聖靈圖騰，持續10秒")
                add("&f每0.5秒對15格內隨機1個敵人射出聖滅")
                add("&f造成 攻擊魔傷*1.2+智慧*1.5 傷害")
                add("")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊魔傷*1.5+智慧*1.8")
                add("&7Lv2 ➤ &7額外增加造成一名敵人")
                add("&7Lv3 ➤ &7技能傷害改為 &f攻擊魔傷*1.8+智慧*2.35")
                add("&7Lv3 ➤ &7射中敵人時對敵人周遭額外造成一次傷害")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("totem" to 36),
        {it.getSkillData("聖靈守護神").level >=5}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);