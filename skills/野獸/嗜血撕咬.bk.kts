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
    "嗜血撕咬", //同時也是這個技能的id
    "&6嗜血撕咬",
    3, //最大等級
    12.0, //魔力消耗
    18.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.WEEPING_VINES) // 材質
        .setModelData(0) // model
        .setDisplayName("&6嗜血撕咬 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f12 魔力")
                add("&7▪ 冷卻：&f18 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f野獸同化 Lv.5")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f向前方衝刺八格並且撕咬路徑上的五名敵人")
                add("&f造成 攻擊傷害*1.25+體魄*1.5 攻擊傷害")
                add("&f撕咬敵人時自身恢復造成傷害的5%血量")
                add("")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊傷害*1.5+體魄*1.75")
                add("&7Lv2 ➤ &7撕咬敵人時自身恢復造成傷害的7.5%血量")
                add("&7Lv3 ➤ &7技能傷害改為 &f攻擊傷害*2.25+體魄*2")
                add("&7Lv3 ➤ &7撕咬敵人時自身恢復造成傷害的12%血量")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("wildbeast" to 29),
        {it.getSkillData("野獸同化").level >=5&&it.getSkillData("嗜血之軀").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);