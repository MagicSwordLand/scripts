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
    "狂暴野獸",
    "&6狂暴野獸", //顯示名稱
    10, //最大等級
    { player, data ->
        val map = mutableMapOf("CRITICAL_STRIKE_CHANCE" to data.level*0.5,"MAX_HEALTH" to data.level*2.5)
        if(data.level > 9) map.put("CRITICAL_STRIKE_POWER" , 15.0);
        map;
    },  //素質增加的量
    { player, data -> Icon() //底下設定物品
        .setMaterial(Material.IRON_HORSE_ARMOR) // 材質
        .setModelData(0) // model
        .setDisplayName("&6狂暴野獸 &7Lv.${data.level}&8/&710") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f被動")
                add("&7▪ 消耗：&f無")
                add("&7▪ 冷卻：&f無")
                if(data.isEmpty){
                    add("&7▪ 前置：&f狂熱野心 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f")
                add("&f化為強悍且壯碩的野獸")
                add("&f狂暴的氣息能夠帶給周遭影響")
                add("")
                add("&7Lv1-10 ➤ 增加 &f0.5% &8(${data.level*0.5}%) &7暴擊機率")
                add("&7Lv1-10 ➤ 增加 &f2.5 &8(${data.level*2.5}) &7最大血量")
                add("&7Lv10 ➤ 額外增加 &f15% &7暴擊傷害")
                add("")
            }
        )
    }
);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("wildbeast" to 43),
        {it.getSkillData("狂熱野心").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);