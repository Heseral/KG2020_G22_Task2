import javax.swing.*;

public abstract class Main {
    public static void main(String[] args) {
        // не создавал класс MainWindow за ненадобностью. В случае необходимости он может быть легко добавлен,
        // но пока не вижу смысла его делать - стандартный JFrame и так хорошо справляется
        JFrame mainWindow = new JFrame();
        mainWindow.add(new DrawTester());
        mainWindow.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        mainWindow.setSize(GlobalVar.SCREEN_WIDTH, GlobalVar.SCREEN_HEIGHT);
        mainWindow.setVisible(true);
    }
}
