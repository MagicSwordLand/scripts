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
        "落花十月斬", //同時也是這個技能的id
        "&6劍技•落花十月斬",
        3, //最大等級
        35.0, //魔力消耗
        30.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
            .setMaterial(Material.BRAIN_CORAL) // 材質
            .setModelData(0) // model
            .setDisplayName("&6劍技•落花十月斬 &7Lv.${data.level}&8/&73") // 物品名稱
            .addLore( // 物品的 lore
                    buildList {
                        add("")
                        add("&7▪ 類型：&f主動")
                        add("&7▪ 消耗：&f35 魔力")
                        add("&7▪ 冷卻：&f30 秒")
                        if(data.isEmpty){
                            add("&7▪ 前置：&f高階劍術 Lv.5、神空破刺 Lv.1")
                            //return@buildList  // 返回 不再繼續往下
                        }
                        add("")
                        add("&f向周遭釋放大量劍氣")
                        add("&f形成劍氣的領域8秒")
                        add("&f進入該領域的敵人將受到持續傷害")
                        add("&f造成 攻擊傷害*1.8+體魄*1.5 攻擊傷害")
                        add("")
                        add("&7Lv2 ➤ &7技能傷害改為 &f攻擊傷害*2.25+體魄*1.75")
                        add("&7Lv2 ➤ &7領域維持時間增加2秒")
                        add("&7Lv3 ➤ &7技能傷害改為 &f攻擊傷害*2.75+體魄*2.25")
                        add("&7Lv3 ➤ &7領域結束時留在其中的敵人")
                        add("        &7額外受到 &f攻擊傷害*5")
                        add("")
                    }
            )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
        PathElement(
                activeSkill,
                mutableMapOf("fencing" to 53),
                {it.getSkillData("高階劍術").level >=5&&it.getSkillData("神空破刺").level >=1}
        )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);