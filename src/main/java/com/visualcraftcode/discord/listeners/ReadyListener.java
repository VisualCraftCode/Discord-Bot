package com.visualcraftcode.discord.listeners;

import com.visualcraftcode.discord.DiscordBot;
import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.entities.MessageEmbed;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;
import net.dv8tion.jda.api.events.GenericEvent;
import net.dv8tion.jda.api.events.session.ReadyEvent;
import net.dv8tion.jda.api.hooks.EventListener;

import java.awt.*;
import java.util.List;

/**
 * This file is a JavaDoc!
 * Created: 1/7/2024
 *
 * @author RedCrew
 * Discord: RedCrew#0100
 * Website: https://redcrewtv.de/
 */
public class ReadyListener implements EventListener {


    @Override
    public void onEvent(GenericEvent event) {
        if(event instanceof ReadyEvent) {
            sendInfos();
        }
    }

    private void sendInfos() {
        TextChannel channel = DiscordBot.INSTANCE.getJda().getTextChannelById(DiscordBot.INSTANCE.getSettings().getProperty("info_channel_id"));
        MessageEmbed welcome = new EmbedBuilder()
                .setColor(Color.CYAN)
                .setTitle("Welcome to the VisualCraftCode Discord")
                .setDescription("Hello and a warm welcome to all of our new members! \uD83D\uDC4B We're thrilled to have you join our community ❤\uFE0F\n" +
                        "\n" +
                        "\uD83D\uDCDC Before you dive in, please take a moment to read through our server rules. We want everyone to have a great time here, and following the guidelines helps create a positive environment for everyone.\n" +
                        "\n" +
                        "We're excited to have you here.\n" +
                        "Enjoy your stay!")
                .build();

        MessageEmbed rules = new EmbedBuilder()
                .setColor(Color.CYAN)
                .setTitle("\uD83D\uDED1 Rules")
                .setDescription("It is important for us to offer you a friendly and safe community. To achieve this we have developed some rules.\n" +
                        "Please note the language of the respective community area.\n" +
                        "\n" +
                        "\n" +
                        "\uD83D\uDCDC RESPECTFUL TREATMENT\n" +
                        "We ask you to avoid discrimination, racism and sexism. Always treat community and team members the way you would like to be treated!\n" +
                        "\n" +
                        "\n" +
                        "\uD83D\uDCDC NSFW CONTENT\n" +
                        "We ask you to not post NSFW content on this Discord. If you are not sure if something is NSFW, just don't post it.\n" +
                        "\n" +
                        "\n" +
                        "\uD83D\uDCDC SPAM AND ADVERTISING\n" +
                        "We ask you to avoid posting invitations and any other advertising. Also, please avoid posting spam messages like caps lock, emoji walls, etc.\n" +
                        "\n" +
                        "\n" +
                        "\uD83D\uDCDC VALIDITY OF THE RULES.\n" +
                        "We ask you to respect the rules in the public area as well as in personal messages.")
                .build();


        MessageEmbed links = new EmbedBuilder()
                .setColor(Color.CYAN)
                .setDescription("For additional questions feel free to contact us.")
                .addField("Links", "[Official website](https://visualcraftcode.com/)", true)
                .addField("Social Networks", "[GitHub](https://github.com/VisualCraftCode)", true)
                .build();


        //Clear Channel
        for (Message message : channel.getIterableHistory()) {
            message.delete().queue();
        }

        //Send Messages

        //welcome
        channel.sendMessage("https://media.discordapp.net/attachments/1193212050416074855/1193475974109610024/VisualCraftCode_Discord_Banner_Welcome.png?ex=65acda1d&is=659a651d&hm=2e72ac57726f95ea10ea5f6e6cad86648719a9f4a9568daa0a7189fbc703b5e5&=&format=webp&quality=lossless&width=490&height=148").queue();
        channel.sendMessageEmbeds(welcome).queue();

        //rules
        channel.sendMessage("https://media.discordapp.net/attachments/1193212050416074855/1193475973480468530/VisualCraftCode_Discord_Banner_Rules.png?ex=65acda1d&is=659a651d&hm=6fceff705dd31170ec8d9498c60e14d917c966df05ba7501d054128462262644&=&format=webp&quality=lossless&width=490&height=148").queue();
        channel.sendMessageEmbeds(rules).queue();

        //links
        channel.sendMessage("https://media.discordapp.net/attachments/1193212050416074855/1193475974453547028/VisualCraftCode_Discord_Banner_Links.png?ex=65acda1e&is=659a651e&hm=0622002ebaae1ddb9ac21b46fbb889e1ccb5a6ed24a8cbdefcd33e087a5e9945&=&format=webp&quality=lossless&width=490&height=148").queue();
        channel.sendMessageEmbeds(links).queue();

    }

}
