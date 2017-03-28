package nl.stijn.KitPvP.Utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.bukkit.ChatColor;
import org.bukkit.craftbukkit.v1_8_R3.entity.CraftPlayer;
import org.bukkit.entity.Player;

import net.minecraft.server.v1_8_R3.IChatBaseComponent;
import net.minecraft.server.v1_8_R3.PacketPlayOutTitle;
import net.minecraft.server.v1_8_R3.PlayerConnection;
import nl.stijn.KitPvP.KitPvP;

public class ScreenUtil {
   
	public static ScreenUtil getInstance;
	
	public ScreenUtil() {
		getInstance = this;
		
	}
	
	public static String prefix = ChatColor.RED + "" + ChatColor.BOLD + "KitPvP " + ChatColor.RESET;
	
   public static void sendTitle(Player player, Integer fadeIn, Integer stay, Integer fadeOut, String title, String subtitle)
     {
       PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;
       
       PacketPlayOutTitle packetPlayOutTimes = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TIMES, null, fadeIn.intValue(), stay.intValue(), fadeOut.intValue());
       connection.sendPacket(packetPlayOutTimes);
       if (subtitle != null)
       {
         IChatBaseComponent titleSub = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + subtitle + "\"}");
         PacketPlayOutTitle packetPlayOutSubTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.SUBTITLE, titleSub);
         connection.sendPacket(packetPlayOutSubTitle);
       }
       if (title != null){
        IChatBaseComponent titleStoelendans = IChatBaseComponent.ChatSerializer.a("{\"text\": \"" + title + "\"}");
        PacketPlayOutTitle packetPlayOutTitle = new PacketPlayOutTitle(PacketPlayOutTitle.EnumTitleAction.TITLE, titleStoelendans);
        connection.sendPacket(packetPlayOutTitle);
       }
 }
   
   public static void Errorbar(Player player, String message)
   {
     try
     {
       Class<?> c1 = Class.forName("org.bukkit.craftbukkit." + KitPvP.nmsver + ".entity.CraftPlayer");
       Object p = c1.cast(player);
       Object ppoc = null;
       Class<?> c4 = Class.forName("net.minecraft.server." + KitPvP.nmsver + ".PacketPlayOutChat");
       Class<?> c5 = Class.forName("net.minecraft.server." + KitPvP.nmsver + ".Packet");
       if ((KitPvP.nmsver.equalsIgnoreCase("v1_8_R1")) || (!KitPvP.nmsver.startsWith("v1_8_")))
       {
         Class<?> c2 = Class.forName("n	et.minecraft.server." + KitPvP.nmsver + ".ChatSerializer");
         Class<?> c3 = Class.forName("net.minecraft.server." + KitPvP.nmsver + ".IChatBaseComponent");
         Method m3 = c2.getDeclaredMethod("a", new Class[] { String.class });
         Object cbc = c3.cast(m3.invoke(c2, new Object[] { "{\"text\": \"" + message + "\"}" }));
         ppoc = c4.getConstructor(new Class[] { c3, Byte.TYPE }).newInstance(new Object[] { cbc, Byte.valueOf((byte) 2) });
       }
       else
       {
         Class<?> c2 = Class.forName("net.minecraft.server." + KitPvP.nmsver + ".ChatComponentText");
         Class<?> c3 = Class.forName("net.minecraft.server." + KitPvP.nmsver + ".IChatBaseComponent");
         Object o = c2.getConstructor(new Class[] { String.class }).newInstance(new Object[] { message });
         ppoc = c4.getConstructor(new Class[] { c3, Byte.TYPE }).newInstance(new Object[] { o, Byte.valueOf((byte) 2) });
       }
       Method m1 = c1.getDeclaredMethod("getHandle", new Class[0]);
       Object h = m1.invoke(p, new Object[0]);
       Field f1 = h.getClass().getDeclaredField("playerConnection");
       Object pc = f1.get(h);
       Method m5 = pc.getClass().getDeclaredMethod("sendPacket", new Class[] { c5 });
       m5.invoke(pc, new Object[] { ppoc });
     }
     catch (Exception ex)
     {
       ex.printStackTrace();
       KitPvP.works = false;
     }
   }
 
}