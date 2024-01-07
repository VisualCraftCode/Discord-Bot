package com.visualcraftcode.discord;

import com.visualcraftcode.discord.listeners.ButtonListener;
import com.visualcraftcode.discord.listeners.ReadyListener;
import com.visualcraftcode.discord.utils.Ticket;
import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.requests.GatewayIntent;
import net.dv8tion.jda.api.utils.cache.CacheFlag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * This file is a JavaDoc!
 * Created: 1/7/2024
 *
 * @author RedCrew
 * Discord: RedCrew#0100
 * Website: https://redcrewtv.de/
 */
public class DiscordBot {

    public static final DiscordBot INSTANCE = new DiscordBot();

    @Getter
    private final Properties settings = new Properties();

    @Getter
    private final JDA jda;

    @Getter
    private final List<Ticket> tickets = new ArrayList<>();

    /*
        TODO List

        [] Support (Ticket System, decide between support and bug report)
        [x] Information sending

     */

    private DiscordBot() {

        try {
            settings.load(DiscordBot.class.getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.jda = JDABuilder.createDefault(settings.getProperty("token"), GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.playing("VisualCraftCode"))
                .addEventListeners(new ReadyListener(), new ButtonListener())
                .disableCache(CacheFlag.EMOJI, CacheFlag.STICKER, CacheFlag.SCHEDULED_EVENTS, CacheFlag.VOICE_STATE)
                .build();
    }




    public static void main(String[] args) throws InterruptedException {
        DiscordBot.INSTANCE.getJda().awaitReady();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> DiscordBot.INSTANCE.getJda().shutdownNow()));

    }

}
