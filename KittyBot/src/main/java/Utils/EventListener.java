package Utils;

import Debug.Debug;
import Variables.Roles;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.entities.*;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;

import java.io.IOException;
import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

public class EventListener extends ListenerAdapter {

    private Roles Roles = new Roles();
    private EmbedCreator EC = new EmbedCreator();

    public void onMessageReceived(MessageReceivedEvent event) {
        JDA jda = event.getJDA();
        User author = event.getAuthor();
        Message message = event.getMessage();
        String msg = message.getContentDisplay();
        boolean bot = author.isBot();

        if (event.isFromType(ChannelType.TEXT) && !bot)
        {
            Guild guild = event.getGuild();
            TextChannel textChannel = event.getTextChannel();
            Member member = event.getMember();
            String name;
            assert member != null;
            String UID = member.getId().toString();
            if (message.isWebhookMessage())
            {
                name = author.getName();
            }
            else
            {
                name = member.getEffectiveName();
            }

            if (msg.startsWith("~") && msg.contains("~stats")) {
                List<User> toStats = message.getMentionedUsers();
                toStats.forEach(user -> {
                    String userID = user.getId().toString();
                    try {
                        JsonUtils.refreshUsers();
                        Collection<String> Base = JsonUtils.returnBase();
                        if (Base.contains(userID)) {
                            JsonBase data = JsonUtils.JsonParseToBase(userID);
                            EC.Stats(data, textChannel);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
            }
            else if (msg.startsWith("~") && msg.contains("~addrate") && message.getMember().getRoles().toString().contains(Roles.getCM())) {
                List<User> toRate = message.getMentionedUsers();
                toRate.forEach(user -> {
                    String userID = user.getId().toString();
                    try {
                        JsonUtils.refreshUsers();
                        Collection<String> Base = JsonUtils.returnBase();
                        if (Base.contains(userID)) {
                            JsonBase data = JsonUtils.JsonParseToBase(userID);
                            int rate = Integer.parseInt(msg.split(user.getName() + " ")[1]);
                            int fullRating = data.rating + rate;
                            if (fullRating >= 50 && fullRating <= 100) {
                                data.rating = fullRating;
                                data.currentRole = Roles.getSilver();
                                data.nextStep = 100;
                                data.roleName = "Silver";
                                data.nextRoleName = "Gold";
                                EC.newRole(data, textChannel);
                                this.updateRole(guild, guild.getMember(user), Roles.getNewbie(), Roles.getSilver());
                            }
                            else if (fullRating >= 100 && fullRating <= 250) {
                                data.rating = fullRating;
                                data.currentRole = Roles.getGold();
                                data.nextStep = 250;
                                data.roleName = "Gold";
                                data.nextRoleName = "Platinum";
                                EC.newRole(data, textChannel);
                                this.updateRole(guild, guild.getMember(user), Roles.getSilver(), Roles.getGold());
                            }
                            else if (fullRating >= 250 && fullRating <= 500) {
                                data.rating = fullRating;
                                data.currentRole = Roles.getPlatinum();
                                data.nextStep = 500;
                                data.roleName = "Platinum";
                                data.nextRoleName = "Nova";
                                EC.newRole(data, textChannel);
                                this.updateRole(guild, guild.getMember(user), Roles.getGold(), Roles.getPlatinum());
                            }
                            else if (fullRating >= 500 && fullRating <= 1000) {
                                data.rating = fullRating;
                                data.currentRole = Roles.getNova();
                                data.nextStep = 1000;
                                data.roleName = "Nova";
                                data.nextRoleName = "Elite";
                                EC.newRole(data, textChannel);
                                this.updateRole(guild, guild.getMember(user), Roles.getPlatinum(), Roles.getNova());
                            }
                            else if (fullRating >= 1000 && fullRating <= 2000) {
                                data.rating = fullRating;
                                data.currentRole = Roles.getElite();
                                data.nextStep = 2000;
                                data.roleName = "Elite";
                                data.nextRoleName = "Master";
                                EC.newRole(data, textChannel);
                                this.updateRole(guild, guild.getMember(user), Roles.getNova(), Roles.getElite());
                            }
                            else if (fullRating >= 2000 && fullRating <= 3000) {
                                data.rating = fullRating;
                                data.currentRole = Roles.getMaster();
                                data.nextStep = 3000;
                                data.roleName = "Master";
                                data.nextRoleName = "Grand Master";
                                EC.newRole(data, textChannel);
                                this.updateRole(guild, guild.getMember(user), Roles.getElite(), Roles.getMaster());
                            }
                            else if (fullRating >= 3000 && fullRating <= 4000) {
                                data.rating = fullRating;
                                data.currentRole = Roles.getGrandMaster();
                                data.nextStep = 4000;
                                data.roleName = "Grand Master";
                                data.nextRoleName = "Supreme";
                                EC.newRole(data, textChannel);
                                this.updateRole(guild, guild.getMember(user), Roles.getMaster(), Roles.getGrandMaster());
                            }
                            else if (fullRating >= 4000 && fullRating <= 5000) {
                                data.rating = fullRating;
                                data.currentRole = Roles.getSupreme();
                                data.nextStep = 5000;
                                data.roleName = "Supreme";
                                data.nextRoleName = "Global Elite";
                                EC.newRole(data, textChannel);
                                this.updateRole(guild, guild.getMember(user), Roles.getGrandMaster(), Roles.getSupreme());
                            }
                            else if (fullRating >= 5000 && fullRating <= 10000) {
                                data.rating = fullRating;
                                data.currentRole = Roles.getGlobalElite();
                                data.nextStep = 10000;
                                data.roleName = "Global Elite";
                                data.nextRoleName = "Legendary";
                                EC.newRole(data, textChannel);
                                this.updateRole(guild, guild.getMember(user), Roles.getSupreme(), Roles.getGlobalElite());
                            }
                            else if (fullRating >= 10000) {
                                data.rating = fullRating;
                                EC.Legendary(data, textChannel);
                            }
                            JsonUtils.writeJson(data);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
            }
            else if (msg.startsWith("~") && msg.contains("~punish") && message.getMember().getRoles().toString().contains(Roles.getCM())) {
                List<User> toPunish = message.getMentionedUsers();
                toPunish.forEach(user -> {
                    String userID = user.getId().toString();
                    try {
                        JsonUtils.refreshUsers();
                        Collection<String> Base = JsonUtils.returnBase();
                        if (Base.contains(userID)) {
                            JsonBase data = JsonUtils.JsonParseToBase(userID);
                            guild.removeRoleFromMember(guild.getMember(user), Objects.requireNonNull(guild.getRoleById(data.currentRole))).queue();
                            data = new JsonBase(userID, user.getName(), Roles.getNewbie(), "Newbie", "Silver", 0, 50, Instant.now().toString());
                            EC.Punish(data, textChannel);
                            JsonUtils.writeJson(data);
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                });
            }

            if (!textChannel.getId().equals("609428100962582541")) {
                try {
                    JsonUtils.refreshUsers();
                    Collection<String> Base = JsonUtils.returnBase();
                    if (Base.contains(UID)) {
                        JsonBase data = JsonUtils.JsonParseToBase(UID);
                        data.rating++;
                        switch (data.rating) {
                            case 50:
                                this.updateRole(guild, member, Roles.getNewbie(), Roles.getSilver());
                                data.currentRole = Roles.getSilver();
                                data.nextStep = 100;
                                data.roleName = "Silver";
                                data.nextRoleName = "Gold";
                                EC.newRole(data, textChannel);
                                break;
                            case 100:
                                this.updateRole(guild, member, Roles.getSilver(), Roles.getGold());
                                data.currentRole = Roles.getGold();
                                data.nextStep = 250;
                                data.roleName = "Gold";
                                data.nextRoleName = "Platinum";
                                EC.newRole(data, textChannel);
                                break;
                            case 250:
                                this.updateRole(guild, member, Roles.getGold(), Roles.getPlatinum());
                                data.currentRole = Roles.getPlatinum();
                                data.nextStep = 500;
                                data.roleName = "Platinum";
                                data.nextRoleName = "Nova";
                                EC.newRole(data, textChannel);
                                break;
                            case 500:
                                this.updateRole(guild, member, Roles.getPlatinum(), Roles.getNova());
                                data.currentRole = Roles.getNova();
                                data.nextStep = 1000;
                                data.roleName = "Nova";
                                data.nextRoleName = "Elite";
                                EC.newRole(data, textChannel);
                                break;
                            case 1000:
                                this.updateRole(guild, member, Roles.getNova(), Roles.getElite());
                                data.currentRole = Roles.getElite();
                                data.nextStep = 2000;
                                data.roleName = "Elite";
                                data.nextRoleName = "Master";
                                EC.newRole(data, textChannel);
                                break;
                            case 2000:
                                this.updateRole(guild, member, Roles.getElite(), Roles.getMaster());
                                data.currentRole = Roles.getMaster();
                                data.nextStep = 3000;
                                data.roleName = "Master";
                                data.nextRoleName = "Grand Master";
                                EC.newRole(data, textChannel);
                                break;
                            case 3000:
                                this.updateRole(guild, member, Roles.getMaster(), Roles.getGrandMaster());
                                data.currentRole = Roles.getGrandMaster();
                                data.nextStep = 4000;
                                data.roleName = "Grand Master";
                                data.nextRoleName = "Supreme";
                                EC.newRole(data, textChannel);
                                break;
                            case 4000:
                                this.updateRole(guild, member, Roles.getGrandMaster(), Roles.getSupreme());
                                data.currentRole = Roles.getSupreme();
                                data.nextStep = 5000;
                                data.roleName = "Supreme";
                                data.nextRoleName = "Global Elite";
                                EC.newRole(data, textChannel);
                                break;
                            case 5000:
                                this.updateRole(guild, member, Roles.getSupreme(), Roles.getGlobalElite());
                                data.currentRole = Roles.getGlobalElite();
                                data.nextStep = 10000;
                                data.roleName = "Global Elite";
                                data.nextRoleName = "Legendary";
                                EC.newRole(data, textChannel);
                                break;
                            case 10000:
                                EC.Legendary(data, textChannel);
                                break;
                            default:
                                break;
                        }
                        JsonUtils.writeJson(data);
                    }
                    else {
                        JsonUtils.createNewUser(UID);
                        event.getGuild().addRoleToMember(member, Objects.requireNonNull(event.getGuild().getRoleById(Roles.getNewbie()))).queue();
                        JsonBase data = new JsonBase(UID, author.getName(), Roles.getNewbie(), "Newbie", "Silver", 1, 50, Instant.now().toString());
                        JsonUtils.writeJson(data);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            String createChatString = guild.getName() + " | " + textChannel.getName() + " | " + name + " | " + msg;
            Debug.message("GUILD CHAT", "MessageReceive", createChatString);
        }
        else if (event.isFromType(ChannelType.PRIVATE) && !bot)
        {
            PrivateChannel privateChannel = event.getPrivateChannel();
            String createChatString = author.getName() + " | " + msg;
            privateChannel.sendMessage("Hello! I can't read private messages!").queue();
            Debug.message("PRIVATE CHAT", "MessageReceive", createChatString);
        }
    }

    private void updateRole(Guild guild, Member member, String currentRole, String newRole) {
        guild.removeRoleFromMember(member, Objects.requireNonNull(guild.getRoleById(currentRole))).queue();
        guild.addRoleToMember(member, Objects.requireNonNull(guild.getRoleById(newRole))).queue();
    }
}

