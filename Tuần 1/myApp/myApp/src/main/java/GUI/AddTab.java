package GUI;


import lombok.Getter;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class AddTab extends JPanel {
    FrameCreateOrder customTabbed;

    public AddTab(FrameCreateOrder tabbed) {
        this.customTabbed = tabbed;
        setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
        setBorder(new EmptyBorder(5, 2, 2, 2));
        setOpaque(false);
        add(addLable());
        add( addButton());
    }

    public JLabel addLable() {
        JLabel lb = new JLabel() {
            @Override
            public String getText() {
                int index = customTabbed.tabbedPane.indexOfTabComponent(AddTab.this);
                if (index != -1)
                    return "Đơn " + index;
                return null;
            }
        };
        lb.setBorder(new EmptyBorder(0, 0, 0, 10));
        return lb;
    }

    private JButton addButton() {
        JButton bt = new JButton();
        bt.setBorderPainted(false);
        bt.setContentAreaFilled(false);
        bt.setPreferredSize(new Dimension(20, 20));
        bt.setIcon(new ImageIcon("Imager\\delete_15px.png"));
        bt.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        bt.addMouseListener(new MouseListener() {

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
            }

            @Override
            public void mouseExited(MouseEvent e) {
                bt.setBorderPainted(false);
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                bt.setBorderPainted(true);

            }

            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton() == MouseEvent.BUTTON1) {
                    int i = customTabbed.tabbedPane.indexOfTabComponent(AddTab.this);
                    customTabbed.removeTab(i);
                }
            }
        });
        return bt;
    }
}

