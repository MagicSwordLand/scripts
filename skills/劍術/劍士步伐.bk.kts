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
        "劍士步伐", //同時也是這個技能的id
        "&6劍士步伐",
        3, //最大等級
        5.0, //魔力消耗
        15.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
            .setMaterial(Material.LEATHER_BOOTS) // 材質
            .setModelData(0) // model
            .setDisplayName("&6劍士步伐 &7Lv.${data.level}&8/&73") // 物品名稱
            .addLore( // 物品的 lore
                    buildList {
                        add("")
                        add("&7▪ 類型：&f主動")
                        add("&7▪ 消耗：&f5 魔力")
                        add("&7▪ 冷卻：&f15 秒")
                        if(data.isEmpty){
                            add("&7▪ 前置：&f壯碩身軀 Lv.5")
                            //return@buildList  // 返回 不再繼續往下
                        }
                        add("")
                        add("&f將魔力附著在腳上")
                        add("&f使自身步伐更加輕盈")
                        add("&f5秒內受到攻擊有機率迴避傷害")
                        add("")
                        add("&7Lv1-3 ➤ 增加 &f15% &8(${data.level*15}%) &7迴避機率")
                        add("&7Lv2 ➤ &7閃避秒數提升至 &f6秒")
                        add("&7Lv3 ➤ &7閃避秒數提升至 &f8秒")
                        add("")
                    }
            )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
        PathElement(
                activeSkill,
                mutableMapOf("fencing" to 23),
                {it.getSkillData("壯碩身軀").level >=5}
        )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);