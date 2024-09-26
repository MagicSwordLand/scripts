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
    "致命劍士",
    "&6致命劍士", //顯示名稱
    10, //最大等級
    { player, data ->
        val map = mutableMapOf("CRITICAL_STRIKE_POWER" to data.level*1.0,"MAX_MANA" to data.level*1.5)
        if(data.level > 9) map.put("CRITICAL_STRIKE_POWER" , 20.0);
        map;
    },  //素質增加的量
    { player, data -> Icon() //底下設定物品
        .setMaterial(Material.NETHERITE_SWORD) // 材質
        .setModelData(0) // model
        .setDisplayName("&6致命劍士 &7Lv.${data.level}&8/&710") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f被動")
                add("&7▪ 消耗：&f無")
                add("&7▪ 冷卻：&f無")
                if(data.isEmpty){
                    add("&7▪ 前置：&f幻刀劍武 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f緊緊握著手上的武器")
                add("&f這一擊我就要擊敗你！")
                add("")
                add("&7Lv1-10 ➤ 增加 &f1% &8(${data.level*1}%) &7暴擊傷害")
                add("&7Lv1-10 ➤ 增加 &f1.5 &8(${data.level*1.5}) &7最大魔力")
                add("&7Lv10 ➤ 額外增加 &f10% &7暴擊傷害")
                add("")
            }
        )
    }
);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("fencing" to 43),
        {it.getSkillData("幻刀劍武").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);