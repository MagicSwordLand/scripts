@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.core.gui.paths.PathElement
import net.brian.heroesskills.core.skills.PassiveMultipleStatSkill
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material

var passiveSkill = PassiveMultipleStatSkill(
    "圖騰之主",
    "&6圖騰之主", //顯示名稱
    10, //最大等級
    { player, data ->
        val map = mutableMapOf("MAX_MANA" to data.level*3.0,"MANA_REGENERATION" to data.level*0.35)
        if(data.level > 9) map.put("MAX_HEALTH" , 30.0);
        if(data.level > 9) map.put("MG_WEAPON_DAMAGE" , 80.0);
        map;
    },  //素質增加的量
    { player, data -> Icon() //底下設定物品
        .setMaterial(Material.PRISMARINE_SHARD) // 材質
        .setModelData(0) // model
        .setDisplayName("&6圖騰之主 &7Lv.${data.level}&8/&710") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f被動")
                add("&7▪ 消耗：&f無")
                add("&7▪ 冷卻：&f無")
                if(data.isEmpty){
                    add("&7▪ 前置：&f聖靈圖騰•聖滅 Lv.1/靈威圖騰•靈火 Lv.1")
                }
                add("")
                add("&f信仰的圖騰守護神將無私提供力量")
                add("")
                add("&7Lv1-10 ➤ 增加 &f3 &8(${data.level*2.5}) &7最大魔力")
                add("&7Lv1-10 ➤ 增加 &f0.35 &8(${data.level*0.35}) &7回魔")
                add("&7Lv10 ➤ 額外增加 &f30 &7最大血量")
                add("&7Lv10 ➤ 額外增加 &f80 &7攻擊魔傷")
                add("")
            }
        )
    }
);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("totem" to 40),
        {it.getSkillData("聖靈圖騰聖滅").level >=1||it.getSkillData("靈威圖騰靈火").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);