package main.java.views;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.*;
import java.awt.event.*;

public class TrelloMockup extends JFrame {

//	private JPanel contentPane;
//
//	/**
//	 * Launch the application.
//	 */
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					TrelloMockup frame = new TrelloMockup();
//					frame.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
//
//	/**
//	 * Create the frame.
//	 */
//	public TrelloMockup() {
//		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setBounds(100, 100, 450, 300);
//		contentPane = new JPanel();
//		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//
//		setContentPane(contentPane);
//	}
	
	private DefaultListModel<String> toDoModel;
    private DefaultListModel<String> doingModel;
    private DefaultListModel<String> doneModel;

    public TrelloMockup() {
        setTitle("Trello Mockup");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(1, 3)); // Layout for 3 columns

        // Creating the "To Do" column
        toDoModel = new DefaultListModel<>();
        toDoModel.addElement("Task name 1");
        toDoModel.addElement("Task name 2");
        JList<String> toDoList = new JList<>(toDoModel);
        initDnD(toDoList);
        add(new JScrollPane(toDoList));

        // Creating the "Doing" column
        doingModel = new DefaultListModel<>();
        JList<String> doingList = new JList<>(doingModel);
        initDnD(doingList);
        add(new JScrollPane(doingList));

        // Creating the "Done" column
        doneModel = new DefaultListModel<>();
        JList<String> doneList = new JList<>(doneModel);
        initDnD(doneList);
        add(new JScrollPane(doneList));

        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void initDnD(JList<String> list) {
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        list.setDragEnabled(true);
        list.setDropMode(DropMode.INSERT);
        list.setTransferHandler(new ListTransferHandler());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new TrelloMockup());
    }

    private class ListTransferHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return MOVE;
        }

        @Override
        protected Transferable createTransferable(JComponent source) {
            @SuppressWarnings("unchecked")
            JList<String> sourceList = (JList<String>) source;
            String data = sourceList.getSelectedValue();
            return new StringSelection(data);
        }

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(DataFlavor.stringFlavor);
        }

        @Override
        public boolean importData(TransferSupport support) {
            if (!canImport(support)) return false;

            try {
                String data = (String) support.getTransferable().getTransferData(DataFlavor.stringFlavor);
                @SuppressWarnings("unchecked")
                JList<String> targetList = (JList<String>) support.getComponent();
                DefaultListModel<String> listModel = (DefaultListModel<String>) targetList.getModel();
                int index = targetList.getDropLocation().getIndex();
                if (index == -1) index = listModel.getSize();
                listModel.add(index, data);
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
            return false;
        }

        @Override
        protected void exportDone(JComponent source, Transferable data, int action) {
            @SuppressWarnings("unchecked")
            JList<String> sourceList = (JList<String>) source;
            if (action == MOVE) {
                ((JFrame) sourceList.getModel()).remove(sourceList.getSelectedIndex());
            }
        }
    }

}
