package com.visualcraftcode.discord;

import lombok.Getter;
import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;

import java.io.IOException;
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

    private DiscordBot() {

        try {
            settings.load(DiscordBot.class.getClassLoader().getResourceAsStream("settings.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.jda = JDABuilder.createDefault(settings.getProperty("token"))
                .setActivity(Activity.playing("VisualCraftCode"))
                .build();
    }




    public static void main(String[] args) throws InterruptedException {
        DiscordBot.INSTANCE.getJda().awaitReady();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> DiscordBot.INSTANCE.getJda().shutdownNow()));

    }

}
