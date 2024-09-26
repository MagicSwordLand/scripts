@file:RequiredPlugins("ScriptedQuests","PlayerDataSync","MMOItems","MythicLib","ItemLeveling")

import com.google.gson.JsonParser
import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import io.lumine.mythic.lib.api.item.NBTItem
import net.Indyuce.mmoitems.ItemStats
import net.Indyuce.mmoitems.MMOItems
import net.Indyuce.mmoitems.api.item.mmoitem.LiveMMOItem
import net.Indyuce.mmoitems.stat.data.SoulboundData
import net.brian.itemleveling.ItemLeveling
import net.brian.itemleveling.configs.ItemConfig
import net.brian.itemleveling.stat.LevelStat
import net.brian.itemleveling.stat.PRStat
import net.brian.scriptedquests.ScriptedQuests
import net.brian.scriptedquests.conversation.IconFonts
import net.brian.scriptedquests.conversation.NPCQuestion
import net.brian.scriptedquests.conversation.PlayerOption
import net.brian.scriptedquests.utils.Utils
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack

val npcName = "§b● §e魯福§r"
val npcIcon = IconFonts.getNPC(178);
val side01 = "side_gold_02"






val map = mutableMapOf(
        "MAGICWIND:W004" to "SWORD:S004",
        "MAGICWIND:W005" to "SWORD:S005",
        "MAGICWIND:W006" to "SWORD:S006",
        "MAGICWIND:W007" to "SWORD:S007",
        "MAGICWIND:W008" to "SWORD:S008",
        "MAGICWIND:W009" to "SWORD:S009",
        "SWORD:S004" to "MAGICWIND:W004",
        "SWORD:S005" to "MAGICWIND:W005",
        "SWORD:S006" to "MAGICWIND:W006",
        "SWORD:S007" to "MAGICWIND:W007",
        "SWORD:S008" to "MAGICWIND:W008",
        "SWORD:S009" to "MAGICWIND:W009",
        "SPWEAPON:S002" to "SPWEAPON:S001",
        "SPWEAPON:S001" to "SPWEAPON:S002"
)

fun exchangeItem(itemStack: ItemStack): ItemStack?{
    val nbtItem = NBTItem.get(itemStack)
    val identifier = nbtItem.type+":"+nbtItem.getString("MMOITEMS_ITEM_ID");
    if(!map.containsKey(identifier)) return null;
    val newItemString = map[identifier]!!;
    val args = newItemString.split(":");
    val type = args[0];
    val id = args[1];
    val item = MMOItems.plugin.getItem(type,id) ?: return null;
    val liveMMOItem = LiveMMOItem(item);
    val oldLiveItem = LiveMMOItem(itemStack);

    val soulbound = nbtItem.getString(ItemStats.SOULBOUND.nbtPath)
    if (soulbound != null && soulbound != "") {
        liveMMOItem.setData(ItemStats.SOULBOUND, SoulboundData(JsonParser().parse(soulbound).asJsonObject))
    }
    if (oldLiveItem.hasData(LevelStat.inst())) liveMMOItem.setData(
            LevelStat.inst(),
            oldLiveItem.getData(LevelStat.inst())
    )
    if (oldLiveItem.hasData(PRStat.inst())) liveMMOItem.setData(PRStat.inst(), oldLiveItem.getData(PRStat.inst()))
    if (oldLiveItem.hasData(ItemStats.TIER)) liveMMOItem.setData(ItemStats.TIER, oldLiveItem.getData(ItemStats.TIER))

    ItemLeveling.getInstance().itemService.getConfig(type, id).ifPresent { itemConfig: ItemConfig ->
        val tierS = nbtItem.getString("MMOITEMS_TIER")
        val tier = if (tierS == "") 1 else tierS.toInt()
        val level = LevelStat.read(itemStack)
        itemConfig.getStatInfo(tier).set(liveMMOItem,level);
    }
    return liveMMOItem.newBuilder().build();
}





