/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Commands;

import editor.Pane;
import javax.swing.event.UndoableEditEvent;
import javax.swing.event.UndoableEditListener;
import javax.swing.undo.CannotRedoException;
import javax.swing.undo.UndoManager;

/**
 *
 * @author muham
 */
public class UndoAction implements Command{
    
    UndoManager undoManager;

    public UndoAction(Pane textPane) {
        undoManager = new UndoManager();
          textPane.getDocument().addUndoableEditListener(
                new UndoableEditListener() {
            @Override
            public void undoableEditHappened(UndoableEditEvent e) {
                undoManager.addEdit(e.getEdit());

            }
        });
        
        
    }
  

    @Override
    public boolean execute() {
    
      try {
            undoManager.undo();
            return true;
        } catch (CannotRedoException cre) {
            cre.printStackTrace();
        }
        return false;
    }
    
    
}
