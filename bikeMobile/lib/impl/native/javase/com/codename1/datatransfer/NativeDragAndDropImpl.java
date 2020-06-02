package com.codename1.datatransfer;

import com.codename1.io.FileSystemStorage;
import com.codename1.ui.Display;
import java.awt.Component;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.Frame;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.dnd.DnDConstants;
import java.awt.dnd.DropTargetAdapter;
import java.awt.dnd.DropTargetDropEvent;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URLConnection;
import java.nio.file.Files;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class NativeDragAndDropImpl implements com.codename1.datatransfer.NativeDragAndDrop{
    
    java.awt.dnd.DropTarget dropTarget;
    
    private Component findCanvas(Component c, Set<Component> checked) {
        if (checked.contains(c)) {
            return null;
        }
        checked.add(c);
        if (c.getClass().getSimpleName().equals("C")) {
            return c;
        }
        if (c instanceof JComponent) {
                //int count = ((Container)c).getComponentCount();
                //for (int i=0; i<count; i++) {
                JComponent jc = (JComponent)c;
                Component[] children = jc.getComponents();
                for (Component child : children){
                    Component found = findCanvas(child, checked);
                    if (found != null) {
                        return found;
                    }
                }
            
            
        }
        return null;
        
    }
    
    private Component findCanvas(Component c) {
        return findCanvas(c, new HashSet<Component>());
    }
    
    public void startGlobalDropListener() {
        EventQueue.invokeLater(new Runnable() {
           public void run() {
               if (dropTarget != null) {
                   dropTarget.setActive(true);
               }
               Frame[] frames = Frame.getFrames();
                JPanel canvas = null;
                for (Frame frame : frames) {
                    if (frame instanceof JFrame) {
                        JFrame jframe = (JFrame)frame;
                        canvas = (JPanel)findCanvas(jframe.getContentPane());
                        if (canvas != null) {
                            break;
                        }
                    }
                }
                final JPanel fcanvas = canvas;
                dropTarget = new java.awt.dnd.DropTarget(canvas, new DropTargetAdapter() {

                   public void drop(DropTargetDropEvent dtde) {
                        if ( dtde.isDataFlavorSupported(DataFlavor.javaFileListFlavor)){
                            dtde.acceptDrop(DnDConstants.ACTION_COPY);
                            

                            try {
                                Transferable t = dtde.getTransferable();
                                //for (DataFlavor flavour : t.getTransferDataFlavors()) {
                                //    System.out.println("Possible flavor: "+flavour.getMimeType());
                                //}
                                List<File> files = (List<File>)t.getTransferData(DataFlavor.javaFileListFlavor);
                                
                                FileSystemStorage fs = FileSystemStorage.getInstance();
                                String tmpPath = fs.getAppHomePath();
                                char sep = fs.getFileSystemSeparator();
                                if (tmpPath.charAt(tmpPath.length()-1) != sep) {
                                    tmpPath += sep;
                                }
                                String tmpDir = tmpPath + "dndTmp";
                                if (!fs.exists(tmpDir)) {
                                    fs.mkdir(tmpDir);
                                }
                                
                                long now = System.currentTimeMillis();
                                // Let's clean out the tmpDir
                                for (String tmpFile : fs.listFiles(tmpDir)) {
                                    if (fs.getLastModified(tmpDir + sep + tmpFile) < now - 60000) {
                                        fs.delete(tmpDir + sep + tmpFile);
                                    }
                                }
                                
                                if (!files.isEmpty()) {
                                    File firstFile = files.get(0);
                                    
                                    if (firstFile != null && firstFile.exists()) {
                                        String mimetype = Files.probeContentType(firstFile.toPath());
                                        
                                        if (mimetype == null) {
                                            mimetype= URLConnection.guessContentTypeFromName(firstFile.getName());
                                        }
                                        if (mimetype == null) {
                                            InputStream is = null;
                                            try {
                                                is = new BufferedInputStream(new FileInputStream(firstFile));
                                                mimetype = URLConnection.guessContentTypeFromStream(is);
                                            } catch (Exception ex){}
                                            finally {
                                                if (is != null) {
                                                    try {
                                                        is.close();
                                                    } catch (Exception ex){}
                                                }
                                            }
                                        }
                                        if ((mimetype != null && DropTarget.isMimetypeAccepted(mimetype)) || DropTarget.isTypeAccepted(Display.GALLERY_ALL)) {
                                            //dtde.acceptDrop(DnDConstants.ACTION_COPY);
                                        
                                        } else {
                                            return;
                                        }
                                        tmpPath = tmpDir + sep + firstFile.getName();
                                        FileInputStream fis = null;
                                        OutputStream fos = null;
                                        try {
                                            fis = new FileInputStream(firstFile);
                                            fos = fs.openOutputStream(tmpPath);
                                            com.codename1.io.Util.copy(fis, fos);
                                        } finally {
                                            if (fis != null) {
                                                try {
                                                    fis.close();
                                                } catch (Exception ex){}
                                            }
                                            if (fos != null) {
                                                try {
                                                    fos.close();
                                                } catch (Exception ex){}
                                            }
                                        }
                                        dtde.dropComplete(true);
                                        double scale = fcanvas.getWidth() / (double)Display.getInstance().getDisplayWidth();
                                        com.codename1.datatransfer.DropTarget.fireDropEvent((int)(dtde.getLocation().x/scale), (int)(dtde.getLocation().y/scale), tmpPath);
                                    }
                                }


                            } catch (UnsupportedFlavorException ex) {
                                //Logger.getLogger(FileDropFrameController.class.getName()).log(Level.SEVERE, null, ex);
                                throw new RuntimeException(ex);
                            } catch (IOException ex) {
                                throw new RuntimeException(ex);
                            }

                        }
                       
                       
                   }
                    
                });
           } 
        });
        
    }

    public void stopGlobalDropListener() {
        if (dropTarget != null) {
            dropTarget.setActive(false);
        }
    }

    public boolean isSupported() {
        return true;
    }

}
