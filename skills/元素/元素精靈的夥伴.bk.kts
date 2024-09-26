@file:RequiredPlugins("AltSkill2-Totem","PlayerDataSync","HeroesSkills")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroesskills.HeroesSkills
import net.brian.heroesskills.core.gui.paths.PathElement
import net.brian.heroesskills.core.skills.PassiveMultipleStatSkill
import net.brian.heroesskills.core.utils.Icon
import org.bukkit.Material

var passiveSkill = PassiveMultipleStatSkill(
    "元素精靈的夥伴",
    "&6元素精靈的夥伴", //顯示名稱
    10, //最大等級
    { player, data ->
        val map = mutableMapOf("MAX_MANA" to data.level*2.5,"MG_WEAPON_DAMAGE" to data.level*2.0)
        if(data.level > 9) map.put("MAX_MANA" , 40.0);
        if(data.level > 9) map.put("MG_WEAPON_DAMAGE" , 50.0);
        map;
    },  //素質增加的量
    { player, data -> Icon() //底下設定物品
        .setMaterial(Material.PRISMARINE_SHARD) // 材質
        .setModelData(0) // model
        .setDisplayName("&6元素精靈的夥伴 &7Lv.${data.level}&8/&710") // 物品名稱
        .addLore( // 物品的 lore
            buildList {
                add("")
                add("&7▪ 類型：&f被動")
                add("&7▪ 消耗：&f無")
                add("&7▪ 冷卻：&f無")
                if(data.isEmpty){
                    add("&7▪ 前置：&f平波水紋 Lv.1/火元素濃縮球 Lv.1")
                }
                add("")
                add("&f與元素精靈成為共同奮戰的夥伴")
                add("&f元素精靈將提供更多的儲存魔力空間")
                add("")
                add("&7Lv1-10 ➤ 增加 &f2.5 &8(${data.level*2.5}) &7最大魔力")
                add("&7Lv1-10 ➤ 增加 &f2.0 &8(${data.level*2.0}) &7攻擊魔傷")
                add("&7Lv10 ➤ 額外增加 &f15 &7最大魔力")
                add("&7Lv10 ➤ 額外增加 &f30 &7攻擊魔傷")
                add("")
            }
        )
    }
);

HeroesSkills.getInstance().mainPathGui.registerElement(
    PathElement(
        passiveSkill,
        mutableMapOf("element" to 40),
        {it.getSkillData("平波水紋").level >=1||it.getSkillData("火元素濃縮球").level >=1}
    )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);