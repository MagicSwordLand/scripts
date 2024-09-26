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
        "壯碩身軀",
        "&6壯碩身軀", //顯示名稱
        5, //最大等級
        { player, data ->
            val map = mutableMapOf("MAX_HEALTH" to data.level*5.0)
            if(data.level > 4) map.put("MAX_HEALTH" , 50.0);
            map;
        },  //素質增加的量
        { player, data -> Icon() //底下設定物品
                .setMaterial(Material.APPLE) // 材質
                .setModelData(0) // model
                .setDisplayName("&6壯碩身軀 &7Lv.${data.level}&8/&75") // 物品名稱
                .addLore( // 物品的 lore
                        buildList {
                            add("")
                            add("&7▪ 類型：&f被動")
                            add("&7▪ 消耗：&f無")
                            add("&7▪ 冷卻：&f無")
                            if(data.isEmpty){
                                add("&7▪ 前置：&f低階劍氣 Lv.1")
                                //return@buildList  // 返回 不再繼續往下
                            }
                            add("")
                            add("&f身為一個劍士")
                            add("&f想要學會更強大的劍術")
                            add("&f當然需要更強大的身軀")
                            add("")
                            add("&7Lv1-4 ➤ 增加 &f5&8(${data.level*5}) &7最大生命")
                            add("&7Lv5 ➤ 額外增加 &f25 &7最大生命")
                            add("")
                        }
                )
        }
);

HeroesSkills.getInstance().mainPathGui.registerElement(
        PathElement(
                passiveSkill,
                mutableMapOf("fencing" to 22),
                {it.getSkillData("低階劍氣").level >=1}
        )
)
HeroesSkills.getInstance().skillManager.register(passiveSkill);