val question1 = NPCQuestion(*Utils.getMessage(
        npcIcon,
        "${npcName}:",
        "§7(敲打敲打)",
        "§7恩?有客人阿",
        "§7需要什麼嗎?",
)).addPlayerOptions(
        PlayerOption("&7[&c轉換繼承&7] &f我想互換和繼承同類型的法杖或劍")
                .setNpcResponse(NPCQuestion("請把要兌換的武器放在主手上")
                        .addPlayerOptions(PlayerOption(
                                "[我已經拆除武器上的寶石，並確認定要轉換]"
                        ).setResult
                        { player ->
                            val item = player.inventory.itemInMainHand;
                            val newItem = exchangeItem(item);
                            if(newItem != null){
                                player.inventory.setItemInMainHand(newItem);
                                player.sendMessage("更新成功")
                            }
                            else player.sendMessage("該物品不能更新")
                        })),

        PlayerOption("&7[&b商店交易&7] &f我想買點東西")
                .setResult { player ->
                    if (isDay(player)||player.hasPermission("npc.ignoretime"))
                        command("shop open 魯福的鐵匠鋪 ${player.name}")
                    else{
                        player.sendMessage(*Utils.getMessage(
                                npcIcon,
                                "${npcName}:",
                                "§7抱歉喔，已經打烊了",
                                "§7敲了一整天的鐵",
                                "§7我需要休息一下...",
                        ))
                    }},

        PlayerOption("&7[&b武器強化&7] &f我想强化武裝")
                .setResult { player ->
                    if (isDay(player)||player.hasPermission("npc.ignoretime"))
                        command("crafting upgrade ${player.name}")
                    else{
                        player.sendMessage(*Utils.getMessage(
                                npcIcon,
                                "${npcName}:",
                                "§7抱歉喔，已經打烊了",
                                "§7敲了一整天的鐵",
                                "§7我需要休息一下...",
                        ))
                    }},

        PlayerOption("&7[&b武器洗鍊&7] &f我想洗煉武裝")
                .setResult { player ->
                    if (isDay(player)||player.hasPermission("npc.ignoretime"))
                        command("crafting reroll ${player.name}")
                    else{
                        player.sendMessage(*Utils.getMessage(
                                npcIcon,
                                "${npcName}:",
                                "§7抱歉喔，已經打烊了",
                                "§7敲了一整天的鐵",
                                "§7我需要休息一下...",
                        ))
                    }},

        PlayerOption("&7[&b製造選項&7] &f能幫我製造一些東西嗎?").setNpcResponse(
                NPCQuestion(*Utils.getMessage(
                        npcIcon,
                        "${npcName}:",
                        "§7交給我了！",
                        "§7讓我打造一把傳奇武器吧！",
                        "§7要製作甚麼呢！",
                )).addPlayerOptions(
                        PlayerOption("&7[&b製造物品&7] &f物品製造")
                                .setResult { player -> command("crafting station ${player.name} 魯福製作物品")},

                        PlayerOption("&7[&b製造武器&7] &f武器鍛造")
                                .setResult { player -> command("crafting station ${player.name} 魯福鍛造武器")},

                        PlayerOption("&7[&b製造防具&7] &f防具製作")
                                .setResult { player -> command("crafting station ${player.name} 魯福鍛造防具")}
                )),
        //.addCondition{it.hasPermission("")}
        PlayerOption("&7[&b修補物品&7] &f我有一些想要修補的裝備")
                //.addCondition{it.hasPermission("")}
                .setResult { player ->
                    if (isDay(player)||player.hasPermission("npc.ignoretime"))
                        command("crafting repair ${player.name}");
                    else{
                        player.sendMessage(*Utils.getMessage(
                                npcIcon,
                                "${npcName}:",
                                "§7抱歉喔，已經打烊了",
                                "§7敲了一整天的鐵",
                                "§7我需要休息一下...",
                        ))
                    }},

        PlayerOption("&7[&a對話選項&7] &f沒事了")
                //.addCondition{it.hasPermission("")}
                .setResult { player ->
                    if (isDay(player)||player.hasPermission("npc.ignoretime"))
                        player.sendMessage(*Utils.getMessage(
                                npcIcon,
                                "${npcName}:",
                                "§7沒事我就繼續忙了",
                        ))
                    else {
                        player.sendMessage(*Utils.getMessage(
                                npcIcon,
                                "${npcName}:",
                                "§7那我先去休息了..",
                        ))
                    }
                },

        PlayerOption("§7[§b金牌支線§7] §f煤炭包的防潮運輸 &8(階級需求:黃金)")
                .addConditions( {!it.hasPermission("rank.lv3")} )
                .setResult { player -> player.sendMessage("§7[§c提示§7] §f未滿足階級需求")})


ScriptedQuests.getInstance().questManager.getQuest(side01).ifPresent {
    question1.addPlayerOptions(it.getStartOption("§7[§b金牌支線§7] §f煤炭包的防潮運輸",false)
            .addConditions( {player->player.hasPermission("rank.lv3")} )
    )}


fun isDay(player: Player): Boolean{
    return player.getWorld().getTime() < 16000 || player.getWorld().getTime() > 23000;
}

fun command(commandString: String){
    Bukkit.dispatchCommand(Bukkit.getConsoleSender(),commandString)
}

ScriptedQuests.getInstance().conversationManager.pushNPCQuestion(178,question1)