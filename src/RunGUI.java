public class RunGUI
{
    public static void main(String[] args)
    {
        PrototypeGUI gui = new PrototypeGUI();
        new Thread(new QueryThread()).start(); // Perhaps too concise
        gui.initFrame();
    }
}
