package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.concurrent.TimeUnit;


public class Super_GUI
{
    private JPanel super_java_GUI;
    private JPanel fensterleiste;
    private JButton gelberButton;
    private JButton gruenerButton;
    private JButton roterButton;
    private JPanel menuleiste;
    private JLabel windowIcon;
    private JRadioButton appleButton;
    private JPanel seitenLeiste;
    private JLabel appleIcon;
    private JRadioButton windowButton;
    private JRadioButton otherButton;
    private JButton infoButton;
    private JButton gruen2Button;
    private JButton gelb2Button;
    private JButton rot2Button;
    private JButton buttonAnleitung;
    private JButton buttonProgrammStart;
    private JButton buttonHinweis;
    private JCheckBox sperrenCheckBox;
    private JButton hPosL;
    private JTextField hPosTextfield;
    private JButton hPosR;
    private JButton vPosL;
    private JButton vPosR;
    private JButton buttonHalt;
    private JTextField vPosTextfield;
    private JTextField hSizeTextfield;
    private JTextField vSizeTextfield;
    private JButton hSizeL;
    private JButton hSizeR;
    private JButton vSizeL;
    private JButton vSizeR;
    private JButton buttonAktualisieren;
    private int xx = 0;
    private int yy = 0;
    private String select = "apple";
    private boolean maximierer = false;
    private static JFrame frame = new JFrame("Tote Pixel können wiederbelebt werden!");
    private static JFrame pixelKiller = new JFrame("");
    private static JPanel paneel = new JPanel();
    private volatile boolean threadRunning = false;
    private int hPos = 0;
    private int vPos = 0;
    private int hSize = 50;
    private int vSize = 50;
    private boolean sperre = false;

    //Startet das Fenster und macht es sichtbar
    public static void main(String[] args)
    {
        frame.setContentPane(new Super_GUI().super_java_GUI);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setUndecorated(true);
        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setVisible(true);

        paneel.setBackground(Color.lightGray);
        pixelKiller.add(paneel);
        pixelKiller.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        pixelKiller.setResizable(true);
        pixelKiller.setUndecorated(true);
        pixelKiller.setSize(50, 50);
        pixelKiller.setAlwaysOnTop(true);
        pixelKiller.setVisible(true);
    }

    //Selektiert das zuletzt verwendete Design
    private void selektor ()
    {
        if (select.equals("apple")) appleButton.setSelected(true);
        else if (select.equals("window")) windowButton.setSelected(true);
        else if (select.equals("other")) otherButton.setSelected(true);
    }

