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
    "野獸怒吼", //同時也是這個技能的id
    "&6野獸怒吼",
    3, //最大等級
    2.0, //魔力消耗
    8.0  //冷卻  秒數
) { player, data -> //底下設定物品
    Icon()
        .setMaterial(Material.DEAD_HORN_CORAL_FAN) // 材質
        .setModelData(0) // model
        .setDisplayName("&6野獸怒吼 &7Lv.${data.level}&8/&73") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f主動")
                add("&7▪ 消耗：&f2 魔力")
                add("&7▪ 冷卻：&f8 秒")
                if(data.isEmpty){
                    add("&7▪ 前置：&f野獸之力 Lv.5")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f解放身體裡野獸的力量")
                add("&f向前怒吼使敵人感到畏懼")
                add("&f造成 攻擊傷害*1.25+體魄*1.2 傷害")
                add("&f且受到傷害的敵人獲得 移動緩速I 1秒")
                add("")
                add("&7Lv2 ➤ &7技能傷害改為 &f攻擊傷害*1.5+體魄*2")
                add("&7Lv3 ➤ &7技能傷害改為 &f攻擊傷害*2+體魄*2.5")
                add("&7Lv3 ➤ &7移動緩速I持續至 &f3秒")
                add("")
            }
        )
}
HeroesSkills.getInstance().skillManager.register(activeSkill);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        activeSkill,
        mutableMapOf("wildbeast" to 13),
        {it.getSkillData("野獸之力").level >=5}

    )
)
HeroesSkills.getInstance().skillManager.register(activeSkill);