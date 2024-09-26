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



val questID = "side_silver_04"

class Side_Silver_04: Quest(questID,"§7[§b銀牌支線§7] §f艾莉絲的復仇"){
    val playerIcon = "%squests_icon%";
    val NPC1Icon = IconFonts.getNPC(164);


    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e艾莉絲§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8...傭兵好久不見",
        ),

        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e妳的心情好點了嗎?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8不...",
            "&x&d&6&d&0&b&8我的心情依舊難以平復",
            "&x&d&6&d&0&b&8我始終忘不掉哈比的死因",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8它是為了保護我而死的...",
            "&x&d&6&d&0&b&8我...我對不起它",
            "&x&d&6&d&0&b&8我想為它報仇",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e保護?發生什麼事情了..?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我陣子出鎮採集藥草",
            "&x&d&6&d&0&b&8沒想到突然冒出一個盜賊",
            "&x&d&6&d&0&b&8想要搶劫我身上的財產",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8哈比為了保護我",
            "&x&d&6&d&0&b&8與那個盜賊搏鬥",
            "&x&d&6&d&0&b&8最後盜賊跑走了，但哈比...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8傭兵！",
            "&x&d&6&d&0&b&8求你了",
            "&x&d&6&d&0&b&8幫我報仇吧！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e我...好吧...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8那位盜賊腳上應該有哈比的咬痕",
            "&x&d&6&d&0&b&8就拜託你了...",
            "&x&d&6&d&0&b&8(啜泣)",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這個盜賊腳上有咬痕",
            "§x§d§6§c§1§a§e看來就是他了",
        ),
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8太感謝你了...",
            "&x&d&6&d&0&b&8希望這樣哈比也能瞑目...",
        ),

        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e安息...妳也要注意身體",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我會的",
            "&x&d&6&d&0&b&8這是獎勵",
        ),
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",164,*mes1)
            .setInstruction("§7與 §e艾莉絲 §7對話");
        val obj2 = KillMobsObjective(this,"obj2","盜賊據點_",40)
            .setInstruction { "擊殺 ${it.amount}/40 隻盜賊" }
        val obj3 = ListenTalkObjective(this,"obj3",*mes2)
            .setInstruction("§7檢查一下盜賊的腳");
        val obj4 = ListenNPCObjective(this,"obj4",164,*mes3)
            .setInstruction("§7與 §e艾莉絲 §7對話");
        pushObjective(obj1,obj2,obj3,obj4)

        addRewards(
            MoneyReward(80), //30錢幣
            QuestExpReward(35), //15傭兵聲
            ClassExpReward(320)
        )
    }
}


val quest = Side_Silver_04();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



