package main.java.views.DragDrop;

import javax.swing.*;

import main.java.controllers.TaskController;
import main.java.dao.DAO;
import main.java.models.Project;
import main.java.models.Task;
import main.java.views.NavigationListener;
import main.java.views.components.dashboard.TaskOverviewPanel;

import java.awt.Point;
import java.awt.Rectangle;
import java.awt.datatransfer.*;
import java.awt.dnd.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class DragDropHandler {

    public static void setupDrag(JComponent component) {
        component.setTransferHandler(new ValueExportTransferHandler());
        component.addMouseListener((MouseListener) new DragMouseAdapter());
    }

    public static void setupDrop(JComponent container, String statusNew, Project project, 
    		NavigationListener navigationListener) {
        new DropTarget(container, new DropTargetAdapter() {
        	@Override
            public void drop(DropTargetDropEvent dtde) {
                try {
                	Transferable tr = dtde.getTransferable();
                    DataFlavor componentFlavor = new DataFlavor(JComponent.class, "JComponent");
                    if (dtde.isDataFlavorSupported(componentFlavor)) {
                        dtde.acceptDrop(DnDConstants.ACTION_MOVE);
                        TaskOverviewPanel component = (TaskOverviewPanel) tr.getTransferData(componentFlavor);
                        DragDropHandler.setupDrag(component);
                        
                        String taskID = component.getTaskID();

                        // Determine drop position and add panel
                        Point dropPoint = dtde.getLocation();
                        int index = getInsertionIndex(container, dropPoint) - 1;

                        TaskController taskController = new TaskController();
                        for (Task task : taskController.getListOfTask()) {
                    		if (task.getId().equalsIgnoreCase(taskID)) {
                    			task.setStatus(statusNew);
                    			task.setPosition(index);
                    		}
                    	}
                        DAO.saveData(taskController.getListOfTask(), DAO.TASK_FILE_PATH, taskController.taskIDCounter);
                        
                    	if (navigationListener != null) {
                    		navigationListener.onDashboardSelected(project);
                    	}
                        
                        dtde.dropComplete(true);
                    } else {
                        dtde.rejectDrop();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            private int getInsertionIndex(JComponent container, Point dropPoint) {
                for (int i = 0; i < container.getComponentCount(); i++) {
                    Rectangle bounds = container.getComponent(i).getBounds();
                    if (dropPoint.y < bounds.y + bounds.height / 2) {
                        return i;
                    }
                }
                return container.getComponentCount();
            }
        });
    }

    private static class ComponentTransferable implements Transferable {
        private JComponent component;

        public ComponentTransferable(JComponent component) {
            this.component = component;
        }

        @Override
        public DataFlavor[] getTransferDataFlavors() {
            return new DataFlavor[]{new DataFlavor(JComponent.class, "JComponent")};
        }

        @Override
        public boolean isDataFlavorSupported(DataFlavor flavor) {
            return flavor.getRepresentationClass() == JComponent.class;
        }

        @Override
        public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException {
            if (!isDataFlavorSupported(flavor)) {
                throw new UnsupportedFlavorException(flavor);
            }
            return component;
        }
    }


    private static class ValueExportTransferHandler extends TransferHandler {
        @Override
        protected Transferable createTransferable(JComponent c) {
            return new ComponentTransferable(c);
        }

        @Override
        public int getSourceActions(JComponent c) {
            return MOVE;
        }
    }


    private static class DragMouseAdapter extends MouseAdapter {
        public void mousePressed(MouseEvent e) {
            JComponent c = (JComponent) e.getSource();
            TransferHandler handler = c.getTransferHandler();
            handler.exportAsDrag(c, e, TransferHandler.MOVE);
        }
    }
}

