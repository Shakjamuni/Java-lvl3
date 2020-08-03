package misc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class LinkRotator extends JFrame implements Runnable, ActionListener {
    String[] pageTitle = new String[6];
    URI[] pageLink = new URI[6];
    int current = 0;
    Thread runner;
    JLabel siteLabel = new JLabel();

    public LinkRotator() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 100);
        FlowLayout flo = new FlowLayout();
        setLayout(flo);
        add(siteLabel);

        pageTitle = new String[]{
                "Oracle site", "Comm Server Side", "Comm Java World", "24 hours Java", "Sams Publ", "Keidenhead Roger"
        };
        pageLink[0] = getURI(" http://www.oracle.com");
        pageLink[1] = getURI(" http://www.oracle.com");
        pageLink[2] = getURI(" http://www.oracle.com");
        pageLink[3] = getURI(" http://www.oracle.com");
        pageLink[4] = getURI(" http://www.oracle.com");
        pageLink[5] = getURI(" http://www.oracle.com");

        Button visitButton = new Button("Visit site");
        visitButton.addActionListener(this);
        add(visitButton);
        setVisible(true);
        start();
    }

    private URI getURI(String urlTexT) {
        URI pageURI = null;
        try {
            pageURI = new URI(urlTexT);
        } catch (URISyntaxException ex) {

        }
        return pageURI;
    }

    public void start() {
        if (runner == null) {
            runner = new Thread(this);
            runner.start();
        }
    }

    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        Desktop desktop = Desktop.getDesktop();
        if(pageLink[current] != null) {
            try {
                desktop.browse(pageLink[current]);
                runner = null;
                System.exit(0);
            } catch (IOException exc) {

            }
        }
    }

    @Override
    public void run() {
        Thread thisThread = Thread.currentThread();
        while (runner == thisThread) {
            current++;
            if (current > 5) {
                current = 0;
            }
            siteLabel.setText(pageTitle[current]);
            repaint();
            try {
                Thread.sleep(2000);
            } catch (InterruptedException exc) {

            }
        }
    }

    public static void main(String[] args) {
        new LinkRotator();
    }
}
