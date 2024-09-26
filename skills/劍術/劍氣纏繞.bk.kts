@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.core.gui.paths.PathElement
import net.brian.heroesskills.core.gui.paths.conditions.SkillLevelCondition
import net.brian.heroesskills.core.skills.*
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material
import java.util.function.BiFunction

var passiveSkill = MythicBindAbleTimerSkill (
    "劍氣纏繞",
    "&6劍氣纏繞", //顯示名稱
    20,
    3)
{ player, data -> Icon()
    .setMaterial(Material.DIAMOND_SWORD) // 材質
    .setModelData(0) // model
    .setDisplayName("&6劍氣纏繞 &7Lv.${data.level}&8/&73") // 物品名稱
    .addLore( // 物品的 lore
        buildList {
            add("")
            add("&7▪ 類型：&f被動 &8(綁定)")
            add("&7▪ 消耗：&f無")
            add("&7▪ 冷卻：&f1 秒")
            if(data.isEmpty){
                add("&7▪ 前置：&f壯碩身軀 Lv.5")
                //return@buildList  // 返回 不再繼續往下
            }
            add("")
            add("&f手持劍類時攻擊額外造成")
            add("&f職業等級+體魄*1.5 攻擊傷害")
            add("")
            add("&7Lv2 ➤ 技能傷害改為 職業等級*1.5+體魄*1.5")
            add("&7Lv3 ➤ 技能傷害改為 職業等級*1.8+體魄*2")
            add("")
        }
    )
};

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("fencing" to 21),
        {it.getSkillData("壯碩身軀").level >=5}
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);