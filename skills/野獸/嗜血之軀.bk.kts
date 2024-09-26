@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.core.gui.paths.PathElement
import net.brian.heroesskills.core.gui.paths.conditions.SkillLevelCondition
import net.brian.heroesskills.core.skills.*
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material
import java.util.function.BiFunction

var passiveSkill = MythicActiveSkill (
    "嗜血之軀",
    "&6嗜血之軀", //顯示名稱
    3,
    8.0,
    15.0,)
{ player, data -> Icon()
    .setMaterial(Material.REDSTONE) // 材質
    .setModelData(0) // model
    .setDisplayName("&6嗜血之軀 &7Lv.${data.level}&8/&73") // 物品名稱
    .addLore( // 物品的 lore
        buildList {
            add("")
            add("&7▪ 類型：&f主動")
            add("&7▪ 消耗：&f8 魔力")
            add("&7▪ 冷卻：&f15 秒")
            if(data.isEmpty){
                add("&7▪ 前置：&f野獸之軀 Lv.5")
                //return@buildList  // 返回 不再繼續往下
            }
            add("")
            add("&f使自身的野獸之軀變得渴望血液")
            add("&f這使得野獸之軀能夠帶來短時間的增益效果")
            add("&f5秒內獲得移動加速I、每秒恢復 1% 血量")
            add("")
            add("&7Lv2 ➤ 恢復血量改為 2%")
            add("&7Lv3 ➤ 恢復血量改為 3%")
            add("")
        }
    )
};

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("wildbeast" to 21),
        {it.getSkillData("野獸之軀").level >=5}
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);