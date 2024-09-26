@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.classcore.ReachLevelObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.GiveItemObjective
import net.brian.scriptedquests.objectives.ListenNPCObjective
import net.brian.scriptedquests.objectives.ListenTalkObjective
import net.brian.scriptedquests.objectives.MoveToObjective
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import java.lang.Compiler.command




val questID = "main_ch1_02"

class MainCh1_02: Quest(questID,"§7[§c主線委託§7] §f第一章-殲滅與賞金"){


    val playerIcon = "%squests_icon%";
    val namoIcon = IconFonts.getNPC(4);
    val mayleeIcon = IconFonts.getNPC(59);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val mayleeNPC = "§c● §e梅里§r:";
    private val namoNPC = "§c● §e那莫斯克§r:";

    private val mes1 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e梅里~最近有甚麼特別的任務嗎~",
            ),
            *Utils.getMessage(mayleeIcon,
                    "$mayleeNPC",
                    "&x&d&6&d&0&b&8恩…我看看阿…",
                    "&x&d&6&d&0&b&8啊!有了，最近有一個特級委託",
                    "&x&d&6&d&0&b&8真難得這時間還有特級委託阿",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e特級委託?",
                    "§x§d§6§c§1§a§e是什麼樣子的任務?",
            ),
            *Utils.getMessage(mayleeIcon,
                    "$mayleeNPC",
                    "&x&d&6&d&0&b&8我看看…",
                    "&x&d&6&d&0&b&8哎呀，是殲滅任務呢",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e殲滅任務...",
                    "§x§d§6§c§1§a§e感覺難度比找錢包這種難多了",
                    "§x§d§6§c§1§a§e懸賞人是誰啊?",
            ),
            *Utils.getMessage(mayleeIcon,
                    "$mayleeNPC",
                    "&x&d&6&d&0&b&8這位委託人是匿名委託的",
                    "&x&d&6&d&0&b&8所以我也不太清楚呢",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那任務內容是甚麼?",
            ),
            *Utils.getMessage(mayleeIcon,
                    "$mayleeNPC",
                    "&x&d&6&d&0&b&8任務內容是殲滅彼遺古岸的怪物",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e殲滅…這也太難了吧！",
                    "§x§d§6§c§1§a§e彼遺古岸的怪物...",
                    "§x§d§6§c§1§a§e數量眾多還非常難纏...",
            ),
            *Utils.getMessage(mayleeIcon,
                    "$mayleeNPC",
                    "&x&d&6&d&0&b&8對新人傭兵來說的確有點難度",
                    "&x&d&6&d&0&b&8不然怎麼可能都沒人接呢?",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e不過…試試也無訪",
                    "§x§d§6§c§1§a§e但還是找人一起去才行",
            )
    )
    private val mes2 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那莫斯克!",
            ),
            *Utils.getMessage(namoIcon,
                    "$namoNPC",
                    "&x&d&6&d&0&b&8你是說那個都沒人要接的特級任務!?",
                    "&x&d&6&d&0&b&8你當你是白骨殺手嗎!?",
            ),
            *Utils.getMessage(namoIcon,
                    "$namoNPC",
                    "&x&d&6&d&0&b&8我們聽了都退避三舍的任務",
                    "&x&d&6&d&0&b&8你居然眼都不眨就接下了…",
                    "&x&d&6&d&0&b&8我猜猜你想找我帶你去，是吧?",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e就想問問看你能不能幫我一下...",
            ),
            *Utils.getMessage(namoIcon,
                    "$namoNPC",
                    "&x&d&6&d&0&b&8但是我自己還有委託",
                    "&x&d&6&d&0&b&8所以…",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e這樣啊…",
                    "§x§d§6§c§1§a§e沒關係，我還是嘗試看看好了！",
            ),

            )
    private val mes3 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e小怪都清得差不多了",
            )
    )
    private val mes5 = arrayOf(
            *Utils.getMessage(mayleeIcon,
                    "$mayleeNPC",
                    "&x&d&6&d&0&b&8天啊！沒想到你真的完成委託了",
                    "&x&d&6&d&0&b&8難得有新人傭兵完成特級委託",
                    "&x&d&6&d&0&b&8恭喜你!",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e哈哈哈，沒想到我真的做到了",
                    "§x§d§6§c§1§a§e接下來所有任務都沒什麼好怕了呢~",
                    "§x§d§6§c§1§a§e回去跟那莫斯克炫耀一下，哈哈",
            )
    )

    init {
        cancelAble = false
        val obj0 = ReachLevelObjective("obj0", 2)
                .setInstruction("§72等後將繼續任務");
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",59,*mes1)
                .setInstruction("§7與 §e梅里 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",4,*mes2)
                .setInstruction("§7與 §e那莫斯克 §7對話");
        val obj3 = MoveToObjective(this,"obj3",Location(world,-468.87, 69.00, 632.80),8.0)
                .setInstruction("§7前往彼遺古岸");
        val obj4 = KillMultiMobsObjective(this,"obj4", mapOf(
                "彼遺古岸_" to 20))
                .setInstruction{ "§7擊殺  ${it.get("彼遺古岸_")}/20 個彼遺古岸的怪物"}
        val obj5 = ListenTalkObjective(this,"obj5",*mes3)
        val obj6 = ListenNPCObjective(this,"obj6",59,*mes5)
                .setInstruction("§7與 §e梅里 §7對話");

        pushObjective(obj0,obj1,obj2,obj3,obj4,obj5,obj6)

        addRewards(
                MoneyReward(30), //30錢幣
                QuestExpReward(15), //15傭兵聲望
                MessageReward("&a➯ &7素材: &7傭兵團的認證"),
                ClassExpReward(100),
        )
        addEndHook({
            Utils.command("get ${it.name} QUESTMATERIAL QM009")
            Utils.command("sq start ${it.name} main_ch1_03")
        })

    }
}


val quest = MainCh1_02();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



