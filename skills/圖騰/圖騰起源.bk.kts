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
    "圖騰起源",
    "&6圖騰起源", //顯示名稱
    10, //最大等級
    { player, data ->
        val map = mutableMapOf("MAX_MANA" to data.level*2.0,"MAX_HEALTH" to data.level*2.0)
        if(data.level > 9) map.put("MANA_REGENERATION" , 1.5);
        map;
    },  //素質增加的量
    { player, data -> Icon() //底下設定物品
        .setMaterial(Material.LIGHTNING_ROD) // 材質
        .setModelData(0) // model
        .setDisplayName("&6圖騰起源 &7Lv.${data.level}&8/&710") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f被動")
                add("&7▪ 消耗：&f無")
                add("&7▪ 冷卻：&f無")
                add("")
                add("&f古代人們將動物、植物等...")
                add("&f當作自己的守護神")
                add("&f並且相信它們會帶給自己力量")
                add("&f因此人們將這些守護神作為圖騰保護自己")
                add("")
                add("&7Lv1-10 ➤ 增加 &f2 &8(${data.level*2}) &7最大魔力")
                add("&7Lv1-10 ➤ 增加 &f2 &8(${data.level*1}) &7最大血量")
                add("&7Lv10 ➤ 額外增加 &f1.5 &7魔力恢復")
                add("")
            }
        )
    }
);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("totem" to 4)
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);