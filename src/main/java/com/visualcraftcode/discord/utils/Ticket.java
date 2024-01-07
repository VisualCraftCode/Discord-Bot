package com.visualcraftcode.discord.utils;

import lombok.Getter;
import net.dv8tion.jda.api.entities.Member;
import net.dv8tion.jda.api.entities.channel.concrete.TextChannel;

/**
 * This file is a JavaDoc!
 * Created: 10/5/2023
 *
 * @author RedCrew
 * Discord: RedCrew#0100
 * Website: https://redcrewtv.de/
 */
public class Ticket {

    private final TextChannel channel;
    private final Member member;

    private @Getter final Type type;

    public Ticket(TextChannel channel, Member member, Type type) {
        this.channel = channel;
        this.member = member;
        this.type = type;
    }

    public TextChannel getChannel() {
        return channel;
    }

    public Member getMember() {
        return member;
    }

    public enum Type {
        SUPPORT, BUG
    }


}
