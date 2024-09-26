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

class goblinshaman: HeroesPet("goblinshaman","哥布林法師"), UpgradeablePet {


    init {
        display = "菁英哥布林法師"
        initTier = 2
        height = 2.6
        skill = SudoOwnerSkill("哥布林法師_哥布林治癒",80000L);
    }

    override fun getEntityName(player: Player, pet: Pet): MutableList<String> {
        return arrayListOf(
            "§f${player.name} 的寵物",
            "§7Lv.${pet.level} 菁英哥布林法師"

        )
    }

    override fun getIcon(player: Player?, pet: Pet): ItemStack {
        val itemStack = SkullBuilder.getSkull("eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDhhZmZkMjBlNmU2Zjg2MWZkOWYzN2M3ZmE4NWQ1NTIyOGM4NTk4MTdiNWRmYTBkZDIxOTA1MjYxZjlkYzJjNSJ9fX0=")
        val itemMeta = itemStack.itemMeta;
        itemMeta.setDisplayName("§f菁英哥布林法師 §7Lv.${pet.level}")
        val lore = buildList {
            add("")
            if(player != null) add("§7▪ 主人: &f${player.name}")
            add("§7▪ 品階: &b稀有")
            if(pet.nextLevelExp == -1){
                add(Language.MAX_LEVEL);
            }
            else add("§7▪ 經驗: &f${pet.exp}/${pet.nextLevelExp}")
            add("")
            add("§7§l傭兵公會不斷的嘗試")
            add("§7§l在經歷幾百次失敗後")
            add("§7§l終於成功培育成寵物的哥布林法師")
            add("")
            add("&f&l哥布林治癒 &7| &fCD: 80秒")
            add("&7哥布林法師施展哥布林部落的獨特法術")
            add("&7能夠恢復主人15%血量")
            add("")
            add("&7➤ + ${expBuff(pet)} 攻擊魔傷")

        }

        itemMeta.lore = IridiumColorAPI.process(lore);

        itemStack.itemMeta = itemMeta;
        return itemStack;
    }

    private fun expBuff(pet: Pet): Double {
        return Math.floor (pet.level/2.0);
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
        return  list;
    }
    val modifierKey = "heroes-pet";
    override fun onSpawn(spawnedPet: SpawnedPet) {
        MythicStats.addModifier(spawnedPet.owner.uniqueId,"MG_WEAPON_DAMAGE",expBuff(spawnedPet.pet),modifierKey)
    }

    override fun onDespawn(spawnedPet: SpawnedPet) {
        MythicStats.removeModifier(spawnedPet.owner.uniqueId,"MG_WEAPON_DAMAGE",modifierKey);
    }


}

HeroesPets.getPlugin().petManager.register(goblinshaman());