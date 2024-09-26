@file:RequiredPlugins("HeroesPets","PlayerDataSync","HeroesSkills","HeroesPetUpgrading")

import io.github.clayclaw.lightcargo.kts.environment.bukkit.annotation.RequiredPlugins
import net.brian.heroespet.upgrading.models.UpgradeablePet
import net.brian.heroespet.upgrading.utils.PetExpGlobalWhiteList
import net.brian.heroespets.HeroesPets
import net.brian.heroespets.api.pets.HeroesPet
import net.brian.heroespets.api.pets.Pet
import net.brian.heroespets.api.pets.SpawnedPet
import net.brian.heroespets.bukkit.compatible.mythiclib.MythicStats
import net.brian.heroespets.bukkit.compatible.mythicmobs.MythicPetSkill
import net.brian.heroespets.bukkit.configs.configs.Language
import net.brian.heroespets.core.pets.skills.SudoOwnerSkill
import net.brian.heroespets.utils.SkullBuilder
import net.brian.playerdatasync.util.IridiumColorAPI
import org.bukkit.entity.Player
import org.bukkit.inventory.ItemStack
import org.bukkit.potion.PotionEffect
import org.bukkit.potion.PotionEffectType

class Baby_Shark: HeroesPet("babyshark","兩棲鯊魚"),UpgradeablePet {


    init {
        display = "兩棲鯊魚"
        initTier = 2
        skill = SudoOwnerSkill("兩棲鯊魚_威嚇",40000L);
    }

    override fun getEntityName(player: Player, pet: Pet): MutableList<String> {
        return arrayListOf(
            "§f${player.name} 的寵物",
            "§7Lv.${pet.level} 兩棲鯊魚"

        )
    }

    override fun getIcon(player: Player?, pet: Pet): ItemStack {
        val itemStack = SkullBuilder.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjEyNGM1NDFkZWRhNGIyNjZmZjE0ZTYxYjBhMTMyNDRiMmQ0YjdjMThkMmI2YzhkZDIxNzZkNWQzNjVmYmQifX19")
        val itemMeta = itemStack.itemMeta;
        itemMeta.setDisplayName("§f兩棲鯊魚 §7Lv.${pet.level}")
        val lore = buildList {
            add("")
            if(player != null) add("§7▪ 主人: &f${player.name}")
            add("§7▪ 品階: &b稀有")
            if(pet.nextLevelExp == -1){
                add(Language.MAX_LEVEL);
            }
            else add("§7▪ 經驗: &f${pet.exp}/${pet.nextLevelExp}")
            add("")
            add("§7§l雖然沒有腳，但卻能在陸地上移動的鯊魚")
            add("§7§l有著非比尋常的生命力")
            add("§7§l且能對周遭的敵人造成威嚇")
            add("")
            add("&f&l威嚇 &7| &fCD: 40秒")
            add("&7使主人周遭8格內的敵人")
            add("&7獲得 緩速IV 5秒")
            add("")
            add("&7➤ + ${expBuff(pet)} 最大血量")

        }

        itemMeta.lore = IridiumColorAPI.process(lore);

        itemStack.itemMeta = itemMeta;
        return itemStack;
    }

    private fun expBuff(pet: Pet): Double {
        return (pet.level/2.0)*3.0;
    }

    override fun onTick(pet: SpawnedPet, ticks: Long) {
        // 每個 Tick 要做的事
        /*if(ticks % (60*20) == 0L){
            pet.owner.addPotionEffect(PotionEffect(PotionEffectType.SPEED,400,2));
        }*/
    }
    override fun getWhiteLists(): MutableList<String> {
        val list = mutableListOf<String>();
        list.addAll(PetExpGlobalWhiteList.get());//全局通用果实，如果不要通用吃，就删掉这行
        list.add("GLOW_BERRIES");
        return  list;
    }
    val modifierKey = "heroes-pet";
    override fun onSpawn(spawnedPet: SpawnedPet) {
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"MAX_HEALTH",expBuff(spawnedPet.pet),modifierKey)
    }

    override fun onDespawn(spawnedPet: SpawnedPet) {
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"MAX_HEALTH",modifierKey);
    }


}

HeroesPets.getPlugin().petManager.register(Baby_Shark());