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
        "劍氣龍捲風", //同時也是這個技能的id
        "&6劍技•劍氣龍捲風",
        3, //最大等級
        30.0, //魔力消耗
        25.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
            .setMaterial(Material.NETHER_STAR) // 材質
            .setModelData(0) // model
            .setDisplayName("&6劍技•劍氣龍捲風 &7Lv.${data.level}&8/&73") // 物品名稱
            .addLore( // 物品的 lore
                    buildList {
                        add("")
                        add("&7▪ 類型：&f主動")
                        add("&7▪ 消耗：&f30 魔力")
                        add("&7▪ 冷卻：&f25 秒")
                        if(data.isEmpty){
                            add("&7▪ 前置：&f高階劍術 Lv.5、順烈風斬 Lv.1")
                            //return@buildList  // 返回 不再繼續往下
                        }
                        add("")
                        add("&f消耗大量魔力聚集劍氣")
                        add("&f並向前釋放緩慢的劍氣龍捲風")
                        add("&f劍氣龍捲風向前5格後消失")
                        add("&f每2秒造成 攻擊傷害*2+體魄*1.75 攻擊傷害")
                        add("")
                        add("&7Lv2 ➤ &7技能傷害改為 &f攻擊傷害*2.25+體魄*2")
                        add("&7Lv2 ➤ &7消失格數改為8格")
                        add("&7Lv3 ➤ &7技能傷害改為 &f攻擊傷害*3.5+體魄*2.5")
                        add("&7Lv3 ➤ &7龍捲風結束後原地釋放劍氣颶風")
                        add("        &7額外造成半徑10格敵人一半傷害")
                        add("")
                    }
            )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
        PathElement(
                activeSkill,
                mutableMapOf("fencing" to 45),
                {it.getSkillData("高階劍術").level >=5&&it.getSkillData("順烈風斬").level >=1}
        )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);