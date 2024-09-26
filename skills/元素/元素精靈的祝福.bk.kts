@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.core.gui.paths.PathElement
import net.brian.heroesskills.core.skills.MythicActiveSkill
import net.brian.heroesskills.core.skills.PassiveMultipleStatSkill
import net.brian.heroesskills.core.skills.PassiveStatSkill
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material
import java.util.function.BiFunction

var passiveSkill = PassiveMultipleStatSkill(
    "元素精靈的祝福",
    "&6元素精靈的祝福", //顯示名稱
    5, //最大等級
    { player, data ->
        val map = mutableMapOf("MAX_MANA" to data.level*2.0,"MG_WEAPON_DAMAGE" to data.level*2.0)
        if(data.level > 4) map.put("MANA_REGENERATION" , 2.0);
        map;
    },  //素質增加的量
    { player, data -> Icon() //底下設定物品
        .setMaterial(Material.BELL) // 材質
        .setModelData(0) // model
        .setDisplayName("&6元素精靈的祝福 &7Lv.${data.level}&8/&75") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f被動")
                add("&7▪ 消耗：&f無")
                add("&7▪ 冷卻：&f無")
                if(data.isEmpty){
                    add("&7▪ 前置：&f元素推擊 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f元素精靈降下的祝福")
                add("&f使自身的魔力儲量增加")
                add("")
                add("&7Lv1-5 ➤ 增加 &f2 &8(${data.level*2}) &7最大魔力")
                add("&7Lv1-5 ➤ 增加 &f2 &8(${data.level*2}) &7攻擊魔傷")
                add("&7Lv5 ➤ 增加 &f2 &7回魔")
                add("")
            }
        )
    }
);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("element" to 22),
        {it.getSkillData("元素推擊").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);