package org.soulslike.client.data;

public class ClientSoulsData {
    private static int playerSouls;
    private static int newPlayerSouls;

    private static int newPlayerSoulsTick;
    public static void set(int souls, int newPlayerSouls, int newPlayerSoulsTick) {
        ClientSoulsData.playerSouls = souls;
        ClientSoulsData.newPlayerSouls = newPlayerSouls;
        ClientSoulsData.newPlayerSoulsTick = newPlayerSoulsTick;
    }

    public static int getNewPlayerSoulsTick() {
        return newPlayerSoulsTick;
    }

    public static int getSouls() {
        return playerSouls;
    }

    public static int getNewPlayerSouls() {
        return newPlayerSouls;
    }
}
