
import goodsprice.control.DataControl;
import goodsprice.io.FileIOImpl;
import goodsprice.io.PersistenceManager;
import goodsprice.model.Good;
import goodsprice.views.IListView;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.SwingWorker;
import goodsprice.control.IDataControl;



/**
 *
 * @author Raitis
 */
public class TrialTreadPersistenceJDialog extends javax.swing.JDialog implements IListView{

    private static PersistenceManager<Object> pm;
    private static DataControl control;

    private  IDataControl vcallBack;

    /**
     * Creates new form TestTreadPersistenceJDialog
     */
    public TrialTreadPersistenceJDialog(java.awt.Frame parent, boolean modal, IDataControl vcallBack) {
        super(parent, modal);
        initComponents();
       jProgressBar1.setVisible(false);
        this.vcallBack = vcallBack;
        jLbl.setText("......");
        jLblResult.setText("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jBtnStart = new javax.swing.JButton();
        jProgressBar1 = new javax.swing.JProgressBar();
        jLbl = new javax.swing.JLabel();
        jBtnReset = new javax.swing.JButton();
        jBtnWorker = new javax.swing.JButton();
        jBtnWorker1 = new javax.swing.JButton();
        jLblResult = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        jBtnStart.setText("Start Thread");
        jBtnStart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnStartActionPerformed(evt);
            }
        });

        jProgressBar1.setIndeterminate(true);

        jLbl.setText("info");

        jBtnReset.setText("Reset");
        jBtnReset.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnResetActionPerformed(evt);
            }
        });

        jBtnWorker.setText("Swing Worker");
        jBtnWorker.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnWorkerActionPerformed(evt);
            }
        });

        jBtnWorker1.setText("Swing Worker1");
        jBtnWorker1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jBtnWorker1ActionPerformed(evt);
            }
        });

        jLblResult.setText("result");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jBtnWorker1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jBtnReset)
                                .addGap(52, 52, 52)
                                .addComponent(jBtnStart)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                                .addComponent(jBtnWorker))))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jProgressBar1, javax.swing.GroupLayout.DEFAULT_SIZE, 323, Short.MAX_VALUE)
                            .addComponent(jLbl, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLblResult, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(69, 69, 69)
                .addComponent(jProgressBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLbl)
                .addGap(18, 18, 18)
                .addComponent(jLblResult)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 68, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jBtnStart)
                    .addComponent(jBtnReset)
                    .addComponent(jBtnWorker))
                .addGap(18, 18, 18)
                .addComponent(jBtnWorker1)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnStartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnStartActionPerformed
        // TODO add your handling code here:
        
        //control.startPersistDataControlMemoryThread();
        vcallBack.startPersistDataControlMemoryThread();
        //jProgressBar1.setVisible(true);
        jLbl.setText("Start simulate");
        try {
            vcallBack.persistDataControlMemory();
        } catch (FileNotFoundException | UnsupportedEncodingException ex) {
            Logger.getLogger(TrialTreadPersistenceJDialog.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("jBtnStartActionPerformed(): simulateAccessToDataControlMemory completed...");
       //  jProgressBar1.setVisible(false);
          jLbl.setText("simulateAccessToDataControlMemory completed...");
        
    }//GEN-LAST:event_jBtnStartActionPerformed
void swingBackgroundProcessWithReturn(String txt) throws Exception{
        SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
            @Override
            protected String doInBackground() throws Exception {
               vcallBack.startPersistDataControlMemoryThread();
                jProgressBar1.setVisible(true);
                jProgressBar1.setString("Please wait...");
                jLbl.setText("Start simulate");
                jLblResult.setText("");
                Good res = vcallBack.simulateAccessToDataControlMemoryGood(txt);
                System.out.println("jBtnStartActionPerformed(): swingBackgroundProcessWithReturn completed...");
                return res.toString();
            }

           

            @Override
            protected void done() {
                try {
                    jProgressBar1.setVisible(false);
                     jLbl.setText("simulateAccessToDataControlMemory completed...");
                    jLblResult.setText(get());
                } catch (InterruptedException | ExecutionException ex) {
                    Logger.getLogger(TrialTreadPersistenceJDialog.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            
        };
        worker.execute();
}
    private void jBtnResetActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnResetActionPerformed
      jLbl.setText("......");
    }//GEN-LAST:event_jBtnResetActionPerformed

    private void jBtnWorkerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnWorkerActionPerformed
        swingBackgroundProcess();
    }//GEN-LAST:event_jBtnWorkerActionPerformed

    private void jBtnWorker1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jBtnWorker1ActionPerformed
        try {
            swingBackgroundProcessWithReturn("Hi there"); //("Exception"); 
        } catch (Exception ex) {
            System.out.println(">>>>>>>>>>>TestTreadPersistenceJDialog.jBtnWorker1ActionPerformed() catched exception");
            Logger.getLogger(TrialTreadPersistenceJDialog.class.getName()).log(Level.SEVERE, null, ex);
            
        }
    }//GEN-LAST:event_jBtnWorker1ActionPerformed

    void swingBackgroundProcess() {
        SwingWorker<Void, Void> worker = new SwingWorker<Void, Void>() {
            @Override
            protected Void doInBackground() throws Exception {

                vcallBack.startPersistDataControlMemoryThread();
                jProgressBar1.setVisible(true);
                jProgressBar1.setString("Please wait...");
                jLbl.setText("Start simulate");
                vcallBack.persistDataControlMemory();
                System.out.println("jBtnStartActionPerformed(): simulateAccessToDataControlMemory completed...");
                return null;
            }

            @Override
            protected void done() {
              
            jProgressBar1.setVisible(false);
            jLbl.setText("simulateAccessToDataControlMemory completed...");
            }
            
        };
        worker.execute();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) throws IOException, ClassNotFoundException {
      
        TrialTreadPersistenceJDialog dialog = new TrialTreadPersistenceJDialog(new javax.swing.JFrame(), true,null);
                //FileIOImpl<Good> fileIO = new FileIOImpl<>("Goods.bin");

         pm = new PersistenceManager<>();
        
        control = new DataControl(null, dialog);
        
        dialog.setCallBack(control);
        
        
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(TrialTreadPersistenceJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(TrialTreadPersistenceJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(TrialTreadPersistenceJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(TrialTreadPersistenceJDialog.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnReset;
    private javax.swing.JButton jBtnStart;
    private javax.swing.JButton jBtnWorker;
    private javax.swing.JButton jBtnWorker1;
    private javax.swing.JLabel jLbl;
    private javax.swing.JLabel jLblResult;
    private javax.swing.JProgressBar jProgressBar1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void updateListView(List<Good> goods) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void setCallBack(IDataControl calback) {
            this.vcallBack= calback;
    }
}