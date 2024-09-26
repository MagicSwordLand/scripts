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
    "生命氣息", //同時也是這個技能的id
    "&6生命氣息",
    3, //最大等級
    0.0, //魔力消耗
    1.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.HORN_CORAL_FAN) // 材質
        .setModelData(0) // model
        .setDisplayName("&6生命氣息 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f10 魔力")
                add("&7▪ 冷卻：&f18 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f狂暴野獸 Lv.5")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f開啟期間每秒恢復2%血量")
                add("&f最多恢復至最大血量的50%")
                add("&f當最大血量恢復至50%觸發以下效果")
                add("&f◆ 周遭10格隊友等級小於自身 - 減傷10%")
                add("&f◆ 周遭10格隊友等級大於自身 - 減傷5%")
                add("&c&n※開啟技能時每秒消耗 5 魔力")
                add("&c&n※魔力需多於 15 才能發動")
                add("")
                add("&7Lv2 ➤ &7周遭10格隊友等級小於自身 - 減傷15%")
                add("&7Lv2 ➤ &7周遭10格隊友等級大於自身 - 減傷10%")
                add("&7Lv3 ➤ &7開啟時自身減傷10%")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("wildbeast" to 44),
        {it.getSkillData("狂暴野獸").level >=5}
    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);