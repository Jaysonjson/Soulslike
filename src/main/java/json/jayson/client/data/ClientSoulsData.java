package json.jayson.client.data;

public class ClientSoulsData {
    private static long playerSouls;
    private static long newPlayerSouls;

    private static long newPlayerSoulsTick;
    public static void set(long souls, long newPlayerSouls, long newPlayerSoulsTick) {
        ClientSoulsData.playerSouls = souls;
        ClientSoulsData.newPlayerSouls = newPlayerSouls;
        ClientSoulsData.newPlayerSoulsTick = newPlayerSoulsTick;
    }

    public static long getNewPlayerSoulsTick() {
        return newPlayerSoulsTick;
    }

    public static long getSouls() {
        return playerSouls;
    }

    public static long getNewPlayerSouls() {
        return newPlayerSouls;
    }
}
