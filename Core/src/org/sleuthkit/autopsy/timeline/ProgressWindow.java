/*
 * Autopsy Forensic Browser
 *
 * Copyright 2013 Basis Technology Corp.
 * Contact: carrier <at> sleuthkit <dot> org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.sleuthkit.autopsy.timeline;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.annotation.concurrent.Immutable;
import javax.swing.AbstractAction;
import javax.swing.ActionMap;
import javax.swing.InputMap;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import org.openide.util.NbBundle;
import org.openide.windows.WindowManager;
import org.sleuthkit.autopsy.coreutils.ThreadConfined;

/**
 * Dialog with progress bar that pops up when timeline is being generated
 */
public class ProgressWindow extends JFrame {

    private final SwingWorker<?, ?> worker;

    /**
     * Creates new form TimelineProgressDialog
     */
    @NbBundle.Messages({"Timeline.progressWindow.name=Timeline",
        "Timeline.progressWindow.title=Generating Timeline data"})
    public ProgressWindow(Component parent, boolean modal, SwingWorker<?, ?> worker) {
        super();
        initComponents();

        setLocationRelativeTo(parent);

        setAlwaysOnTop(modal);

        //set icon the same as main app
        SwingUtilities.invokeLater(() -> {
            setIconImage(WindowManager.getDefault().getMainWindow().getIconImage());
        });

        setName(Bundle.Timeline_progressWindow_name());
        setTitle(Bundle.Timeline_progressWindow_title());
        // Close the dialog when Esc is pressed
        String cancelName = "cancel"; // NON-NLS
        InputMap inputMap = getRootPane().getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT);

        inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_ESCAPE, 0), cancelName);
        ActionMap actionMap = getRootPane().getActionMap();

        actionMap.put(cancelName, new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cancel();
            }
        });
        this.worker = worker;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        progressBar = new javax.swing.JProgressBar();
        progressHeader = new javax.swing.JLabel();

        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                closeDialog(evt);
            }
        });

        org.openide.awt.Mnemonics.setLocalizedText(progressHeader, NbBundle.getMessage(ProgressWindow.class, "ProgressWindow.progressHeader.text")); // NOI18N
        progressHeader.setMinimumSize(new java.awt.Dimension(10, 14));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(progressBar, javax.swing.GroupLayout.DEFAULT_SIZE, 504, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(progressHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(progressHeader, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(progressBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Closes the dialog
     */
    private void closeDialog(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_closeDialog
        cancel();
    }//GEN-LAST:event_closeDialog

    @NbBundle.Messages({"Timeline.ProgressWindow.cancel.confdlg.msg=Do you want to cancel timeline creation?",
        "Timeline.ProgressWindow.cancel.confdlg.detail=Cancel timeline creation?"})
    public void cancel() {
        SwingUtilities.invokeLater(() -> {
            if (isVisible()) {
                int showConfirmDialog = JOptionPane.showConfirmDialog(ProgressWindow.this,
                        Bundle.Timeline_ProgressWindow_cancel_confdlg_msg(),
                        Bundle.Timeline_ProgressWindow_cancel_confdlg_detail(),
                        JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
                if (showConfirmDialog == JOptionPane.YES_OPTION) {
                    close();
                }
            } else {
                close();
            }
        });
    }

    public void close() {
        worker.cancel(false);
        setVisible(false);
        dispose();
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JProgressBar progressBar;
    private javax.swing.JLabel progressHeader;
    // End of variables declaration//GEN-END:variables

    @ThreadConfined(type = ThreadConfined.ThreadType.AWT)
    public void update(ProgressUpdate chunk) {
        progressHeader.setText(chunk.getHeaderMessage());
        if (chunk.getTotal() >= 0) {
            progressBar.setIndeterminate(false);
            progressBar.setMaximum(chunk.getTotal());
            progressBar.setStringPainted(true);
            progressBar.setValue(chunk.getProgress());
            progressBar.setString(chunk.getDetailMessage());
        } else {
            progressBar.setIndeterminate(true);
            progressBar.setStringPainted(true);
            progressBar.setString(chunk.getDetailMessage());
        }
    }

    /**
     * bundles up progress information to be shown in the progress dialog
     */
    @Immutable
    public static class ProgressUpdate {

        private final int progress;
        private final int total;
        private final String headerMessage;
        private final String detailMessage;

        public int getProgress() {
            return progress;
        }

        public int getTotal() {
            return total;
        }

        public String getHeaderMessage() {
            return headerMessage;
        }

        public String getDetailMessage() {
            return detailMessage;
        }

        public ProgressUpdate(int progress, int total, String headerMessage, String detailMessage) {
            this.progress = progress;
            this.total = total;
            this.headerMessage = headerMessage;
            this.detailMessage = detailMessage;
        }

        public ProgressUpdate(int progress, int total, String headerMessage) {
            this(progress, total, headerMessage, "");
        }
    }
}
