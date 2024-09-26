@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location

val playerIcon = "%squests_icon%";
val NPC1Icon = IconFonts.getNPC(164);

val questID = "daily_copper_11"

class Daily_Copper_11: Quest(questID,"§7[§a銅牌支線§7] §f留戀之情"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e艾莉絲§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8(呆呆著看著雕像)",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e妳還好嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8阿?",
            "&x&d&6&d&0&b&8我還好...",
            "&x&d&6&d&0&b&8只是...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這隻狗是妳曾經的寵物嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8對...它叫哈比",
            "&x&d&6&d&0&b&8是我唯一的家人",
            "&x&d&6&d&0&b&8它陪我度過人生的各個階段",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8現在它離開了...",
            "&x&d&6&d&0&b&8我很想它",
            "&x&d&6&d&0&b&8我...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我能為妳做些甚麼嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8哈比遇到我前是傭兵所養的狗",
            "&x&d&6&d&0&b&8因此比起一般的骨頭",
            "&x&d&6&d&0&b&8更喜歡彼遺古岸怪物掉落的骨頭",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8能請你幫我帶回來一些嗎?",
            "&x&d&6&d&0&b&8我想祭拜給哈比",
            "&x&d&6&d&0&b&8希望它在天上也能吃到喜歡的東西",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e交給我吧",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8謝謝..",
            "&x&d&6&d&0&b&8這些東西給你當作獎勵吧...",
        )
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",164,*mes1)
            .setInstruction("§7與 §e艾莉絲 §7對話");
        val obj2 = GiveMultiItemObjective(this,"obj2",164,
            mapOf(
                "MATERIAL:M003" to 10
            ))
            .setInstruction{"§7將 §7${it.get("MATERIAL:M003")}/10 個 §7受汙染的骨頭  §7交給 §e艾莉絲§r"}
        val obj3 = ListenNPCObjective(this,"obj3",164,*mes2)
            .setInstruction("§7與 §e艾莉絲 §7對話");

        pushObjective(obj1,obj2,obj3)

        addRewards(
            MoneyReward(12), //錢幣
            ClassExpReward(60),
            QuestExpReward(15) //傭兵聲望
        )
    }
}


val quest = Daily_Copper_11();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



