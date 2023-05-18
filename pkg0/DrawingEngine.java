/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package paintapp2.pkg0;

import java.awt.Graphics;
import javax.swing.JComboBox;

/**
 *
 * @author menna
 */
    public interface DrawingEngine {
    /* manage shapes objects */
    public void addShape(Shape shape);
    public void removeShape(Shape shape);
    /* return the created shapes objects */
    public Shape[] getShapes();
    public void clearShapes();
    /* redraw all shapes on the canvas */
    public void refresh(java.awt.Graphics canvas);
    public void setCombo(JComboBox combo);
    //public void displayHandles(Graphics g);
}
