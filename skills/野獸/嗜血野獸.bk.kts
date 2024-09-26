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

var passiveSkill = PassiveMultipleStatSkill(
    "嗜血野獸",
    "&6嗜血野獸", //顯示名稱
    10, //最大等級
    { player, data ->
        val map = mutableMapOf("ATTACK_DAMAGE" to data.level*1.75,"MAX_HEALTH" to data.level*2.0)
        if(data.level > 9) map.put("MAX_HEALTH" , 50.0)
        if(data.level > 9) map.put("CRITICAL_STRIKE_POWER" , 10.0);
        map;
    },  //素質增加的量
    { player, data -> Icon() //底下設定物品
        .setMaterial(Material.FIRE_CORAL_FAN) // 材質
        .setModelData(0) // model
        .setDisplayName("&6嗜血野獸 &7Lv.${data.level}&8/&710") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f被動")
                add("&7▪ 消耗：&f無")
                add("&7▪ 冷卻：&f無")
                if(data.isEmpty){
                    add("&7▪ 前置：&f嗜血撕咬 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f化為渴望鮮血的野獸")
                add("&f不顧一切只為一口鮮美的血液")
                add("")
                add("&7Lv1-10 ➤ 增加 &f1.75&8(${data.level*1.75}) &7攻擊傷害")
                add("&7Lv1-10 ➤ 增加 &f2&8(${data.level*2} &7最大血量")
                add("&7Lv10 ➤ 增加 &f10% &7暴擊傷害及 &f30 &7最大血量")
                add("")
            }
        )
    }
);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("wildbeast" to 37),
        {it.getSkillData("嗜血撕咬").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);