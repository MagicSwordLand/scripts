@file:RequiredPlugins("HeroesPets","PlayerDataSync","HeroesSkills","HeroesPetUpgrading")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroespet.upgrading.models.UpgradeablePet
import net.brian.heroespet.upgrading.utils.PetExpGlobalWhiteList
import net.brian.heroespets.HeroesPets
import net.brian.heroespets.api.pets.HeroesPet
import net.brian.heroespets.api.pets.Pet
import net.brian.heroespets.api.pets.SpawnedPet
import net.brian.heroespets.bukkit.compatible.mythiclib.MythicStats
import net.brian.heroespets.bukkit.configs.configs.Language
import net.brian.heroespets.utils.SkullBuilder
import net.brian.playerdatasync.util.IridiumColorAPI
import net.kyori.adventure.title.Title
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class ElinDragon: HeroesPet("elin-dragon","耶夢加得"), UpgradeablePet {


    init {
        display = "耶夢加得"
        initTier = 2
    }

    override fun getEntityName(player: Player, pet: Pet): MutableList<String> {
        return arrayListOf(
            "§f${player.name} 的寵物",
            "§7Lv.${pet.level} 耶夢加得"
        )
    }

    override fun getIcon(player: Player?, pet: Pet): ItemStack {
        val itemStack = SkullBuilder.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTRjNjgzNGM4YmYwYjgxZjkyY2YwZGE0YmQ5OWFkNzY0MTU0ZDUzNjhhMzhlMDNjODVhZGRhMTBlNTE4ODkwYSJ9fX0=")
        val itemMeta = itemStack.itemMeta;
        itemMeta.setDisplayName("§f耶夢加得 §7Lv.${pet.level}")
        val lore = buildList {
            add("")
            if(player != null) add("§7▪ 主人: &f${player.name}")
            add("§7▪ 品階: &b稀有")
            if(pet.nextLevelExp == -1){
                add(Language.MAX_LEVEL);
            }
            else add("§7▪ 經驗: &f${pet.exp}/${pet.nextLevelExp}")
            add("")
            add("§7§l傭兵公會特別培育的幼龍寵物")
            add("§7§l能夠使主人更快速的成長")
            add("§7§l但因為難以培育導致數量稀少")
            add("§7§l因此只提供給對公會有重大貢獻之人")
            add("")
            add("&7➤ + ${expBuff(pet)}% 玩家經驗獲取")
            if(pet.level>=10){
                add("&7➤ &7等級達10等時每30秒獲得 加速I 5秒")}
            else{
                add("&7➤ &8等級達10等時每30秒獲得 加速I 5秒")
            }
            if(pet.level>=20){
                add("&7➤ &7等級達20等時每30秒獲得 加速I 10秒")}
            else{
                add("&7➤ &8等級達20等時每30秒獲得 加速I 10秒")
            }
            if(pet.level>=30){
                add("&7➤ &7等級達30等時每30秒獲得 加速II 10秒")}
            else{
                add("&7➤ &8等級達30等時每30秒獲得 加速II 10秒")
            }
        }


        itemMeta.lore = IridiumColorAPI.process(lore);

        itemStack.itemMeta = itemMeta;
        return itemStack;
    }

    private fun expBuff(pet: Pet): Double{
        return Math.floor(pet.level/1.5);
    }
    override fun getWhiteLists(): MutableList<String> {
        val list = mutableListOf<String>();
        list.addAll(PetExpGlobalWhiteList.get());//全局通用果实，如果不要通用吃，就删掉这行
        return  list;
    }
    override fun onTick(pet: SpawnedPet, ticks: Long) {
        // 每個 Tick 要做的事
        if(ticks % (30*20) == 0L){

            if (pet.pet.level>=10&&pet.pet.level<20) {
                pet.owner.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 100, 0))
                pet.owner.sendMessage("§7[§x§B§E§9§B§7§B寵物§7] §f耶夢加得施展能力為主人加速")
            }
            if (pet.pet.level>=20&&pet.pet.level<30) {
                pet.owner.addPotionEffect(PotionEffect(PotionEffectType.SPEED, 200, 0))
                pet.owner.sendMessage("§7[§x§B§E§9§B§7§B寵物§7] §f耶夢加得施展能力為主人加速")
            }
            if (pet.pet.level>=30){
                pet.owner.addPotionEffect(PotionEffect(PotionEffectType.SPEED,200,1))
                pet.owner.sendMessage("§7[§x§B§E§9§B§7§B寵物§7] §f耶夢加得施展能力為主人加速")
            }
        }
    }

    val modifierKey = "heroes-pet";
    override fun onSpawn(spawnedPet: SpawnedPet) {
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"ADD_CLASS_EXP",expBuff(spawnedPet.pet),modifierKey)
    }

    override fun onDespawn(spawnedPet: SpawnedPet) {
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"ADD_CLASS_EXP",modifierKey);
    }


}

HeroesPets.getPlugin().petManager.register(ElinDragon());