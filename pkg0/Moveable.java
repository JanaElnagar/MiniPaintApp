/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package paintapp2.pkg0;

import java.awt.Point;

/**
 *
 * @author ADMIN
 */
public interface Moveable extends Resizable{
    
    public void setDraggingPoint(Point point);
    public Point getDraggingPoint();
    public boolean contains(Point point);
    public void moveTo(Point point);

}
