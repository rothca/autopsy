/*
 * Autopsy Forensic Browser
 *
 * Copyright 2014 Basis Technology Corp.
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
package org.sleuthkit.autopsy.ingest;

/**
 * A UI panel that allows a user to make data source ingest cancellation
 * requests.
 */
final class DataSourceIngestCancellationPanel extends javax.swing.JPanel {

    private boolean cancelAllIngestModules;

    /**
     * Constructs an instance of the panel.
     */
    DataSourceIngestCancellationPanel() {
        initComponents();
        this.cancelCurrentModuleRadioButton.setSelected(true);
    }

    /**
     * Queries whether the user wants to cancel the ingest job or just the
     * currently executing data source ingest module.
     *
     * @return True if the ingest job is to be canceled, false if only the
     *         current module is to be canceled.
     */
    boolean cancelAllDataSourceIngestModules() {
        return this.cancelAllIngestModules;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelRadioButtonsGroup = new javax.swing.ButtonGroup();
        cancelCurrentModuleRadioButton = new javax.swing.JRadioButton();
        cancelAllModulesRadioButton = new javax.swing.JRadioButton();

        cancelRadioButtonsGroup.add(cancelCurrentModuleRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(cancelCurrentModuleRadioButton, org.openide.util.NbBundle.getMessage(DataSourceIngestCancellationPanel.class, "DataSourceIngestCancellationPanel.cancelCurrentModuleRadioButton.text")); // NOI18N
        cancelCurrentModuleRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelCurrentModuleRadioButtonActionPerformed(evt);
            }
        });

        cancelRadioButtonsGroup.add(cancelAllModulesRadioButton);
        org.openide.awt.Mnemonics.setLocalizedText(cancelAllModulesRadioButton, org.openide.util.NbBundle.getMessage(DataSourceIngestCancellationPanel.class, "DataSourceIngestCancellationPanel.cancelAllModulesRadioButton.text")); // NOI18N
        cancelAllModulesRadioButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelAllModulesRadioButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(cancelAllModulesRadioButton)
                    .addComponent(cancelCurrentModuleRadioButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(cancelCurrentModuleRadioButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(cancelAllModulesRadioButton)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void cancelCurrentModuleRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelCurrentModuleRadioButtonActionPerformed
        this.cancelAllIngestModules = this.cancelAllModulesRadioButton.isEnabled();
    }//GEN-LAST:event_cancelCurrentModuleRadioButtonActionPerformed

    private void cancelAllModulesRadioButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelAllModulesRadioButtonActionPerformed
        this.cancelAllIngestModules = this.cancelAllModulesRadioButton.isEnabled();
    }//GEN-LAST:event_cancelAllModulesRadioButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JRadioButton cancelAllModulesRadioButton;
    private javax.swing.JRadioButton cancelCurrentModuleRadioButton;
    private javax.swing.ButtonGroup cancelRadioButtonsGroup;
    // End of variables declaration//GEN-END:variables
}
