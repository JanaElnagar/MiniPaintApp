/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paintapp2.pkg0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.lang.Math;
import org.json.simple.JSONObject;

public class Triangle extends AbstractShapeClass{
    private Point point2;
    private Point point3;
    private Point[] handlers = new Point[3];
    
    public Triangle(){  
        this.setBorderColor(Color.BLACK);
        this.setFillColor(Color.BLACK);
        
    }
    public Triangle(Point point1,Point point2,Point point3)
    {
        super(point1);
        this.point2 = point2;
        this.point3 = point3;
    }

    public Point getPoint2() {
        return point2;
    }

    public void setPoint2(Point point2) {
        this.point2 = point2;
    }

    public Point getPoint3() {
        return point3;
    }

    public void setPoint3(Point point3) {
        this.point3 = point3;
    }
    
    public void draw(Graphics canvas)
    {
         int[] xVertices = { getPosition().x, point2.x, point3.x };
         int[] yVertices = { getPosition().y, point2.y, point3.y };
        canvas.setColor(this.getBorderColor());
        canvas.drawPolygon(xVertices, yVertices, 3);
        canvas.setColor(this.getFillColor());
        canvas.fillPolygon(xVertices, yVertices, 3);
        
        
    }
   private double area(int x1, int y1, int x2, int y2, int x3, int y3)
   {
      return Math.abs((x1*(y2-y3) + x2*(y3-y1)+ x3*(y1-y2))/2.0);
   }
   
   public boolean contains(Point p)
   {
       
        double A = area (getPosition().x, getPosition().y, point2.x, point2.y, point3.x, point3.y);
         
        double A1 = area (p.x, p.y, point2.x, point2.y, point3.x, point3.y);      
        double A2 = area (getPosition().x, getPosition().y, p.x, p.y, point3.x, point3.y);       
        double A3 = area (getPosition().x, getPosition().y, point2.x, point2.y, p.x, p.y);
     
        return (A == A1 + A2 + A3);
   }
   
   public void moveTo(Point p)
   {
       // to get the HANDLER_SIZEance between the dragging point and each vertix 
        int dragX1diff = (this.getDraggingPoint().x - this.getPosition().x);
        int dragY1diff = (this.getDraggingPoint().y - this.getPosition().y);
        int dragX2diff = (this.getDraggingPoint().x - this.point2.x);
        int dragY2diff = (this.getDraggingPoint().y - this.point2.y);
        int dragX3diff = (this.getDraggingPoint().x - this.point3.x);
        int dragY3diff = (this.getDraggingPoint().y - this.point3.y);
        
        this.setPosition(new Point(p.x - dragX1diff, p.y - dragY1diff));
        this.point2.x = p.x - dragX2diff;
        this.point2.y = p.y - dragY2diff;
        this.point3.x = p.x - dragX3diff;
        this.point3.y = p.y - dragY3diff;
        this.setHandlers();
       
   }
   

    public Triangle clone() throws CloneNotSupportedException{
        
        Triangle t = (Triangle) super.clone();
        t.setPosition(this.getPosition());
        t.setPoint2(this.point2);
        t.setPoint3(this.point3);
        t.setBorderColor(this.getBorderColor());
        t.setFillColor(this.getFillColor());
        t.setHandlers();
        
        return t;
    }
    
    public Triangle copy() throws CloneNotSupportedException{
        return clone();
    }
    
    public void setHandlers(){
        handlers[0] = this.getPosition();
        handlers[1] = this.getPoint2();
        handlers[2] = this.getPoint3();
    }

    public Point[] getHandlers(){
        return handlers;
    }
    public Point selectedHandler(Point p){
        for(int i = 0; i< 3; i++){
//        int xDiff = handlers[i].x - p.x;
//        int yDiff = handlers[i].y - p.y;
            int xDiff = Math.abs(handlers[i].x - p.x);
            int yDiff = Math.abs(handlers[i].y - p.y);   
            if( xDiff <= Panel.HANDLER_SIZE/2 && xDiff >= 0 && yDiff <= Panel.HANDLER_SIZE/2 && yDiff >= 0){
                this.setResizingPoint(p);
                return handlers[i];
                }
        }
        return null;
    }
    
    public void resize(Point newPoint){
        int h = -1;
        int xDiff = 0;
        int yDiff = 0;
        for(int i = 0; i< 3; i++){
        xDiff = handlers[i].x - this.getResizingPoint().x;
        yDiff = handlers[i].y - this.getResizingPoint().y;
            if( xDiff <= Panel.HANDLER_SIZE && xDiff >= 0 && yDiff <= Panel.HANDLER_SIZE && yDiff >= 0){
                h = i;
                break;
            }
        }
        switch (h) {
            case 0:
                Point newPosition = new Point(newPoint.x ,newPoint.y );
                this.setPosition(newPosition);
                this.setHandlers();
                break;
            case 1:
                newPosition = new Point(newPoint.x ,newPoint.y );
                this.setPoint2(newPosition);
                this.setHandlers();
                break;
            case 2:
                newPosition = new Point(newPoint.x ,newPoint.y );
                this.setPoint3(newPosition);
                this.setHandlers();
                break;
            default:
        }
    
    }
    

        
        

    @Override
    public JSONObject tojson() {
        JSONObject obj = new JSONObject();
        String hexBorderColor = "#"+Integer.toHexString(super.getBorderColor().getRGB()).substring(2);
        String hexFillColor = "#"+Integer.toHexString(super.getFillColor().getRGB()).substring(2);
        obj.put("Type", "Tri");
        obj.put("Positionx1", super.getPosition().x);
        obj.put("Positiony1",super.getPosition().y );
        obj.put("Positionx2", this.point2.x);
        obj.put("Positiony2",this.point2.y );       
        obj.put("Positionx3", this.point3.x);       
        obj.put("Positiony3", this.point3.y);       
        obj.put("borderColor", hexBorderColor);
        obj.put("fillColor", hexFillColor);
        return obj;
        
    }
    
    
}