    private void startThread()
    {
        Thread t = new Thread(() -> {

            threadRunning = true;
            byte zahl = 0;

            while(threadRunning)
            {
                switch (zahl)
                {
                    case 0:
                        paneel.setBackground(Color.green);
                        zahl += 1;
                        break;

                    case 1 :
                        paneel.setBackground(Color.blue);
                        zahl += 1;
                        break;

                    case 2:
                        paneel.setBackground(Color.red);
                        zahl -= 2;
                        break;
                }
                try
                {
                    Thread.sleep(16);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                Toolkit.getDefaultToolkit().sync();
            }
        });
        t.start();
    }

    private void paneelHorizontalPos (boolean position)
    {
        if (!position)
        {
            //Zähle runter
            hPos = Integer.parseInt(hPosTextfield.getText());
            hPos -= 1;
            if (hPos < 0) hPos = 0;
            hPosTextfield.setText("" + hPos);
            pixelKiller.setLocation(hPos, vPos);
        }
        if (position)
        {
            //Zähle hoch
            hPos = Integer.parseInt(hPosTextfield.getText());
            hPos += 1;
            hPosTextfield.setText("" + hPos);
            pixelKiller.setLocation(hPos, vPos);
        }
    }

    private void paneelVertikalPos (boolean position)
    {
        if (!position)
        {
            //Zähle runter
            vPos = Integer.parseInt(vPosTextfield.getText());
            vPos -= 1;
            if (vPos < 0) vPos = 0;
            vPosTextfield.setText("" + vPos);
            pixelKiller.setLocation(hPos, vPos);
        }
        if (position)
        {
            //Zähle hoch
            vPos = Integer.parseInt(vPosTextfield.getText());
            vPos += 1;
            vPosTextfield.setText("" + vPos);
            pixelKiller.setLocation(hPos, vPos);
        }
    }

    private void paneelHorizontalSize (boolean size)
    {
        if (!size)
        {
            //Verringere Größe
            hSize = Integer.parseInt(hSizeTextfield.getText());
            hSize -= 1;
            hSizeTextfield.setText("" + hSize);
            pixelKiller.setSize(hSize, vSize);
        }
        if (size)
        {
            //Vergrößere Größe
            hSize = Integer.parseInt(hSizeTextfield.getText());
            hSize += 1;
            hSizeTextfield.setText("" + hSize);
            pixelKiller.setSize(hSize, vSize);
        }
    }

    private void paneelVertikalSize (boolean size)
    {
        if (!size)
        {
            //Verringere Größe
            vSize = Integer.parseInt(vSizeTextfield.getText());
            vSize -= 1;
            if (vSize <= 0) vSize = 1;
            vSizeTextfield.setText("" + vSize);
            pixelKiller.setSize(hSize, vSize);
        }
        if (size)
        {
            //Vergrößere Größe
            vSize = Integer.parseInt(vSizeTextfield.getText());
            vSize += 1;
            vSizeTextfield.setText("" + vSize);
            pixelKiller.setSize(hSize, vSize);
        }
    }

    private void sperreInhalt ()
    {
        if(sperrenCheckBox.isSelected())
        {
            hSizeR.setEnabled(false);
            hSizeL.setEnabled(false);
            hPosL.setEnabled(false);
            hPosR.setEnabled(false);
            vSizeL.setEnabled(false);
            vSizeR.setEnabled(false);
            vPosL.setEnabled(false);
            vPosR.setEnabled(false);
            hPosTextfield.setEnabled(false);
            hSizeTextfield.setEnabled(false);
            vPosTextfield.setEnabled(false);
            vSizeTextfield.setEnabled(false);
        }
        if (!sperrenCheckBox.isSelected())
        {
            hSizeR.setEnabled(true);
            hSizeL.setEnabled(true);
            hPosL.setEnabled(true);
            hPosR.setEnabled(true);
            vSizeL.setEnabled(true);
            vSizeR.setEnabled(true);
            vPosL.setEnabled(true);
            vPosR.setEnabled(true);
            hPosTextfield.setEnabled(true);
            hSizeTextfield.setEnabled(true);
            vPosTextfield.setEnabled(true);
            vSizeTextfield.setEnabled(true);
        }
    }

    private Super_GUI() {

        buttonProgrammStart.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, "Achtung! Das Programm wurde gestartet, blicken Sie niemals direkt in das Feld!");
            startThread();
        });

        buttonHalt.addActionListener(e -> threadRunning = false);

        buttonAktualisieren.addActionListener(e ->
        {
            if (!sperrenCheckBox.isSelected())
            {
                paneelHorizontalPos(false);
                paneelHorizontalPos(true);
                paneelVertikalPos(false);
                paneelVertikalPos(true);
                paneelHorizontalSize(false);
                paneelHorizontalSize(true);
                paneelVertikalSize(false);
                paneelVertikalSize(true);
            }
        });

        hPosL.addActionListener(e -> paneelHorizontalPos(false));

        hPosR.addActionListener(e -> paneelHorizontalPos(true));

        vPosL.addActionListener(e -> paneelVertikalPos(false));

        vPosR.addActionListener(e -> paneelVertikalPos(true));

        hSizeL.addActionListener(e -> paneelHorizontalSize(false));

        hSizeR.addActionListener(e -> paneelHorizontalSize(true));

        vSizeL.addActionListener(e -> paneelVertikalSize(false));

        vSizeR.addActionListener(e -> paneelVertikalSize(true));

        sperrenCheckBox.addActionListener(e -> sperreInhalt());

        buttonAnleitung.addActionListener(e ->
        {
            JOptionPane.showMessageDialog(frame, "Guten Tag, mit diesem Programm können Sie eventuel einen toten Pixel \n" +
                    "ihres Displays wiederbeleben. Wenn ein Pixel des Displays nur noch eine bestimmte Farbe anzeigt kann dieser \n" +
                    "eventuel repariert werden, leuchtet keiner der Subpixel mehr, dann wird es wahrscheinlich nicht funktionieren. \n \n" +
                    "Oben links erscheint ein graues Feld, dieses müssen sie über dem toten Pixel platzieren, z.B. per Drag 'n Drop \n" +
                    "oder über die Tasten, mit der sich die horizontale und vertikale Position anpassen läßt. \n \n" +
                    "Passen sie nun die größe des Feldes über die Buttons an, so das es etwas größer ist als der tote Pixel selbst. \n \n" +
                    "Sie können die ganzen Werte auch über die Eingabefelder manuell eingeben, drücken sie danach auf den aktualisieren Knopf. \n \n" +
                    "Drücken sie nun auf den Sperren Knopf, um zu verhindern, dass sich das Feld nun versehentlich bewegt. \n \n" +
                    "Drücken sie nun auf Start und lassen es für ca. 1 Stunde laufen, sollte der Pixel dann nicht wieder funktionieren \n" +
                    "ist er wohl nicht mehr zu retten. Mit Halt können sie die Heilung unterbrechen.\n \n" +
                    "Blicken Sie niemals direkt in das blinkende Feld!!!!");
        });

        buttonHinweis.addActionListener(e ->
        {
            JOptionPane.showMessageDialog(frame, "Achtung! Dieses Programm kann bei bestimmten Personen zu epileptischen \n" +
                    "Anfällen führen. Blicken Sie niemals direkt in das Feld!");
        });

        //Macht das Pixelfeld beweglich
        paneel.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                xx = e.getX();
                yy = e.getY();
            }
        });

        paneel.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                if (!sperrenCheckBox.isSelected()) {
                    pixelKiller.setLocation(pixelKiller.getLocation().x + e.getX() - xx, pixelKiller.getLocation().y + e.getY() - yy);
                    hPosTextfield.setText("" + (pixelKiller.getLocation().x + e.getX() - xx));
                    hPos = pixelKiller.getLocation().x + e.getX() - xx;
                    vPosTextfield.setText("" + (pixelKiller.getLocation().y + e.getY() - yy));
                    vPos = pixelKiller.getLocation().y + e.getY() - yy;
                }
            }
        });

        //----Die weiteren Buttons sind nur für die Navigationstasten und für die Funktion des Programms nicht relevant ------------------------------------------------------------------
        //Minimiert das Programmfenster
        gruenerButton.addActionListener(e -> frame.setState(Frame.ICONIFIED));

        //Maximiert das Programmfenster
        gelberButton.addActionListener(e ->
        {
            //Aktivieren wenn keine Maximierung gewünscht ist
            JOptionPane.showMessageDialog(frame, "Die Maximierung wurde deaktiviert!");
        });

        //Beendet das Programm
        roterButton.addActionListener(e -> System.exit(0));

        //Die Buttons machen das gleiche wie die oberen, sind aber im Apple Look nicht sichtbar
        gruen2Button.addActionListener(e -> frame.setState(Frame.ICONIFIED));

        gelb2Button.addActionListener(e ->
        {
            //Aktivieren wenn keine Maximierung gewünscht ist
            JOptionPane.showMessageDialog(frame, "Die Maximierung wurde deaktiviert!");
        });

        rot2Button.addActionListener(e -> System.exit(0));

        fensterleiste.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent e) {
                super.mousePressed(e);
                xx = e.getX();
                yy = e.getY();
            }
        });

        fensterleiste.addMouseMotionListener(new MouseMotionAdapter() {
            @Override
            public void mouseDragged(MouseEvent e) {
                super.mouseDragged(e);
                frame.setLocation(frame.getLocation().x+e.getX()-xx, frame.getLocation().y+e.getY()-yy);
            }
        });

        ButtonGroup groupie = new ButtonGroup();
        groupie.add(appleButton);
        groupie.add(windowButton);
        groupie.add(otherButton);

        //Ändert das Design in Apple Optik
        appleButton.addActionListener(e ->
        {
            frame.dispose();
            frame.setUndecorated(true);

            fensterleiste.setBackground(Color.decode("#6380C2"));
            gruenerButton.setIcon(new ImageIcon(Class.class.getResource("/img/gruen.png")));
            gruenerButton.setRolloverIcon(new ImageIcon(Class.class.getResource("/img/gruen_mouseover.png")));
            gruenerButton.setMargin(new Insets(2, 5, 2, 5));
            gruenerButton.setVisible(true);
            gruen2Button.setVisible(false);

            gelberButton.setIcon(new ImageIcon(Class.class.getResource("/img/gelb.png")));
            gelberButton.setRolloverIcon(new ImageIcon(Class.class.getResource("/img/gelb_mouseover.png")));
            gelberButton.setMargin(new Insets(2, 5, 2, 5));
            gelberButton.setVisible(true);
            gelb2Button.setVisible(false);

            roterButton.setIcon(new ImageIcon(Class.class.getResource("/img/rot.png")));
            roterButton.setRolloverIcon(new ImageIcon(Class.class.getResource("/img/rot_mouseover.png")));
            roterButton.setMargin(new Insets(2, 5, 2, 5));
            roterButton.setVisible(true);
            rot2Button.setVisible(false);

            fensterleiste.setVisible(true);
            frame.pack();
            frame.setVisible(true);
            select = "apple";
        });

        //Ändert das Design in Windows Optik
        windowButton.addActionListener(e ->
        {
            frame.dispose();
            frame.setUndecorated(true);

            fensterleiste.setBackground(Color.decode("#c2d901"));
            gruen2Button.setIcon(new ImageIcon(Class.class.getResource("/img/line.png")));
            gruen2Button.setRolloverIcon(new ImageIcon(Class.class.getResource("/img/line_rollover.png")));
            gruen2Button.setMargin(new Insets(0, 0, 0, 0));
            gruenerButton.setVisible(false);
            gruen2Button.setVisible(true);

            gelb2Button.setMargin(new Insets(0, 0, 0, 0));
            gelberButton.setVisible(false);
            if (maximierer)
            {
                gelb2Button.setIcon(new ImageIcon(Class.class.getResource("/img/square_maximized.png")));
                gelb2Button.setRolloverIcon(new ImageIcon(Class.class.getResource("/img/square_maximized_rollover.png")));
            }
            else
            {
                gelb2Button.setIcon(new ImageIcon(Class.class.getResource("/img/square.png")));
                gelb2Button.setRolloverIcon(new ImageIcon(Class.class.getResource("/img/square_rollover.png")));
            }
            gelb2Button.setVisible(true);

            rot2Button.setIcon(new ImageIcon(Class.class.getResource("/img/cross.png")));
            rot2Button.setRolloverIcon(new ImageIcon(Class.class.getResource("/img/cross_rollover.png")));
            rot2Button.setMargin(new Insets(0, 0, 0, 0));
            roterButton.setVisible(false);
            rot2Button.setVisible(true);

            fensterleiste.setVisible(true);
            frame.pack();
            frame.setVisible(true);
            select = "window";
        });

        //Ändert das Design in die Standardoptik des Betriebssystems
        otherButton.addActionListener(e ->
        {
            int dialogButton = JOptionPane.YES_NO_OPTION;
            int dialogResult = JOptionPane.showConfirmDialog (frame, " Wollen Sie sich wirklich das " +
                    "Standard Design des Systems antun? \n Besonders einige Linux Distributionen wie Ubuntu oder Elementary \n" +
                    " haben ein furchtbar grottiges Design, welches keinen Wert auf eine \n" +
                    " einfache Bedienung legt, obwohl es das bei anderen Betriebssystemen \n schon vor 23 Jahren gegeben hat.","Achtung!",dialogButton);
            if ((dialogResult == JOptionPane.YES_OPTION))
            {
                fensterleiste.setVisible(false);
                frame.dispose();
                frame.setUndecorated(false);
                frame.pack();
                frame.setVisible(true);
                select = "other";
            } else selektor();
        });

        //Gibt eine kleine Info über die Funktionen der Buttons darüber
        infoButton.addActionListener(e -> JOptionPane.showMessageDialog(frame, " Über diese Seitenleiste" +
                " kann die Fensterdekoration eingestellt\n werden, wählen Sie den Look aus, der Ihnen am Besten gefällt!"));
    }
}
