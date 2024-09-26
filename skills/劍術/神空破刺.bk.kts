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
        "神空破刺", //同時也是這個技能的id
        "&6神空破刺",
        3, //最大等級
        10.0, //魔力消耗
        18.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
            .setMaterial(Material.AMETHYST_CLUSTER) // 材質
            .setModelData(0) // model
            .setDisplayName("&6神空破刺 &7Lv.${data.level}&8/&73") // 物品名稱
            .addLore( // 物品的 lore
                    buildList {
                        add("")
                        add("&7▪ 類型：&f主動")
                        add("&7▪ 消耗：&f10 魔力")
                        add("&7▪ 冷卻：&f18 秒")
                        if(data.isEmpty){
                            add("&7▪ 前置：&f致命劍士 Lv.5")
                            //return@buildList  // 返回 不再繼續往下
                        }
                        add("")
                        add("&f將劍氣集中後向前刺擊")
                        add("&f造成 攻擊傷害*2+體魄*1.5 攻擊傷害")
                        add("&f並且額外造成敵人流血3秒")
                        add("&f每秒造成 技能傷害/2.5")
                        add("")
                        add("&7Lv2 ➤ &7技能傷害改為 &f攻擊傷害*2.3+體魄*1.8")
                        add("&7Lv2 ➤ &7增加流血時間2秒")
                        add("&7Lv3 ➤ &7技能傷害改為 &f攻擊傷害*2.75+體魄*2.25")
                        add("&7Lv3 ➤ &7被擊中的敵人獲得緩速II五秒")
                        add("")
                    }
            )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
        PathElement(
                activeSkill,
                mutableMapOf("fencing" to 44),
                {it.getSkillData("致命劍士").level >=5}
        )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);