package com.visualcraftcode.discord.listeners;

import com.visualcraftcode.discord.DiscordBot;
import com.visualcraftcode.discord.utils.Ticket;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.Permission;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.entities.emoji.Emoji;
import net.dv8tion.jda.api.events.interaction.component.ButtonInteractionEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.interactions.components.ActionRow;
import net.dv8tion.jda.api.interactions.components.buttons.ButtonStyle;
import net.dv8tion.jda.api.interactions.components.text.TextInput;
import net.dv8tion.jda.api.interactions.components.text.TextInputStyle;
import net.dv8tion.jda.api.interactions.modals.Modal;
import net.dv8tion.jda.api.utils.messages.MessageCreateBuilder;
import net.dv8tion.jda.internal.interactions.component.ButtonImpl;

import java.awt.*;
import java.util.EnumSet;
import java.util.Random;

/**
 * This file is a JavaDoc!
 * Created: 10/4/2023
 *
 * @author RedCrew
 * Discord: RedCrew#0100
 * Website: https://redcrewtv.de/
 */
public class ButtonListener extends ListenerAdapter {

    @Override
    public void onButtonInteraction(ButtonInteractionEvent event) {
        final String id = DiscordBot.INSTANCE.getSettings().getProperty("support_channel_id");

        if(event.getButton().getId().equalsIgnoreCase("ticket.create") && event.getMessageChannel().getId().equals(id)) {
            createTicket(event.getMember());

            event.deferEdit().queue();

        } else if (event.getButton().getId().equalsIgnoreCase("ticket.delete")) {
            event.deferEdit().queue();

            TextChannel channel = event.getChannel().asTextChannel();
            Member member = null;
            for (Ticket ticket : DiscordBot.INSTANCE.getTickets()) {
                if(ticket.getChannel().getId().equals(channel.getId())) {
                    member = ticket.getMember();
                    DiscordBot.INSTANCE.getTickets().remove(ticket);
                    break;
                }
            }
            if(member == null) throw new RuntimeException("Invalid Ticket!");

            channel.getManager().setParent(DiscordBot.INSTANCE.getJda().getCategoryById(DiscordBot.INSTANCE.getSettings().getProperty("archieved_id")))
                    .removePermissionOverride(member)
                    .queue();


        } else if(event.getButton().getId().equalsIgnoreCase("bug.create") && event.getMessageChannel().getId().equals(id)) {
            createBug(event.getMember());
            event.deferEdit().queue();
        }
    }

    private void createTicket(Member member) {
        TextChannel channel = DiscordBot.INSTANCE.getJda().getCategoryById(DiscordBot.INSTANCE.getSettings().getProperty("tickets_id"))
                .createTextChannel("ticket-" + new Random().nextInt(9999))
                    .addPermissionOverride(DiscordBot.INSTANCE.getJda().getRoleById(DiscordBot.INSTANCE.getSettings().getProperty("everyone_id")), null, EnumSet.of(Permission.VIEW_CHANNEL))
                    .addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL), null).complete();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("VisualCraftCode - Support");
        builder.setColor(Color.CYAN);
        builder.setDescription("Please describe the problem or question you have.");
        builder.setImage("https://media.discordapp.net/attachments/1193212050416074855/1193475973753090089/VisualCraftCode_Discord_Banner_Support.png?ex=65acda1d&is=659a651d&hm=d4439f8e936cf0eb477333a834da2a4d9f68086d130abc3eb7ce030081f06c20&=&format=webp&quality=lossless&width=490&height=148");

        MessageCreateBuilder messageBuilder = new MessageCreateBuilder();
        messageBuilder.setEmbeds(builder.build());
        messageBuilder.setActionRow(new ButtonImpl("ticket.delete", "Close ticket", ButtonStyle.DANGER, false, Emoji.fromUnicode("\uD83D\uDDD1\uFE0F")));

        channel.sendMessage(messageBuilder.build()).queue();

        DiscordBot.INSTANCE.getTickets().add(new Ticket(channel, member, Ticket.Type.SUPPORT));
    }

    private void createBug(Member member) {
        TextChannel channel = DiscordBot.INSTANCE.getJda().getCategoryById(DiscordBot.INSTANCE.getSettings().getProperty("bugs_id"))
                .createTextChannel("bug-" + new Random().nextInt(9999))
                .addPermissionOverride(DiscordBot.INSTANCE.getJda().getRoleById(DiscordBot.INSTANCE.getSettings().getProperty("everyone_id")), null, EnumSet.of(Permission.VIEW_CHANNEL))
                .addPermissionOverride(member, EnumSet.of(Permission.VIEW_CHANNEL), null).complete();

        EmbedBuilder builder = new EmbedBuilder();
        builder.setTitle("VisualCraftCode - Bugs");
        builder.setColor(Color.CYAN);
        builder.setDescription("Please describe the bug you have found.");
        builder.setImage("https://media.discordapp.net/attachments/1193212050416074855/1193475973753090089/VisualCraftCode_Discord_Banner_Support.png?ex=65acda1d&is=659a651d&hm=d4439f8e936cf0eb477333a834da2a4d9f68086d130abc3eb7ce030081f06c20&=&format=webp&quality=lossless&width=490&height=148");

        MessageCreateBuilder messageBuilder = new MessageCreateBuilder();
        messageBuilder.setEmbeds(builder.build());
        messageBuilder.setActionRow(new ButtonImpl("ticket.delete", "Close Bug", ButtonStyle.DANGER, false, Emoji.fromUnicode("\uD83D\uDDD1\uFE0F")));

        channel.sendMessage(messageBuilder.build()).queue();

        DiscordBot.INSTANCE.getTickets().add(new Ticket(channel, member, Ticket.Type.SUPPORT));
    }
}
