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
    "野獸之軀",
    "&6野獸之軀", //顯示名稱
    5, //最大等級
    { player, data ->
        val map = mutableMapOf("MAX_HEALTH" to data.level*10.0)
        if(data.level > 4) map.put("MAX_HEALTH" , 70.0);
        map;
    },  //素質增加的量
    { player, data -> Icon() //底下設定物品
        .setMaterial(Material.LEATHER_CHESTPLATE) // 材質
        .setModelData(0) // model
        .setDisplayName("&6野獸之軀 &7Lv.${data.level}&8/&75") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f被動")
                add("&7▪ 消耗：&f無")
                add("&7▪ 冷卻：&f無")
                if(data.isEmpty){
                    add("&7▪ 前置：&f野獸怒吼 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f將自身的身軀鍛鍊成能承受野獸之力")
                add("")
                add("&7Lv1-4 ➤ 增加 &f10&8(${data.level*10}) &7最大生命")
                add("&7Lv5 ➤ 額外增加 &f20 &7最大生命")
                add("")
            }
        )
    }
);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("wildbeast" to 22),
        {it.getSkillData("野獸怒吼").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);