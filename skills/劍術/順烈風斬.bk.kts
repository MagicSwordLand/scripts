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
        "順烈風斬", //同時也是這個技能的id
        "&6順烈風斬",
        3, //最大等級
        15.0, //魔力消耗
        20.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
            .setMaterial(Material.FEATHER) // 材質
            .setModelData(0) // model
            .setDisplayName("&6順烈風斬 &7Lv.${data.level}&8/&73") // 物品名稱
            .addLore( // 物品的 lore
                    buildList {
                        add("")
                        add("&7▪ 類型：&f主動")
                        add("&7▪ 消耗：&f15 魔力")
                        add("&7▪ 冷卻：&f20 秒")
                        if(data.isEmpty){
                            add("&7▪ 前置：&f無心劍意 Lv.5")
                            //return@buildList  // 返回 不再繼續往下
                        }
                        add("")
                        add("&f蓄力後向前方斬出1道帶有斬擊的風")
                        add("&f造成 攻擊傷害*1.5+體魄*1.5 攻擊傷害")
                        add("")
                        add("&7Lv2 ➤ &7技能傷害改為 &f攻擊傷害*1.75+體魄*1.75")
                        add("&7Lv2 ➤ &7額外斬出一道順烈風斬")
                        add("&7Lv3 ➤ &7技能傷害改為 &f攻擊傷害*2.5+體魄*2.25")
                        add("&7Lv3 ➤ &7額外斬出一道順烈風斬")
                        add("")
                    }
            )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
        PathElement(
                activeSkill,
                mutableMapOf("fencing" to 36),
                {it.getSkillData("無心劍意").level >=5}
        )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);