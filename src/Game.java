import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class Graphic extends JPanel {

    Grid grid;

    public Graphic(Grid g) {
        super();
        this.grid = g;
    }

    private void doDrawing(Graphics g) {
        Graphics2D g2d = (Graphics2D) g;
        Color dead = new Color(0,0,0);
        Color alive = new Color(255,255,255);
        for (int i = 0; i < 20 * Grid.SIZE; i += 20) {
            for (int j = 0; j < 20 * Grid.SIZE; j += 20) {
                g2d.setColor(dead);
                g2d.drawRect(i, j, 20, 20);
                if (grid.getGridCell(i/20,j/20).getIsAlive()) g2d.setColor(alive);
                g2d.fillRect(i, j, 20, 20);
            }
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        doDrawing(g);
    }
}

public class Game extends JFrame {

    public static final int tickSpeed = 250; //rate of generation ticks in milliseconds

    static Grid grid;
    static Graphic graphic;

    public Game(Grid g) {
        initUI(g);
    }

    private void initUI(Grid g) {
        graphic = new Graphic(g);
        add(graphic);

        setResizable(false);
        setSize(20*Grid.SIZE,20*Grid.SIZE);
        setTitle("Game of Life");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static Point subtract(Point a, Point b) {
        return new Point(a.x-b.x, a.y-b.y);
    }

    public static void bringToLife() {
        //will take mouse clicks and bring corresponding cell to life
        Point location = subtract(MouseInfo.getPointerInfo().getLocation(), graphic.getLocationOnScreen());
        if (location.x >= 0 && location.y >= 0 && location.x <= 20*Grid.SIZE && location.y <= 20*Grid.SIZE) {
            //if within bounds of window
            System.out.println("change" + location.y / 20 + " " + location.x/20);
            grid.getGridCell(location.x / 20, location.y / 20).
                    setIsAlive(!grid.getGridCell(location.x / 20, location.y / 20).getIsAlive());
            //kills if alive, brings alive if dead
        }
    }

    public static void main(String[] args) {

        //boolean running = true;

        grid = new Grid();

        Game ex = new Game(grid);
        ex.setVisible(true);

        //listens for mouse click
        ex.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                System.out.println("Alive!");
                bringToLife();
            }
        });

        ex.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent keyEvent) {

            }
            @Override
            public void keyPressed(KeyEvent keyEvent) {
                if (keyEvent.getKeyCode() == KeyEvent.VK_SPACE) grid.running = !grid.running;
            }
            @Override
            public void keyReleased(KeyEvent keyEvent) {

            }
        });

        Timer timer = new Timer(tickSpeed, null);
        timer.setRepeats(true);
        timer.addActionListener(evt -> {
            //run cycle of game
            if (grid.running) grid.advanceGeneration();
            ex.repaint(); //updates the gui
        });
        timer.start();

    }

}
