@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.api.quests.Quest
import net.brian.scriptedquests.compability.classcore.ReachLevelObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMobsObjective
import net.brian.scriptedquests.compability.mythicmobs.KillMultiMobsObjective
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.objectives.*
import net.brian.scriptedquests.rewards.*
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player




val questID = "main_ch2_04"

class MainCh2_04: Quest(questID,"§7[§c主線委託§7] §f第二章-艾靈的危機"){

    val playerIcon = "%squests_icon%";
    val crayliaIcon = IconFonts.getNPC(105);
    val alielIcon = IconFonts.getNPC(123);
    val freerlIcon = IconFonts.getNPC(125);
    val foxtenlIcon = IconFonts.getNPC(126);


    private val playerName = "§c● §f%player_displayname%§r:";
    private val crayliaNPC = "§c● §e克雷利亞§r:";
    private val unknow = "§c● §e???§r:";
    private val alielNPC = "§c● §e艾黎爾§r:";
    private val freerlNPC = "§c● §e弗麗兒§r:";
    private val foxtenlNPC = "§c● §e弗斯頓§r:";




    private val mes1 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e克雷利亞，我回來了",
                    "§x§d§6§c§1§a§e至於毒藥…",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$crayliaNPC",
                    "&x&d&6&d&0&b&8好了我知道你要那瓶毒藥我這就…",
                    "&x&d&6&d&0&b&8阿!(跌倒)",
                    "&x&d&6&d&0&b&8糟糕…毒藥…開始擴散開了…",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$crayliaNPC",
                    "&x&d&6&d&0&b&8這樣下去諾亞村也會被侵蝕的",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那…那該怎麼辦",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$crayliaNPC",
                    "&x&d&6&d&0&b&8你得去艾靈長廊",
                    "&x&d&6&d&0&b&8我的毒藥，只有我的師父",
                    "&x&d&6&d&0&b&8艾黎爾才有辦法",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e糟糕…沒想到又是突發意外…",
                    "§x§d§6§c§1§a§e看來不得不去艾靈長廊了…",
            ),
            *Utils.getMessage(alielIcon,
                    "$unknow",
                    "&x&d&6&d&0&b&8諾亞島的西岸",
                    "&x&d&6&d&0&b&8我們…需要你…",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那聲音又出現了！",
                    "§x§d§6§c§1§a§e西岸…沿著外頭那條路就可以抵達了",
                    "§x§d§6§c§1§a§e還是趕快過去好了",
            ),
    )
    private val mes2 = arrayOf(
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8陌生人，我叫艾黎爾",
                    "&x&d&6&d&0&b&8先前是我呼喚你來的",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e妳在哪，妳就是克雷利亞的師傅吧",
                    "§x§d§6§c§1§a§e為甚麼我能一直聽見妳的聲音?",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8我…就在艾靈長廊的正中央",
                    "&x&d&6&d&0&b&8快來找我吧！",
            ),
    )
    private val mes3 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e為甚麼…這有這麼大個洞…",
                    "§x§d§6§c§1§a§e而且周遭怪物也好多…",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8這…說來話長",
                    "&x&d&6&d&0&b&8你應該有發現，進入艾靈長廊後的天空",
                    "&x&d&6&d&0&b&8變得非常詭異",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8那是這片土地的保護機制",
                    "&x&d&6&d&0&b&8她暫停了艾靈長廊的時間",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e這是甚麼意思…",
                    "§x§d§6§c§1§a§e我還沒理清頭緒阿",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8艾靈長廊…在某天被入侵了",
                    "&x&d&6&d&0&b&8大地出於本能凍結了所有人的時間",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8你得先去日月守護石像供俸各8株艾靈草",
                    "&x&d&6&d&0&b&8在此之後兩位艾靈長廊的守護會向你解釋",
            ),
    )
    private val mes4 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e放上祭壇…就好了吧",
            ),
            *Utils.getMessage(freerlIcon,
                    "$freerlNPC",
                    "&x&d&6&d&0&b&8(深呼吸) ",
                    "&x&d&6&d&0&b&8終於脫離了時間靜止的魔咒啦",
                    "&x&d&6&d&0&b&8嗯?是你做的嗎?",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e恩…算是的吧",
            ),
            *Utils.getMessage(freerlIcon,
                    "$freerlNPC",
                    "&x&d&6&d&0&b&8肯定是艾黎爾姐姐找你來的",
                    "&x&d&6&d&0&b&8你去過月之石像了嗎?",
                    "&x&d&6&d&0&b&8趕緊去解放月之石像",
            ),
            *Utils.getMessage(freerlIcon,
                    "$freerlNPC",
                    "&x&d&6&d&0&b&8只有解放日月石像，才有辦法拯救我們一族",
                    "&x&d&6&d&0&b&8也許我的弟弟也在那",
                    "&x&d&6&d&0&b&8你的疑問他會幫你解答你的",
            ),
    )
    private val mes5 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e這也有個人…",
                    "§x§d§6§c§1§a§e草就這樣放上去…",
                    "§x§d§6§c§1§a§e就行了吧",
            ),
            *Utils.getMessage(foxtenlIcon,
                    "$foxtenlNPC",
                    "&x&d&6&d&0&b&8(嘆氣)",
                    "&x&d&6&d&0&b&8終於脫離了那該死的魔咒了",
                    "&x&d&6&d&0&b&8嗯?是你小子做的嗎?",
            ),
            *Utils.getMessage(foxtenlIcon,
                    "$foxtenlNPC",
                    "&x&d&6&d&0&b&8肯定是艾黎爾那傢伙找你來的吧",
                    "&x&d&6&d&0&b&8對了，你去過日之石像了嗎?",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e我去過了，也見到了你的姐姐弗麗兒",
                    "§x§d§6§c§1§a§e是他讓我趕緊來找你的",
            ),
            *Utils.getMessage(foxtenlIcon,
                    "$foxtenlNPC",
                    "&x&d&6&d&0&b&8沒想到姐姐真的還守在那",
                    "&x&d&6&d&0&b&8艾黎爾那老太婆呢?她在哪?",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e她在中心的大洞裡",
                    "§x§d§6§c§1§a§e但不知道為甚麼",
                    "§x§d§6§c§1§a§e她貌似不受靜止魔咒的影響",
            ),
            *Utils.getMessage(foxtenlIcon,
                    "$foxtenlNPC",
                    "&x&d&6&d&0&b&8這個嘛…那傢伙早就沒有肉身了",
                    "&x&d&6&d&0&b&8所以她自然也能不受魔咒的影響",
                    "&x&d&6&d&0&b&8但她無法離開艾靈長廊的結界",
            ),
            *Utils.getMessage(foxtenlIcon,
                    "$foxtenlNPC",
                    "&x&d&6&d&0&b&8我們艾靈一族都有將意識具象化的力量",
                    "&x&d&6&d&0&b&8艾黎爾捨棄肉身等待著我們一族的救星",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那為甚麼你們會在這",
            ),
            *Utils.getMessage(foxtenlIcon,
                    "$foxtenlNPC",
                    "&x&d&6&d&0&b&8這就說來話長了",
                    "&x&d&6&d&0&b&8現在你得回去找那老太婆",
                    "&x&d&6&d&0&b&8她現在肯定更需要你",
            ),
    )
    private val mes6 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e艾黎爾，這究竟都發生了甚麼事",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8關於這點…",
                    "&x&d&6&d&0&b&8從前的艾靈長廊生長著無數的奇特植物",
                    "&x&d&6&d&0&b&8外頭的洞穴本是連結艾靈之境的地方",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8卻在某天遭人破壞",
                    "&x&d&6&d&0&b&8隨後大量的怪物便湧入",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e但這些事到底是怎麼發生的?",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8這…我也不清楚",
                    "&x&d&6&d&0&b&8弗斯頓有跟你說了吧",
                    "&x&d&6&d&0&b&8我無法離開艾靈長廊",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e看來只有我能去調查了",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8在此之前，還有個麻煩得解決",
                    "&x&d&6&d&0&b&8艾靈長廊的守衛巨龍",
                    "&x&d&6&d&0&b&8一旦地面的靈魂積累量過剩",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8牠便會甦醒，直到再次被人擊敗",
                    "&x&d&6&d&0&b&8正是因此，我才希望你能來拯救我們",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e我知道了，這就交給我吧!",
            ),
    )
    private val mes7 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e看來…是解決了",
                    "§x§d§6§c§1§a§e回去找艾黎爾吧",
            ),
    )
    private val mes8 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e我回來了",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8太好了，謝謝你拯救整個艾靈長廊",
                    "&x&d&6&d&0&b&8但是這些剩下的魔物…也許還很難清空呢",
                    "&x&d&6&d&0&b&8不過這些靠著我們一族的力量就沒問題了",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e我很好奇…為甚麼你們不解決巨龍呢?",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8這個嘛…我們其實無法對那頭龍造成傷害",
                    "&x&d&6&d&0&b&8放任不管的話，衝出結界可是很危險的",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e對了…我差點就忘了正事了",
                    "§x§d§6§c§1§a§e那個…克雷利亞…",
                    "§x§d§6§c§1§a§e意外打翻了她的特製毒藥",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8這個嘛…",
                    "&x&d&6&d&0&b&8那藥確實是我教她條配的",
                    "&x&d&6&d&0&b&8但…解藥十分複雜",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8我們做個交易吧",
                    "&x&d&6&d&0&b&8你代我去查清傳送門被破壞的事件",
                    "&x&d&6&d&0&b&8解藥就交給我處理了",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e這個嘛…那就這麼說定了!",
            ),
            *Utils.getMessage(alielIcon,
                    "$alielNPC",
                    "&x&d&6&d&0&b&8那就拜託你了!",
            ),
    )
    init {
        cancelAble = false
        //val obj0 = ReachLevelObjective(this,"obj0", 999)
        //.setInstruction("§c該任務尚未開放，開放後將自動解鎖");
        val obj0 = ReachLevelObjective("obj0", 18)
                .setInstruction("§718等後將繼續任務");
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",100,*mes1)
                .setInstruction("§7與 &e克雷利亞 §7對話");
        val obj2 = MoveToObjective(this,"obj2",Location(world,-679.22, 85.00, 416.75),5.0)
                .setInstruction("§7前往艾靈長廊");
        val obj3 = ListenTalkObjective(this,"obj3",*mes2)
                .setInstruction("§7查看一下周遭");
        val obj4 = ListenNPCObjective(this,"obj4",123,*mes3)
                .setInstruction("§7與 §e艾黎爾 §7對話");
        val obj5 =GiveItemObjective(this,"obj5",125,"MATERIAL:M009",8)
                .setInstruction{"交給 §e弗麗兒 §7${it.amount}/8 個艾靈草 &8(艾靈長廊怪物掉落)"}
        val obj6 = ListenTalkObjective(this,"obj6",*mes4)
                .setInstruction("§7查看一下周遭");
        val obj7 = GiveItemObjective(this,"obj7",126,"MATERIAL:M009",8)
                .setInstruction{"交給 §e弗斯頓 §7${it.amount}/8 個艾靈草 &8(艾靈長廊怪物掉落)"}
        val obj8 = ListenTalkObjective(this,"obj8",*mes5)
                .setInstruction("§7查看一下周遭");
        val obj9 = ListenNPCObjective(this,"obj9",123,*mes6)
                .setInstruction("§7與 §e艾黎爾 §7對話");
        val obj10 = KillMobsObjective(this,"obj10","艾靈長廊_艾靈守衛巨龍",1)
                .setInstruction { "擊殺 ${it.amount} / 1 個艾靈守衛巨龍" };
        val obj11 = ListenTalkObjective(this,"obj11",*mes7)
                .setInstruction("§7查看一下周遭");
        val obj12 = ListenNPCObjective(this,"obj12",123,*mes8)
                .setInstruction("§7與 §e艾黎爾 §7對話");

        pushObjective(obj0,obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10,obj11,obj12)

        addRewards(
                MoneyReward(200), //錢幣
                QuestExpReward(50), //傭兵聲望
                ClassExpReward(500),
                MessageReward("&a➯ &7稱號: §7[§f\uE1A7§x§e§4§d§7§d§1艾靈的統治者§f\uE1A7§7]"),
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch3_01")
            Utils.command("lp user ${it.name} permission set ps.suffix.32")
        } )

    }
}


val quest = MainCh2_04();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



