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
    "聖靈守護神",
    "&6聖靈守護神", //顯示名稱
    10, //最大等級
    { player, data ->
        val map = mutableMapOf("MAX_MANA" to data.level*2.0,"MANA_REGENERATION" to data.level*0.5)
        if(data.level > 9) map.put("MAX_MANA" , 20.0);
        if(data.level > 9) map.put("MG_WEAPON_DAMAGE" , 50.0);
        map;
    },  //素質增加的量
    { player, data -> Icon() //底下設定物品
        .setMaterial(Material.LANTERN) // 材質
        .setModelData(0) // model
        .setDisplayName("&6聖靈守護神 &7Lv.${data.level}&8/&710") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f被動")
                add("&7▪ 消耗：&f無")
                add("&7▪ 冷卻：&f無")
                if(data.isEmpty){
                    add("&7▪ 前置：&f聖靈圖騰•聖域 Lv.1")
                    //return@buildList  // 返回 不再繼續往下
                }
                add("")
                add("&f與水元素精靈溝通達到心如止水的境界")
                add("&f在此境界中，魔力回復的速度將大幅提升")
                add("")
                add("&7Lv1-10 ➤ 增加 &f2 &8(${data.level*2}) &7最大魔力")
                add("&7Lv1-10 ➤ 增加 &f0.5 &8(${data.level*0.5}) &7回魔")
                add("&7Lv10 ➤ 額外增加 &f20 &7最大魔力")
                add("&7Lv10 ➤ 額外增加 &f50 &7攻擊魔傷")
                add("")
            }
        )
    }
);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("totem" to 37),
        {it.getSkillData("聖靈圖騰聖域").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);