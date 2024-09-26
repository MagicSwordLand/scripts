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


val questID = "side_activity_01"

class Side_Activity_01: Quest(questID,"§7[§d活動支線§7] §f艾靈長廊-幻境"){

    val playerIcon = "%squests_icon%";
    val nikaIcon = IconFonts.getNPC(147);
    val niaIcon = IconFonts.getNPC(148);
    val kinIcon = IconFonts.getNPC(188);
    val shanIcon = IconFonts.getNPC(197);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val nikaNPC = "§c● §e維洛妮卡§r:";
    private val niaNPC = "§c● §e維洛妮亞§r:";
    private val kinNPC = "§c● §e金§r:";
    private val shanNPC = "§c● §e閃§r:";

    private val mes1 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e維洛妮亞，你怎麼滿臉愁容?",
            "§x§d§6§c§1§a§e是最近都沒什麼事的關係嗎?",
        ),
        *Utils.getMessage(niaIcon,
            "$niaNPC",
            "§x§8§B§6§7§3§A唉…正巧相反，最近有件很嚴重的事發生了",
            "§x§8§B§6§7§3§A在我和姊姊使用靈力探測的時候",
            "§x§8§B§6§7§3§A總感覺有一股奇怪的波動在干擾我們",
        ),
        *Utils.getMessage(niaIcon,
            "$niaNPC",
            "§x§8§B§6§7§3§A就在前兩天聽到有人看到其外的紫色裂縫",
            "§x§8§B§6§7§3§A這時我就知道，是艾靈裂縫出現了",
            "§x§8§B§6§7§3§A但他只有在特定時間才會散發能量，我想只有那時才能進入...",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e艾靈裂縫?那是甚麼?",
            "§x§d§6§c§1§a§e跟艾靈一族難道有著甚麼關聯嗎?",
        ),
        *Utils.getMessage(nikaIcon,
            "$nikaNPC",
            "§x§8§B§6§7§3§A其實我也不清楚…他們艾靈雖然也是妖精",
            "§x§8§B§6§7§3§A但我們身為不同種族，必然不會了解…",
            "§x§8§B§6§7§3§A但艾靈裂縫就在旁邊的樹旁而已",
        ),
    )
    private val mes2 = arrayOf(
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e就是這嗎…?看起來… ",
            "§x§d§6§c§1§a§e總感覺要被吸進去了呢",
            "§x§d§6§c§1§a§e那個人是…",
        ),
    )
    private val mes3 = arrayOf(
        *Utils.getMessage(kinIcon,
            "$kinNPC",
            "§x§8§B§6§7§3§A你是誰?請不要太靠近裂縫",
            "§x§8§B§6§7§3§A這個裂縫是艾靈之境的考驗",
            "§x§8§B§6§7§3§A只有被認可之人才能順利通過",
        ),
        *Utils.getMessage(kinIcon,
            "$kinNPC",
            "§x§8§B§6§7§3§A凡人一旦進入，將有可能粉身碎骨",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e阿…甚麼跟甚麼阿…",
            "§x§d§6§c§1§a§e而且這裂縫到底怎麼出現的阿...?",
        ),
        *Utils.getMessage(kinIcon,
            "$kinNPC",
            "§x§8§B§6§7§3§A當艾靈出現動盪，便會出現裂縫找尋救世主",
            "§x§8§B§6§7§3§A而您…受到過艾靈一族的護佑…",
            "§x§8§B§6§7§3§A祝您一路順風",
        ),
    )
    private val mes4 = arrayOf(
        *Utils.getMessage(kinIcon,
            "$kinNPC",
            "§x§8§B§6§7§3§A果然您能順利通過",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這…是甚麼地方啊?",
        ),
        *Utils.getMessage(kinIcon,
            "$kinNPC",
            "§x§8§B§6§7§3§A這是艾靈幻境中的一項考驗",
            "§x§8§B§6§7§3§A一旦完成考核，即會獲得豐富獎賞",
            "§x§8§B§6§7§3§A同時，裂縫只有在不斷地完成後，才能夠修復",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e你說…豐富獎賞…",
            "§x§d§6§c§1§a§e(兩眼放光)",
            "§x§d§6§c§1§a§e要怎麼參加考驗?",
        ),
        *Utils.getMessage(kinIcon,
            "$kinNPC",
            "§x§8§B§6§7§3§A只需要進入迷宮，跟隨不同的信標顏色",
            "§x§8§B§6§7§3§A完成四位守護者的考驗，便會給予聖物",
            "§x§8§B§6§7§3§A取得全部聖物後，即可通向終點的白色信標",
        ),
        *Utils.getMessage(kinIcon,
            "$kinNPC",
            "§x§8§B§6§7§3§A在考驗途中，會有一些陷阱與怪物的阻撓",
            "§x§8§B§6§7§3§A請您務必小心",
            "§x§8§B§6§7§3§A當中會有無數怪物的阻攔",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這聽起來可不簡單啊…",
            "§x§d§6§c§1§a§e但為了獎賞…試一試也不差",
        ),
    )
    private val mes5 = arrayOf(
        *Utils.getMessage(shanIcon,
            "$shanNPC",
            "§x§8§B§6§7§3§A你就是閃說的那個傭兵啊！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e這樣就可以修補了嗎?",
        ),
        *Utils.getMessage(shanIcon,
            "$shanNPC",
            "§x§8§B§6§7§3§A很可惜，這是遠遠不夠的！",
            "§x§8§B§6§7§3§A裂縫還在不斷擴大",
            "§x§8§B§6§7§3§A需要更多的聖物",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e可是目前的聖物都給你了",
            "§x§d§6§c§1§a§e守護者們還有其他聖物嗎？",
            "§x§d§6§c§1§a§e或者其他守護者?",
        ),
        *Utils.getMessage(shanIcon,
            "$shanNPC",
            "§x§8§B§6§7§3§A守護者們每天都可以幻化一個聖物",
            "§x§8§B§6§7§3§A只要你堅持完成他們的試煉，給予他們艾靈之力",
            "§x§8§B§6§7§3§A他們便可以穩定地產出聖物給你",
        ),
        *Utils.getMessage(shanIcon,
            "$shanNPC",
            "§x§8§B§6§7§3§A當全服修復裂縫的次數到達指定數字",
            "§x§8§B§6§7§3§A便可觸發額外的獎賞",
            "§x§8§B§6§7§3§A一定要堅持每天完成呢！",
        ),
        *Utils.getMessage(playerIcon,
            "$playerName",
            "§x§d§6§c§1§a§e修裂縫還有獎勵啊！？",
            "§x§d§6§c§1§a§e我要守護艾靈長廊的和平！",
        ),
        *Utils.getMessage(shanIcon,
            "$shanNPC",
            "§x§8§B§6§7§3§A記得每天都要來幫忙喲",
            "§x§8§B§6§7§3§A不見不散",
        ),
    )

    init {
        val world1 = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",148,*mes1)
            .setInstruction("§7與 §e維洛妮亞 §7對話");
        val obj2loc = Location(world1,-333.0,70.0,346.0)
        val obj2 = MoveToObjective(this,"obj2",obj2loc,5.0)
            .setInstruction("§7前往艾靈裂縫");
        val obj3 = ListenTalkObjective(this,"obj3",*mes2)
        val obj4 = ListenNPCObjective(this,"obj4",188,*mes3)
            .setInstruction("§7與 §e金 §7對話")
            .setEndProcess {
                it.sendMessage("§7[§c提示§7] §f解鎖活動場地傳送權限")
                it.sendTitle("§7[§c提示§7]","§f你可以傳送至活動場地了")
                Utils.command("lp user ${it.name} permission set player.ailingtransport true")
            };

        val obj5 = ListenNPCObjective(this,"obj5",189,*mes4)
            .setInstruction("§7前往艾靈裂縫後與 §e金 §7對話")
        val obj6 = GiveMultiItemObjective(this,"obj6",197,
            mapOf(
                "QUESTMATERIAL:M018" to 1,
                "QUESTMATERIAL:M019" to 1,
                "QUESTMATERIAL:M020" to 1,
                "QUESTMATERIAL:M021" to 1,
            )).setInstruction{"§7將 §7${it.get("QUESTMATERIAL:M018")}/1 個燃的聖物、§7${it.get("QUESTMATERIAL:M019")}/1 個凜的聖物、§7${it.get("QUESTMATERIAL:M020")}/1 個闇的聖物、§7${it.get("QUESTMATERIAL:M021")}/1 個森的聖物 §7交給 §e閃§r"}
        val obj7 = ListenNPCObjective(this,"obj7",197,*mes5)
            .setInstruction("§7與 §e閃 §7對話")
        pushObjective(obj1,obj2,obj3,obj4,obj5,obj6,obj7)

        addRewards(
            MoneyReward(50), //30錢幣
            QuestExpReward(20), //15傭兵聲
            ClassExpReward(500),
            MessageReward("&a➯ &7造型: &7自然愛好者"),
            MessageReward("&a➯ &7道具: &b艾靈之箱鑰匙"),
        )
        addEndHook({
            Utils.command("get ${it.name} SKIN SKIN015")
            Utils.command("get ${it.name} CONSUMABLE C065")
            Utils.command("sq limit set ${it.name} 20")
        })
    }
}


val quest = Side_Activity_01();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



