/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package editor;

import Commands.Button;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JMenuBar;

/**
 *
 * @author muham
 */
public class Frame extends JFrame {

   
    public Frame(Builder builder,Pane pane) throws HeadlessException {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Text Editor");
        // jMenu=new JMenuBar();
        setJMenuBar(builder.jMenu);
        setSize(800, 600);
        add(pane);
    
    }

    public void openWindow() {
        setVisible(true);
    }

    public static class Builder {

        private JMenuBar jMenu;

        public Builder() {
            jMenu = new JMenuBar();
        }

        /**
         * @param load the load to set
         * @return 
         */
        public Builder Load(Button load) {
            
            jMenu.add(load);
            return this;
        }

        /**
         * @param save the save to set
         * @return 
         */
        public Builder Save(Button save) {
            jMenu.add(save);
            return this;
        }

        /**
         * @param saveAs the saveAs to set
         * @return 
         */
        public Builder SaveAs(Button saveAs) {
            jMenu.add(saveAs);
            return this;
        }

        /**
         * @param search the search to set
         * @return
         */
        public Builder Search(Button search) {
            jMenu.add(search);
            return this;
        }

        /**
         * @param undo the undo to set
         * @return
         */
        public Builder Undo(Button undo) {
            jMenu.add(undo);
            return this;
        }

        /**
         * @param pane the pane to set
         * @return
         */
      

      
        public Frame build(Pane pane) {
            return new Frame(this,pane);
        }

    }

}
