/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paintapp2.pkg0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import org.json.simple.JSONObject;

/**
 *
 * @author ADMIN
 */
public class Rectangle extends AbstractShapeClass {
    
    private int width;
    private int height;
    private Point[] handlers = new Point[4];
    
    public Rectangle(){
        this.setBorderColor(Color.BLACK);
        this.setFillColor(Color.BLACK);
        
    }
    public Rectangle(Point point, int width, int height)
    {
        super(point);
        this.height = height;
        this.width = width;
    }
    
    public void setHeight(int x)
    {
        height = x;
    }
    public void setWidth(int x)
    {
        width = x;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

 
    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(super.getBorderColor());
        canvas.drawRect(super.getPosition().x, super.getPosition().y, width, height);
        canvas.setColor(super.getFillColor());
        canvas.fillRect(super.getPosition().x, super.getPosition().y, width, height); 
    }
    
    public boolean contains(Point p)
    {
        int x1 = this.getPosition().x;
        int y1 = this.getPosition().y;
        int x2 = x1 + this.width;
        int y2 = y1 + this.height;
        
        return p.x > x1 && p.x < x2 && p.y > y1 && p.y < y2;
    }
    
    public void moveTo(Point p)
    {
        int xDragDiff = Math.abs(this.getPosition().x- this.getDraggingPoint().x);
        int yDragDiff = Math.abs(this.getPosition().y - this.getDraggingPoint().y) ;
        this.setPosition(new Point(p.x - xDragDiff, p.y - yDragDiff));
        this.setHandlers();
       // setPosition(p);
    }
    
    public Rectangle clone() throws CloneNotSupportedException{
        
        Rectangle r = (Rectangle) super.clone();
        r.setPosition(this.getPosition());
        r.setHeight(this.height);
        r.setWidth(this.width);
        r.setBorderColor(this.getBorderColor());
        r.setFillColor(this.getFillColor());
        r.setHandlers();
        
        return r;
    }
    public Rectangle copy() throws CloneNotSupportedException{
        return clone();
    }
    
    public void setHandlers(){
        //handlers[0] = this.getPosition();
        handlers[0] = new Point(this.getPosition().x , this.getPosition().y );
        handlers[1] = new Point(this.getPosition().x + width , this.getPosition().y);
        handlers[2] = new Point(this.getPosition().x  , this.getPosition().y + height);
        handlers[3] = new Point(this.getPosition().x + width , this.getPosition().y + height);
    }

    @Override
    public Point[] getHandlers(){
        return handlers;
    }
    @Override
    public Point selectedHandler(Point p){
        for(int i = 0; i< 4; i++){
//        int xDiff = handlers[i].x - p.x;
//        int yDiff = handlers[i].y - p.y;
        int xDiff =Math.abs(handlers[i].x - p.x);
        int yDiff =Math.abs(handlers[i].y - p.y);
            if( xDiff <= Panel.HANDLER_SIZE && xDiff >= 0 && yDiff <= Panel.HANDLER_SIZE && yDiff >= 0){
                this.setResizingPoint(p);
                return handlers[i];
            }
        }
        return null;
    }
    
    @Override
    public void resize(Point newPoint){
        int h = -1;
        int xDiff = 0;
        int yDiff = 0;
        int wDiff = newPoint.x - this.getResizingPoint().x;
        int hDiff = newPoint.y - this.getResizingPoint().y;
        for(int i = 0; i< 4; i++){
        xDiff = Math.abs(handlers[i].x - this.getResizingPoint().x);
        yDiff = Math.abs(handlers[i].y - this.getResizingPoint().y);
            if( xDiff <= Panel.HANDLER_SIZE/2 && xDiff >= 0 && yDiff <= Panel.HANDLER_SIZE/2 && yDiff >= 0){
                h = i;
                break;
            }
        }
        switch (h) {
            case 0 -> {
                Point newPosition = new Point(newPoint.x,newPoint.y);
                //this.setPosition(newPosition);
                this.setPosition(newPoint);
                this.setHeight(this.height - hDiff);
                this.setWidth(this.width - wDiff);
                this.setHandlers();
            }
            case 1 -> {
                Point newPosition = new Point(this.getPosition().x ,newPoint.y+yDiff);
                this.setPosition(newPosition);
                this.setHeight(this.height - hDiff);
                this.setWidth(this.width + wDiff);
                this.setHandlers();
            }
            case 2 -> {
                Point newPosition = new Point(newPoint.x + xDiff,this.getPosition().y);
                this.setPosition(newPosition);
                this.setHeight(this.height + hDiff);
                this.setWidth(this.width - wDiff);
                this.setHandlers();
            }
            case 3 -> {
                this.setHeight(this.height + hDiff);
                this.setWidth(this.width + wDiff);
                this.setHandlers();
            }
            default -> {
            }
        }
    }
    
    
        
        

    @Override
    public JSONObject tojson() {
        JSONObject obj = new JSONObject();
        
        String hexBorderColor = "#"+Integer.toHexString(super.getBorderColor().getRGB()).substring(2);
        String hexFillColor = "#"+Integer.toHexString(super.getFillColor().getRGB()).substring(2);
        
        obj.put("Type", "Rect");
        obj.put("Width", this.width);
        obj.put("Height", this.height);
        obj.put("borderColor", hexBorderColor);
        obj.put("fillColor", hexFillColor);
        obj.put("Positionx", super.getPosition().x);
        obj.put("Positiony",super.getPosition().y );
        return obj;
        
    }

    
}
