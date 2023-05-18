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
public interface Resizable {
    
    public void setHandlers();
    public Point[] getHandlers();
    public void setResizingPoint(Point p);
    public Point getResizingPoint();
    public void resize(Point newPoint);
    public Point selectedHandler(Point p);
    
}
