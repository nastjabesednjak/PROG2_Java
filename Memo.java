import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;
import java.awt.*;
import java.util.Arrays;

public class Memo {

    public static final Color PURPLE = new Color(102, 0, 153);

    public static int cX = 75;
    public static int cY = 75;

    private static class Krog {
        int x;
        int y;
        int r = 50;
        Color barva;

        public Krog(int xk, int yk, Color barva_k) {
            this.x = xk;
            this.y = yk;
            this.barva = barva_k;
        }
    }

    static LinkedList<Krog> krogi = new LinkedList<>();
    static LinkedList<Krog> prikaz = new LinkedList<>();

    private static void dodajKrog(Color barva) {
        Krog nov = new Krog(cX, cY, barva);
        krogi.add(nov);

        cX += 75;
        if (cX > 300) {
            cX = 75;
            cY += 75;
        }
    }

    private static class Panel extends JPanel {

        public Panel() {
            super();
            setBackground(Color.WHITE);
        }

        @Override
        public void paint(Graphics g) {
            super.paint(g); // klic metode nadrazreda
            Graphics2D graphics = (Graphics2D) g; // pretvarjanje tipov
            graphics.setStroke(new BasicStroke(2.0f)); // čopič debeline 2

            for (int i = 0; i < krogi.size(); i++) {
                Krog trenutni = krogi.get(i);
                graphics.setColor(trenutni.barva);
                graphics.fillOval(trenutni.x, trenutni.y, trenutni.r, trenutni.r);
            }

            for (int i = 0; i < prikaz.size(); i++) {
                Krog trenutni = prikaz.get(i);
                graphics.setColor(trenutni.barva);
                graphics.fillOval(400 + trenutni.x, trenutni.y, trenutni.r, trenutni.r);
            }
        }

    }

    //Računalnik naključno izbere 4 izmed 6 barv
    private static int[] izbira() {

        LinkedList<Integer> barveList = new LinkedList<Integer>();

        for (int i = 1; i <= 6; i++) {
            barveList.add(i);
        }

        Collections.shuffle(barveList);

        int[] barve = new int[4];
        for (int i = 0; i < barve.length; i++) {
            barve[i] = barveList.get(i);
        }
        return  barve;
    }

    // Prikaže se pravilna resitev
    private static void izpisiResitev(int[] resitev) {

        int x5 = 175;
        int x6 = 150;
        int y3 = 50;
        int y4 = 25;

        Color[] barve = new Color[4];

        for (int i = 0; i < 4; i++) {
            switch (resitev[i]) {
                case 1:
                    barve[i] = Color.BLUE;
                    break;
                case 2:
                    barve[i] = Color.RED;
                    break;
                case 3:
                    barve[i] = Color.YELLOW;
                    break;
                case 4:
                    barve[i] = Color.GREEN;
                    break;
                case 5:
                    barve[i] = Color.MAGENTA;
                    break;
                case 6:
                    barve[i] = Color.ORANGE;
                    break;
            }
        }
    }

    private static void primerjavaResitev() {
        int[] barve = izbira();
        int[] vnos = new int[4];
    }

