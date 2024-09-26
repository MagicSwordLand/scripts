import org.bukkit.Bukkit

// 1000 => 1sec
//
// 24000t = 20min = 20*60*1000

val t = 20*60*1000/24000

val twenty_min = 20*60*1000;

val currentTime = System.currentTimeMillis();

val w = currentTime%twenty_min;

val finalTime = w/t;

Bukkit.getWorlds().forEach {
    it.time = finalTime;
}
