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
    "潛能解放狂暴野獸", //同時也是這個技能的id
    "&6潛能解放•狂暴野獸",
    3, //最大等級
    35.0, //魔力消耗
    25.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.HORN_CORAL) // 材質
        .setModelData(0) // model
        .setDisplayName("&6潛能解放•狂暴野獸 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f35 魔力")
                add("&7▪ 冷卻：&f25 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f野獸之王 Lv.5、生命氣息 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f解放身體內躁動的狂暴潛能")
                add("&f5秒內移動速度增加且減傷60%")
                add("&f周遭15格隊友獲得減傷20%")
                add("&f自身近戰攻擊時傷害變為2.5倍")
                add("&f半徑10格內的敵人會因為威壓緩速")
                add("&f且受到的傷害增加10%")
                add("")
                add("&7Lv2 ➤ &7效果持續時間增加8秒")
                add("&7Lv2 ➤ &7減傷效果增加至80%")
                add("&7Lv3 ➤ &7敵人受到傷害增加提高至30%")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("wildbeast" to 53),
        {it.getSkillData("野獸之王").level >=5&&it.getSkillData("生命氣息").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);