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
    "元素親和",
    "&6元素親和", //顯示名稱
    10, //最大等級
    { player, data ->
        val map = mutableMapOf("MAX_MANA" to data.level*2.0)
        if(data.level > 9) map.put("MAX_MANA" , 40.0);
        map;
    },  //素質增加的量
    { player, data -> Icon() //底下設定物品
        .setMaterial(Material.TOTEM_OF_UNDYING) // 材質
        .setModelData(0) // model
        .setDisplayName("&6元素親和 &7Lv.${data.level}&8/&710") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f被動")
                add("&7▪ 消耗：&f無")
                add("&7▪ 冷卻：&f無")
                add("")
                add("&f與元素精靈建立良好的關係")
                add("&f元素精靈將提供儲存魔力的空間")
                add("")
                add("&7Lv1-10 ➤ 增加 &f2 &8(${data.level*2}) &7最大魔力")
                add("&7Lv10 ➤ 額外增加 &f20 &7最大魔力")
                add("")
            }
        )
    }
);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("element" to 4)
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);