package win.kakchuserver.playerlisttoggle;

public final class PlayerListToggleState {
    private static boolean toggled;
    private static boolean playerListDown;

    private PlayerListToggleState() {
    }

    public static boolean toggled() {
        return toggled;
    }

    public static void toggle() {
        toggled = !toggled;
    }

    public static boolean playerListDown() {
        return playerListDown;
    }

    public static void playerListDown(boolean down) {
        playerListDown = down;
    }
}
