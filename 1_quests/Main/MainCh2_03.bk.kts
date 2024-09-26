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




val questID = "main_ch2_03"

class MainCh2_03: Quest(questID,"§7[§c主線委託§7] §f第二章-亡靈村"){


    val playerIcon = "%squests_icon%";
    val crayliaIcon = IconFonts.getNPC(105);
    val craymiaIcon = IconFonts.getNPC(105);
    val jeebooIcon = IconFonts.getNPC(121);
    val silkIcon = IconFonts.getNPC(120);
    val mataliIcon = IconFonts.getNPC(119);
    val unknowIcon = IconFonts.getNPC(123);

    private val playerName = "§c● §f%player_displayname%§r:";
    private val crayliaNPC = "§c● §e克雷利亞§r:";
    private val craymiaNPC = "§c● §e克雷米亞§r:";
    private val jeebooNPC = "§c● §e吉布§r:";
    private val commustonNPC = "§c● §e克雷利亞(通訊石)§r:";
    private val unknow = "§c● §e???§r:";
    private val silkNPC = "§c● §e希爾科§r:";
    private val mataliNPC = "§c● §e瑪塔莉§r:";



    private val mes1 = arrayOf(
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8噓…安靜",
                    "&x&d&6&d&0&b&8他們都在森林裡聽著呢",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e他們?",
                    "§x§d§6§c§1§a§e這除了我們也沒有別人吧？",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8是亡魂們",
                    "&x&d&6&d&0&b&8他們都是在諾亞島上流離失所的亡魂",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那個…先不管這個了",
                    "§x§d§6§c§1§a§e這是巨石核素",
                    "§x§d§6§c§1§a§e你的姊姊託我給你的",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e這是拿來做些甚麼的?",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8這個嘛…",
                    "&x&d&6&d&0&b&8老實說，就在旁邊有著一座村莊",
                    "&x&d&6&d&0&b&8然而除了守墓人，其餘村民早已離世",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8而我依靠著我的力量",
                    "&x&d&6&d&0&b&8維持著結界不讓低語之森的亡魂滲透",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e所以這顆巨石核素就是用來...",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8是的，那個…",
                    "&x&d&6&d&0&b&8我能麻煩你一件事嗎?",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e阿...",
                    "§x§d§6§c§1§a§e可是...",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8拜託了！",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e好吧...",
                    "§x§d§6§c§1§a§e什麼事情?",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8我想請你替守墓人完成村里亡魂的遺願",
                    "&x&d&6&d&0&b&8村里的守墓人自從妻子去世後",
                    "&x&d&6&d&0&b&8便再無心打理墓園",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8甚至仍有尚未瞑目的村民們還在村裡遊蕩呢",
                    "&x&d&6&d&0&b&8如果一直依靠核心的力量…或許撐不了太久…",
                    "&x&d&6&d&0&b&8你可以去找守墓人，他會告訴你要做什麼！",
            ),
    )
    private val mes2 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那個…",
                    "§x§d§6§c§1§a§e請問您就是吉布先生對吧",
            ),
            *Utils.getMessage(jeebooIcon,
                    "$jeebooNPC",
                    "&x&d&6&d&0&b&8走開，別來煩我…",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e這...好吧...",
            ),
            *Utils.getMessage(jeebooIcon,
                    "$jeebooNPC",
                    "&x&d&6&d&0&b&8他獨自住在低語森林外，救贖那些迷失的亡魂",
                    "&x&d&6&d&0&b&8你見到她之後，她會再交代你剩下的事",
            ),
    )

    private val mes3 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e這就是工作內容了吧…",
                    "§x§d§6§c§1§a§e看起來並不多呀",
                    "§x§d§6§c§1§a§e不過都要完成他們的遺願…",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e希望不會太難",
                    "§x§d§6§c§1§a§e先去找找第一個目標「希爾珂」",
                    "§x§d§6§c§1§a§e他就住在...",
            ),
    )
    private val mes4 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那個…",
                    "§x§d§6§c§1§a§e希爾珂先生",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8你能不能安靜點",
                    "&x&d&6&d&0&b&8我都在這幾十年了",
                    "&x&d&6&d&0&b&8沒有一天能過得清靜",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8要不是因為那場船難",
                    "&x&d&6&d&0&b&8我也不會落得現在的下場…",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e有甚麼我能替你完成的嗎?",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8你先去把外面森林的噪音給我清理乾淨",
                    "&x&d&6&d&0&b&8在這之前…",
                    "&x&d&6&d&0&b&8拜託你不要煩我了",
            )
    )
    private val mes5 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e好奇怪…",
                    "§x§d§6§c§1§a§e為甚麼怎麼打都打不完",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$commustonNPC",
                    "&x&d&6&d&0&b&8低語沉寂，樹蔭籌謀",
                    "&x&d&6&d&0&b&8這座森林有著很棘手的死靈衛士",
                    "&x&d&6&d&0&b&8若不擊敗他，死靈只會越來越多",
            )
    )
    private val mes6 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e我的天…這森林小小的",
                    "§x§d§6§c§1§a§e沒料到怪物能那麼多",
                    "§x§d§6§c§1§a§e先回去看看希爾珂先生還有甚麼遺願吧",
            )
    )
    private val mes7 = arrayOf(
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8安靜多了",
                    "&x&d&6&d&0&b&8新來的守墓人終於做事了",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那個…",
                    "§x§d§6§c§1§a§e其實我是受人之託",
                    "§x§d§6§c§1§a§e並不是新的守墓人",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8阿，那都一樣啦",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那希爾珂先生",
                    "§x§d§6§c§1§a§e您還有甚麼願望呢?",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8我想…",
                    "&x&d&6&d&0&b&8希望吉布能回歸正常",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e回歸正常…? ",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8是的，不瞞你說",
                    "&x&d&6&d&0&b&8他曾是我的戰友",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8卻因為受血源的束縛",
                    "&x&d&6&d&0&b&8回到村子做了守墓人",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那就交給我吧",
                    "§x§d§6§c§1§a§e我會想辦法的!",
                    "§x§d§6§c§1§a§e不過等我先把這個工作都做完吧",
            ),
    )
    private val mes8 = arrayOf(
            *Utils.getMessage(mataliIcon,
                    "$mataliNPC",
                    "&x&d&6&d&0&b&8你是誰?",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e我是受人之託來幫助你完成願望的",
            ),
            *Utils.getMessage(mataliIcon,
                    "$mataliNPC",
                    "&x&d&6&d&0&b&8願望…",
                    "&x&d&6&d&0&b&8我想不起來，我是誰",
                    "&x&d&6&d&0&b&8我也想不起來，我為甚麼變成了亡魂",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e瑪塔莉小姐，別著急",
                    "§x§d§6§c§1§a§e我能幫助你的",
            ),
            *Utils.getMessage(mataliIcon,
                    "$mataliNPC",
                    "&x&d&6&d&0&b&8我叫…瑪塔莉?",
                    "&x&d&6&d&0&b&8對!我叫瑪塔莉!",
                    "&x&d&6&d&0&b&8我有個女兒，她叫…她是誰…",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e你的女兒…",
                    "§x§d§6§c§1§a§e說不定希爾珂知道些甚麼",
            ),
    )
    private val mes9 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e希爾珂先生",
                    "§x§d§6§c§1§a§e我想請問你對瑪塔莉小姐知道多少？",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8瑪塔莉…這個嘛…",
                    "&x&d&6&d&0&b&8她曾是村子最美的姑娘",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8某天一位希爾王城的將軍經過看上了她",
                    "&x&d&6&d&0&b&8沒過多久瑪塔莉便懷上了一對雙胞胎",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e後來呢?怎麼不見那對雙胞胎的蹤影了",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8這個嘛…",
                    "&x&d&6&d&0&b&8她們被認為是不潔的象徵",
                    "&x&d&6&d&0&b&8瑪塔莉在生下孩子後便被處死",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8那兩個孩子…",
                    "&x&d&6&d&0&b&8其實就是克雷姊妹",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那對巫女姊妹…",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8是的，但她們卻從不知情",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e那…也許該讓她們見見母親了",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8真的…要嗎?",
                    "&x&d&6&d&0&b&8當初是我們丟棄了她們",
                    "&x&d&6&d&0&b&8現在卻由她們守護著村子",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e也許她們從沒有怨恨過你們",
                    "§x§d§6§c§1§a§e也許她們也一直都知道",
                    "§x§d§6§c§1§a§e所以才願意不惜一切守護村子",
            ),
            *Utils.getMessage(silkIcon,
                    "$silkNPC",
                    "&x&d&6&d&0&b&8這麼說…",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e放心吧，我能處理好的",
            ),
    )
    private val mes10 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e米亞，有些事想跟你說",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8你要告訴我最後一位死者是我的母親對吧",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e你果然都知道了",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8要不然，我怎麼會甘願在這鬼地方生根呢",
                    "&x&d&6&d&0&b&8拜託你也是因為…",
                    "&x&d&6&d&0&b&8不敢回去見母親…",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8當初因為我們的誕生",
                    "&x&d&6&d&0&b&8導致一切悲劇的起源",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e不需要自責，妳們的母親",
                    "§x§d§6§c§1§a§e現在依舊非常想妳們",
                    "§x§d§6§c§1§a§e不願離開也是因為無法放下對妳們的思念",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8母親她…真的這麼說嗎?",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$commustonNPC",
                    "&x&d&6&d&0&b&8母親她真的很想我們",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8姊姊…",
            ),
            *Utils.getMessage(crayliaIcon,
                    "$commustonNPC",
                    "&x&d&6&d&0&b&8小子，你去找我們母親吧",
                    "&x&d&6&d&0&b&8順帶將這顆通訊石",
                    "&x&d&6&d&0&b&8交給我們母親",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e這樣就行了嗎?",
            ),
            *Utils.getMessage(craymiaIcon,
                    "$craymiaNPC",
                    "&x&d&6&d&0&b&8快去吧",
            )
    )
    private val mes11 = arrayOf(
            *Utils.getMessage(crayliaIcon,
                    "$commustonNPC",
                    "&x&d&6&d&0&b&8媽媽，我是克雷利亞",
                    "&x&d&6&d&0&b&8記得我嗎?",
            ),
            *Utils.getMessage(mataliIcon,
                    "$mataliNPC",
                    "&x&d&6&d&0&b&8這是…",
                    "&x&d&6&d&0&b&8媽媽對不起妳們…",
                    "&x&d&6&d&0&b&8當初是媽媽拋棄了妳們",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e妳的女兒其實一直都知道",
                    "§x§d§6§c§1§a§e所以米亞也才會一直守護著村莊",
            ),
            *Utils.getMessage(mataliIcon,
                    "$mataliNPC",
                    "&x&d&6&d&0&b&8原來是這樣…謝謝你",
                    "&x&d&6&d&0&b&8幫助我們團圓了",
            ),
    )
    private val mes12 = arrayOf(
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e接下來回去找克雷利亞拿毒藥應該就行了",
            ),
            *Utils.getMessage(unknowIcon,
                    "$unknow",
                    "&x&d&6&d&0&b&8快…快救救我…",
                    "&x&d&6&d&0&b&8我在…艾靈長廊…",
            ),
            *Utils.getMessage(playerIcon,
                    "$playerName",
                    "§x§d§6§c§1§a§e是誰!",
                    "§x§d§6§c§1§a§e聲音不見了…",
                    "§x§d§6§c§1§a§e不管了，先去找克雷利亞吧",
            )
    )
    init {
        cancelAble = false
        //val obj0 = ReachLevelObjective(this,"obj0", 999)
        //.setInstruction("§c該任務尚未開放，開放後將自動解鎖");
        val obj0 = ReachLevelObjective("obj0", 15)
                .setInstruction("§715等後將繼續任務");
        val world = Bukkit.getWorld("2k");
        val obj1 = ListenNPCObjective(this,"obj1",105,*mes1)
                .setInstruction("§7與 §e克雷米亞 §7對話");
        val obj2 = ListenNPCObjective(this,"obj2",122,*mes2)
                .setInstruction("§7與 §e吉布 §7對話");
        val obj3 = MoveToObjective(this,"obj3",Location(world,-466.51, 74.00, 204.48),4.0)
                .setInstruction("§7找尋 守墓工作清單(任務道具) &8(前往指定地點)")
                .setEndProcess {
                    it.sendTitle("§7[§a獲得物品§7]","§f守墓工作清單")
                    Utils.command("get ${it.name} QUESTMATERIAL QM013")};
        val obj4 = ListenTalkObjective(this,"obj4",*mes3)
                .setInstruction("§7查看一下工作清單");
        val obj5 = ListenNPCObjective(this,"obj5",120,*mes4)
                .setInstruction("§7與 §e希爾科 §7對話");
        val obj6 = KillMobsObjective(this,"obj6","低語森林_",20)
                .setInstruction { "擊殺 ${it.amount} / 20 個低語森林的怪物" };
        val obj7 = ListenTalkObjective(this,"obj7",*mes5)
                .setInstruction("§7查看一下周遭");
        val obj8 = KillMobsObjective(this,"obj8","低語森林_死靈衛士",1)
                .setInstruction { "擊殺 ${it.amount} / 1 個死靈衛士" };
        val obj9 = ListenTalkObjective(this,"obj9",*mes6)
                .setInstruction("§7搜索一下周遭");
        val obj10 = ListenNPCObjective(this,"obj10",120,*mes7)
                .setInstruction("§7與 §e希爾科 §7對話");
        val obj11 = ListenNPCObjective(this,"obj11",118,*mes8)
                .setInstruction("§7與 §e瑪塔莉 §7對話");
        val obj12 = ListenNPCObjective(this,"obj12",120,*mes9)
                .setInstruction("§7與 §e希爾科 §7對話");
        val obj13 = ListenNPCObjective(this,"obj13",105,*mes10)
                .setInstruction("§7與 §e克雷米亞 §7對話");
        val obj14 = ListenNPCObjective(this,"obj14",118,*mes11)
                .setInstruction("§7與 §e瑪塔莉 §7對話");
        val obj15 = MoveToObjective(this,"obj15",Location(world,-477.39, 73.00, 194.54),3.0)
                .setInstruction("§7離開瑪塔利的家")
        val obj16 = ListenTalkObjective(this,"obj16",*mes12)
                .setInstruction("§7耳邊突然傳說神祕的聲音");

        pushObjective(obj0,obj1,obj2,obj3,obj4,obj5,obj6,obj7,obj8,obj9,obj10,obj11,obj12,obj13,obj14,obj15,obj16)

        addRewards(
                MoneyReward(50), //30錢幣
                QuestExpReward(35), //15傭兵聲望
                ClassExpReward(200),
                MessageReward("&a➯ &7稱號: §7[§f\uE20B§x§e§4§d§7§d§1低語村協助人§f\uE20B§7]"),
        )
        addEndHook({
            Utils.command("sq start ${it.name} main_ch2_04")
            Utils.command("lp user ${it.name} permission set ps.suffix.24")
        } )

    }
}


val quest = MainCh2_03();
ScriptedQuests.getInstance().questManager.register(quest);

fun onDispose(){
    quest.unregister();
}



