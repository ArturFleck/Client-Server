import com.sun.jna.platform.win32.User32;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.ptr.IntByReference;

public class ActivateWindow {
    public static void main(String[] args) {
        int processId = GuiProcessesList.findMyApp("EXCEL"); // Replace with the desired process ID

        if (activateWindowByProcessId(processId)) {
            System.out.println("Window activated successfully.");
        } else {
            System.out.println("Failed to activate the window.");
        }
    }

    private static boolean activateWindowByProcessId(long processId) {
        User32 user32 = User32.INSTANCE;
        WinDef.HWND hwnd = user32.FindWindow(null, null);

        while (hwnd != null) {
            IntByReference pidRef = new IntByReference();
            user32.GetWindowThreadProcessId(hwnd, pidRef);

            if (pidRef.getValue() == processId) {
                user32.ShowWindow(hwnd, User32.SW_RESTORE);
                user32.SetForegroundWindow(hwnd);
                return true;
            }

            hwnd = user32.FindWindowEx(null, hwnd, null, null);
        }

        return false;
    }
}