    public static void main(String[] args) {
        int x1 = 25;
        int x2 = 0;
        int y1 = 50;
        int y2 = 25;

        int poskus = 0;
        int tezavnost = 24;
        int tezavnostRaw = 6;

        final int[] pravilnihMest = {0};
        final int[] pravilnihBaru = {0};

        int[] barve = izbira();
        LinkedList<Integer> vnos = new LinkedList<>();

        JFrame frame = new JFrame("Memo, ugani barve!");
        frame.setSize(new Dimension(1024, 720)); // nastavimo sirino in dolzino okna
        frame.setMinimumSize(new Dimension(800, 600));
        frame.setResizable(true); // velikost okna lahko spreminjamo

        JPanel panel = new Panel(); // new JPanel();
        // panel.setBackground(Color.WHITE);
        frame.add(panel);

        frame.setLayout(new BorderLayout());
        frame.add(panel, BorderLayout.CENTER); // bel panel dodamo v center
        JPanel north = new JPanel();
        frame.add(north, BorderLayout.NORTH); // en panel doda  mo na vrh

        north.add(new JLabel("Izberi barvo: ")); // napis na začetku severnega panela

        JButton button0 = new JButton("Modra"); // dodamo gumb
        button0.setPreferredSize(new Dimension(96, 40));
        button0.setBackground(Color.BLUE);
        button0.setForeground(Color.WHITE);
        button0.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                dodajKrog(Color.BLUE);
                panel.repaint();
                vnos.add(1);

                int[] help = new int[4];

                for (int i = 0; i < 4; i++) {
                    help[i] = barve[i];
                }

                if (vnos.size() >= 4) {
                    for (int i = 0; i < 4; i++) {
                        if (vnos.get(i) == help[i]) {
                            pravilnihMest[0]++;
                            vnos.set(i, 0);
                            help[i] = 0;
                        }
                    }
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (help[i] != 0 && help[i] == vnos.get(j)) {
                                pravilnihBaru[0]++;
                                help[i] = 0;
                                vnos.set(j, 0);
                            }
                        }
                    }
                    for (int i = 0; i < pravilnihMest[0]; i++) {
                        Krog nov = new Krog(400 + i*75, cY, Color.BLACK);
                        prikaz.add(nov);
                    }
                    for (int i = 0; i < pravilnihBaru[0]; i++) {
                        Krog nov = new Krog(400 + (i + pravilnihMest[0])*75, cY, Color.WHITE);
                        prikaz.add(nov);
                    }
                    panel.repaint();
                }
            }
        });
        north.add(button0);

        JButton button1 = new JButton("Rdeča"); // dodamo gumb
        button1.setPreferredSize(new Dimension(96, 40));
        button1.setBackground(Color.RED);
        button1.setForeground(Color.WHITE);
        button1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajKrog(Color.RED);
                panel.repaint();
                vnos.add(2);

                int[] help = new int[4];

                for (int i = 0; i < 4; i++) {
                    help[i] = barve[i];
                }

                if (vnos.size() >= 4) {
                    for (int i = 0; i < 4; i++) {
                        if (vnos.get(i) == help[i]) {
                            pravilnihMest[0]++;
                            vnos.set(i, 0);
                            help[i] = 0;
                        }
                    }
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (help[i] != 0 && help[i] == vnos.get(j)) {
                                pravilnihBaru[0]++;
                                help[i] = 0;
                                vnos.set(j, 0);
                            }
                        }
                    }
                    for (int i = 0; i < pravilnihMest[0]; i++) {
                        Krog nov = new Krog(400 + i*75, cY, Color.BLACK);
                        prikaz.add(nov);
                    }
                    for (int i = 0; i < pravilnihBaru[0]; i++) {
                        Krog nov = new Krog(400 + (i + pravilnihMest[0])*75, cY, Color.WHITE);
                        prikaz.add(nov);
                    }
                    panel.repaint();
                }
            }
        });
        north.add(button1);

        JButton button2 = new JButton("Rumena"); // dodamo gumb
        button2.setPreferredSize(new Dimension(96, 40));
        button2.setBackground(Color.YELLOW);
        button2.setForeground(Color.BLACK);
        button2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajKrog(Color.YELLOW);
                panel.repaint();
                vnos.add(3);

                int[] help = new int[4];

                for (int i = 0; i < 4; i++) {
                    help[i] = barve[i];
                }

                if (vnos.size() >= 4) {
                    for (int i = 0; i < 4; i++) {
                        if (vnos.get(i) == help[i]) {
                            pravilnihMest[0]++;
                            vnos.set(i, 0);
                            help[i] = 0;
                        }
                    }
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (help[i] != 0 && help[i] == vnos.get(j)) {
                                pravilnihBaru[0]++;
                                help[i] = 0;
                                vnos.set(j, 0);
                            }
                        }
                    }
                    for (int i = 0; i < pravilnihMest[0]; i++) {
                        Krog nov = new Krog(400 + i*75, cY, Color.BLACK);
                        prikaz.add(nov);
                    }
                    for (int i = 0; i < pravilnihBaru[0]; i++) {
                        Krog nov = new Krog(400 + (i + pravilnihMest[0])*75, cY, Color.WHITE);
                        prikaz.add(nov);
                    }
                    panel.repaint();
                }
            }
        });
        north.add(button2);

        JButton button3 = new JButton("Zelena"); // dodamo gumb
        button3.setPreferredSize(new Dimension(96, 40));
        button3.setBackground(Color.GREEN);
        button3.setForeground(Color.BLACK);
        button3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajKrog(Color.GREEN);
                panel.repaint();
                vnos.add(4);

                int[] help = new int[4];

                for (int i = 0; i < 4; i++) {
                    help[i] = barve[i];
                }

                if (vnos.size() >= 4) {
                    for (int i = 0; i < 4; i++) {
                        if (vnos.get(i) == help[i]) {
                            pravilnihMest[0]++;
                            vnos.set(i, 0);
                            help[i] = 0;
                        }
                    }
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (help[i] != 0 && help[i] == vnos.get(j)) {
                                pravilnihBaru[0]++;
                                help[i] = 0;
                                vnos.set(j, 0);
                            }
                        }
                    }
                    for (int i = 0; i < pravilnihMest[0]; i++) {
                        Krog nov = new Krog(400 + i*75, cY, Color.BLACK);
                        prikaz.add(nov);
                    }
                    for (int i = 0; i < pravilnihBaru[0]; i++) {
                        Krog nov = new Krog(400 + (i + pravilnihMest[0])*75, cY, Color.WHITE);
                        prikaz.add(nov);
                    }
                    panel.repaint();
                }
            }
        });
        north.add(button3);

        JButton button4 = new JButton("Vijolična"); // dodamo gumb
        button4.setPreferredSize(new Dimension(96, 40));
        button4.setBackground(PURPLE);
        button4.setForeground(Color.WHITE);
        button4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajKrog(PURPLE);
                panel.repaint();
                vnos.add(5);

                int[] help = new int[4];

                for (int i = 0; i < 4; i++) {
                    help[i] = barve[i];
                }

                if (vnos.size() >= 4) {
                    for (int i = 0; i < 4; i++) {
                        if (vnos.get(i) == help[i]) {
                            pravilnihMest[0]++;
                            vnos.set(i, 0);
                            help[i] = 0;
                        }
                    }
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (help[i] != 0 && help[i] == vnos.get(j)) {
                                pravilnihBaru[0]++;
                                help[i] = 0;
                                vnos.set(j, 0);
                            }
                        }
                    }
                    for (int i = 0; i < pravilnihMest[0]; i++) {
                        Krog nov = new Krog(400 + i*75, cY, Color.BLACK);
                        prikaz.add(nov);
                    }
                    for (int i = 0; i < pravilnihBaru[0]; i++) {
                        Krog nov = new Krog(400 + (i + pravilnihMest[0])*75, cY, Color.WHITE);
                        prikaz.add(nov);
                    }
                    panel.repaint();
                }
            }
        });
        north.add(button4);

        JButton button5 = new JButton("Oranžna"); // dodamo gumb
        button5.setPreferredSize(new Dimension(96, 40));
        button5.setBackground(Color.ORANGE);
        button5.setForeground(Color.WHITE);
        button5.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dodajKrog(Color.ORANGE);
                panel.repaint();
                vnos.add(6);

                int[] help = new int[4];

                for (int i = 0; i < 4; i++) {
                    help[i] = barve[i];
                }

                if (vnos.size() >= 4) {
                    for (int i = 0; i < 4; i++) {
                        if (vnos.get(i) == help[i]) {
                            pravilnihMest[0]++;
                            vnos.set(i, 0);
                            help[i] = 0;
                        }
                    }
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < 4; j++) {
                            if (help[i] != 0 && help[i] == vnos.get(j)) {
                                pravilnihBaru[0]++;
                                help[i] = 0;
                                vnos.set(j, 0);
                            }
                        }
                    }
                    for (int i = 0; i < pravilnihMest[0]; i++) {
                        Krog nov = new Krog(400 + i*75, cY, Color.BLACK);
                        prikaz.add(nov);
                    }
                    for (int i = 0; i < pravilnihBaru[0]; i++) {
                        Krog nov = new Krog(400 + (i + pravilnihMest[0])*75, cY, Color.WHITE);
                        prikaz.add(nov);
                    }
                    panel.repaint();
                }
            }
        });
        north.add(button5);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        System.out.println(Arrays.toString(barve));
    }
}
