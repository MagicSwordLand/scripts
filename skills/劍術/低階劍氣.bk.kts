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
        "低階劍氣", //同時也是這個技能的id
        "&6低階劍氣",
        3, //最大等級
        2.0, //魔力消耗
        3.5  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
            .setMaterial(Material.COBWEB) // 材質
            .setModelData(0) // model
            .setDisplayName("&6低階劍氣 &7Lv.${data.level}&8/&73") // 物品名稱
            .addLore( // 物品的 lore
                    buildList {
                        add("")
                        add("&7▪ 類型：&f主動")
                        add("&7▪ 消耗：&f2 魔力")
                        add("&7▪ 冷卻：&f3.5 秒")
                        if(data.isEmpty){
                            add("&7▪ 前置：&f低階劍術 Lv.5")
                            //return@buildList  // 返回 不再繼續往下
                        }
                        add("")
                        add("&f將魔力加持在劍上")
                        add("&f向前揮出一道劍氣")
                        add("&f造成 攻擊傷害*1.25+體魄*1.5 傷害")
                        add("")
                        add("&7Lv2 ➤ &7技能傷害改為 &f攻擊傷害*1.5+體魄*1.5")
                        add("&7Lv2 ➤ &7冷卻減少為 &f4秒")
                        add("&7Lv3 ➤ &7技能傷害改為 &f攻擊傷害*2+體魄*2")
                        add("&7Lv3 ➤ &7冷卻減少為 &f2.5秒")
                        add("")
                    }
            )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
        PathElement(
                activeSkill,
                mutableMapOf("fencing" to 13),
                {it.getSkillData("低階劍術").level >=5}

        )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);