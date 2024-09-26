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
val NPC1Icon = IconFonts.getNPC(97);
val NPC2Icon = IconFonts.getNPC(114);

val questID = "side_silver_01"

class Side_Silver_01: Quest(questID,"§7[§b銀牌支線§7] §f行商幫手"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e尤爾達§r:";
    private val NPC2 = "§c● §e切克鬧§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8好忙喔..抽不開身阿！",
            "&x&d&6&d&0&b&8欸欸欸！",
            "&x&d&6&d&0&b&8這位傭兵你有時間嗎！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e怎麼了?",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8我是行商商隊隊長",
            "&x&d&6&d&0&b&8目前有一批貨在我朋友那裏",
            "&x&d&6&d&0&b&8但我實在是太忙了",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8不知道你有沒有時間",
            "&x&d&6&d&0&b&8可以幫我去跟他拿一下嗎?",
            "&x&d&6&d&0&b&8他就在諾亞礦場附近工作",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e就交給我吧！",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8喔?有事嗎孩子?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e請問你這裡有給行商商隊的東西嗎?",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8是尤爾達請你過來的嗎?",
            "&x&d&6&d&0&b&8東西的確在我這",
            "&x&d&6&d&0&b&8但我現在還在工作",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8還是你能幫我?",
            "&x&d&6&d&0&b&8這樣我就能夠提早完成工作",
            "&x&d&6&d&0&b&8你也能提早拿到東西！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e痾...好吧...",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8太棒了！",
            "&x&d&6&d&0&b&8幫我挖鑌礦x256、生金礦x32",
            "&x&d&6&d&0&b&8挖完後給我就好，感謝！",
        ),
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8太棒了！",
            "&x&d&6&d&0&b&8這樣我今天的工作就完成了",
            "&x&d&6&d&0&b&8這就是尤爾達要的東西",
        )
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8太好了！",
            "&x&d&6&d&0&b&8你來得剛剛好",
            "&x&d&6&d&0&b&8東西拿到手了嗎?",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e東西在這",
            "§x§d§6§c§1§a§e給你吧",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8太謝謝你了",
            "&x&d&6&d&0&b&8這是你的酬勞",
        )
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",97,*mes1)
            .setInstruction("§7與 §e尤爾達 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",114,*mes2)
            .setInstruction("§7與 §e切克鬧 §7對話");
        val obj3 = GiveMultiItemObjective(this,"obj3",114,
            mapOf(
                "COLLECTMATERIAL:M001" to 256,
                "COLLECTMATERIAL:M007" to 32
            ))
            .setInstruction{"§7將 §7${it.get("COLLECTMATERIAL:M001")}/256 個鑌礦、§7${it.get("COLLECTMATERIAL:M007")}/32 個生金礦 §7交給 §e切克鬧§r"}
        val obj4 = ListenNPCObjective(this,"obj4",114,*mes3)
            .setInstruction("§7與 §e切克鬧 §7對話")
            .setEndProcess {
                it.sendMessage("§7[§c提示§7] §f獲得商隊物資")
            };
        val obj5 = ListenNPCObjective(this,"obj5",97,*mes4)
            .setInstruction("§7與 §e尤爾達 §7對話");
        pushObjective(obj1,obj2,obj3,obj4,obj5)

        addRewards(
            MoneyReward(100), //30錢幣
            QuestExpReward(30), //15傭兵聲
            ClassExpReward(300)
        )
    }
}


val quest = Side_Silver_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



