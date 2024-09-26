@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.core.gui.paths.PathElement
import net.brian.heroesskills.core.skills.PassiveMultipleStatSkill
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material

var passiveSkill = PassiveMultipleStatSkill(
    "圖騰守護神",
    "&6圖騰守護神", //顯示名稱
    10, //最大等級
    { player, data ->
        val map = mutableMapOf("MAX_MANA" to data.level*2.0,"MG_WEAPON_DAMAGE" to data.level*2.0)
        if(data.level > 9) map.put("MANA_REGENERATION" , 2.0);
        if(data.level > 9) map.put("MG_WEAPON_DAMAGE" , 50.0);
        map;
    },  //素質增加的量
    { player, data -> Icon() //底下設定物品
        .setMaterial(Material.LAPIS_LAZULI) // 材質
        .setModelData(0) // model
        .setDisplayName("&6圖騰守護神 &7Lv.${data.level}&8/&710") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f被動")
                add("&7▪ 消耗：&f無")
                add("&7▪ 冷卻：&f無")
                if(data.isEmpty){
                    add("&7▪ 前置：&f聖靈圖騰•治癒 Lv.1/靈威圖騰•靈傷 Lv.1")
                }
                add("")
                add("&f信仰的守護神回應你的崇拜")
                add("&f提供一部分自己了力量供你使用")
                add("")
                add("&7Lv1-10 ➤ 增加 &f2 &8(${data.level*2}) &7最大魔力")
                add("&7Lv1-10 ➤ 增加 &f2 &8(${data.level*2}) &7攻擊魔傷")
                add("&7Lv10 ➤ 額外增加 &f30 &7攻擊魔傷")
                add("&7Lv10 ➤ 增加 &f2 &7回魔")
                add("")
            }
        )
    }
);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("totem" to 31),
        {it.getSkillData("聖靈圖騰治癒").level >=1||it.getSkillData("靈威圖騰靈傷").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);