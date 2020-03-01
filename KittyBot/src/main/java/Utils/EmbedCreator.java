package Utils;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.MessageChannel;
import java.awt.*;
import java.time.Instant;

public class EmbedCreator {

    public void newRole(JsonBase data, MessageChannel channel) {
        EmbedBuilder log = new EmbedBuilder();
        log.setTitle("I add new role to " + data.userName + "!");
        log.setDescription("Current rating: " + data.rating + "\nNext step: " + data.nextStep + "\nCurrent role: " + data.roleName + "\nNext role: " + data.nextRoleName);
        log.setAuthor("Kitty Bot | Auto Role", null, "https://github.com/Gagong/Kitty/raw/master/KittyBot/KittyBot.jpg");
        log.setColor(Color.pink);
        log.setTimestamp(Instant.now());
        channel.sendMessage(log.build()).queue();
    }

    public void Legendary(JsonBase data, MessageChannel channel) {
        EmbedBuilder log = new EmbedBuilder();
        log.setTitle("New Legendary user on our server - " + data.userName + "!");
        log.setDescription("You unlock all roles on our server! Congratulations!\nContact Community Managers for more details!");
        log.setAuthor("Kitty Bot | Auto Role", null, "https://github.com/Gagong/Kitty/raw/master/KittyBot/KittyBot.jpg");
        log.setColor(Color.red);
        log.setTimestamp(Instant.now());
        channel.sendMessage(log.build()).queue();
    }

    public void Stats(JsonBase data, MessageChannel channel) {
        EmbedBuilder log = new EmbedBuilder();
        log.setTitle("Current stats for " + data.userName + ":");
        log.setDescription("Current rating: " + data.rating + "\nNext step: " + data.nextStep + "\nCurrent role: " + data.roleName + "\nNext role: " + data.nextRoleName);
        log.setAuthor("Kitty Bot | Stats", null, "https://github.com/Gagong/Kitty/raw/master/KittyBot/KittyBot.jpg");
        log.setColor(Color.blue);
        log.setTimestamp(Instant.now());
        channel.sendMessage(log.build()).queue();
    }

    public void Punish(JsonBase data, MessageChannel channel) {
        EmbedBuilder log = new EmbedBuilder();
        log.setTitle("Punishment for " + data.userName + "!");
        log.setDescription("All stats removed!\nCurrent rating: " + data.rating + "\nNext step: " + data.nextStep + "\nCurrent role: " + data.roleName + "\nNext role: " + data.nextRoleName);
        log.setAuthor("Kitty Bot | Stats", null, "https://github.com/Gagong/Kitty/raw/master/KittyBot/KittyBot.jpg");
        log.setColor(Color.black);
        log.setTimestamp(Instant.now());
        channel.sendMessage(log.build()).queue();
    }

}
