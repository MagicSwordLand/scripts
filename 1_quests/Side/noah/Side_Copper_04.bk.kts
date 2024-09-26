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
val NPC1Icon = IconFonts.getNPC(55);
val NPC2Icon = IconFonts.getNPC(135);
val NPC3Icon = IconFonts.getNPC(57);

val questID = "side_copper_04"

class Side_Copper_04: Quest(questID,"§7[§b銅牌支線§7] §f稻草的訂單"){



    private val playerName = "§c● §f%player_displayname%§r:";
    private val NPC1 = "§c● §e赫巴斯§r:";
    private val NPC2 = "§c● §e阿妮塔§r:";
    private val NPC3 = "§c● §e恩費§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8今年真的是大豐收啊！",
            "&x&d&6&d&0&b&8碼頭那邊的稻草緊缺人手",
            "&x&d&6&d&0&b&8正好我跟阿妮塔訂購了一些稻草",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8麻煩你了",
            "&x&d&6&d&0&b&8過去幫完忙後",
            "&x&d&6&d&0&b&8再把我訂購的稻草帶回來就好了",
        )
    )

    private val mes2 = arrayOf(
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8是基地的赫巴斯讓你來幫忙的嗎？",
            "&x&d&6&d&0&b&8他的訂單很快就要準備好了...",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8只是我人手不足，還未能整理...",
            "&x&d&6&d&0&b&8什麼你願意幫忙？太好了！",
        ),
        *Utils.getMessage(NPC2Icon,
            "$NPC2",
            "&x&d&6&d&0&b&8那麻煩你幫我收成 128 個 稻草 吧！",
            "&x&d&6&d&0&b&8這正好是赫巴斯訂購的數量",
        )

    )
    private val mes3 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8小子，速度挺快的嘛！但...",
            "&x&d&6&d&0&b&8我的妻子剛告訴我",
            "&x&d&6&d&0&b&8我們的稻草已經滿倉了...",
        ),
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8只能再麻煩你幫我去恩費那邊",
            "&x&d&6&d&0&b&8把稻草換成錢了...",
        )
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(NPC3Icon,
            "$NPC3",
            "&x&d&6&d&0&b&8尊敬的傭兵， 要販賣物品嗎",
            "&x&d&6&d&0&b&8是作物阿，這也是最常見的收購物品",
            "&x&d&6&d&0&b&8這是給你收購的錢",
        ),
        *Utils.getMessage(NPC3Icon,
            "$NPC3",
            "&x&d&6&d&0&b&8有什麼好東西記得再找我噢",
            "&x&d&6&d&0&b&8不要問我為什麼什麼都收哈哈",
        )
    )
    private val mes5 = arrayOf(
        *Utils.getMessage(NPC1Icon,
            "$NPC1",
            "&x&d&6&d&0&b&8太感恩了！採集的作物除了可以賣掉！",
            "&x&d&6&d&0&b&8也是可以用來在烘焙店製作麵包來食用哦~",
        )
    )
    init {
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",55,*mes1)
            .setInstruction("§7與 §e赫巴斯 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",135,*mes2)
            .setInstruction("§7與 §e阿妮塔 §7對話");
        val obj3 = GiveMultiItemObjective(this,"obj3",55,
            mapOf(
                "COLLECTMATERIAL:M032" to 128
            ))
            .setInstruction{"§7將 §7${it.get("COLLECTMATERIAL:M032")}/128 個稻草 §7交給 §e赫巴斯§r"}
        val obj4 = ListenNPCObjective(this,"obj4",55,*mes3)
            .setInstruction("§7與 §e赫巴斯 §7對話");
        val obj5 = ListenNPCObjective(this,"obj5",57,*mes4)
            .setInstruction("§7與 §e恩費 §7對話");
        val obj6 = ListenNPCObjective(this,"obj6",55,*mes5)
            .setInstruction("§7與 §e赫巴斯 §7對話");

        pushObjective(obj1,obj2,obj3,obj4,obj5,obj6)

        addRewards(
            ClassExpReward(200),
            MoneyReward(30), //30錢幣
            QuestExpReward(12), //15傭兵聲
        )
    }
}


val quest = Side_Copper_04();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



