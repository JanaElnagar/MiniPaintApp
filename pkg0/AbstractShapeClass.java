/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paintapp2.pkg0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

public abstract class AbstractShapeClass implements Shape, Moveable{
    private Color borderColor;
    private Color fillColor;
    private Point position;
    private Point draggingPoint;
    private Point resizingPoint;

    public Point getResizingPoint() {
        return resizingPoint;
    }

    public void setResizingPoint(Point resizingPoint) {
        this.resizingPoint = resizingPoint;
    }

    public AbstractShapeClass(Point position)
    {
        this.position = position;
    }
    public AbstractShapeClass()
    {
        
    }
    
    public Color getBorderColor() {
        return borderColor;
    }

    public void setBorderColor(Color borderColor) {
        this.borderColor = borderColor;
    }

    public Color getFillColor() {
        return fillColor;
    }

    public void setFillColor(Color fillColor) {
        this.fillColor = fillColor;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
    }

    public Point getDraggingPoint() {
        return draggingPoint;
    }

    public void setDraggingPoint(Point draggingPoint) {
        this.draggingPoint = draggingPoint;
    }
    
    
    

    }
    
    
    
   
