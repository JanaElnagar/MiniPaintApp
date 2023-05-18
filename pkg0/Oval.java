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


public class Oval extends AbstractShapeClass {
    
    private int width;
    private int height;
    private Point[] handlers = new Point[4];
    
    public Oval(Point point, int width, int height)
    {
        super(point);
        this.width = width;
        this.height = height;
    }
    public Oval(){
        this.setBorderColor(Color.BLACK);
        this.setFillColor(Color.BLACK);
        
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }
    

    public void draw(Graphics canvas) {
        
        canvas.setColor(getBorderColor());
        canvas.drawOval(getPosition().x, getPosition().y, width, height);
        canvas.setColor(getFillColor());
        canvas.fillOval(getPosition().x, getPosition().y, width, height);
    }
    
    public boolean contains(Point point)
    {
//        int xCenter = getPosition().x;
//        int yCenter = getPosition().y;
//        int x = point.x-getRadius();
//        int y = point.y-getRadius();
//        double newRadius = Math.sqrt(Math.pow(x - xCenter, 2.0) + Math.pow(y - yCenter, 2.0));
//        return newRadius <= radius;
        int h = this.getPosition().x + width/2 ;
        int k = this.getPosition().y + height/2 ;
        double p = ((double)Math.pow((point.x - h), 2)
                    / (double)Math.pow(this.width, 2))
                   + ((double)Math.pow((point.y - k), 2)
                      / (double)Math.pow(this.height, 2));
        return p <= 1;
        
    }
    
    public void moveTo(Point point)
    {
        // to get the HANDLER_SIZEance between dragging point and shape center point
        int dragCenterDistX = Math.abs(this.getPosition().x - this.getDraggingPoint().x);
        int dragCenterDistY = Math.abs(this.getPosition().y - this.getDraggingPoint().y);

        Point newPosition = new Point(point.x-dragCenterDistX ,point.y-dragCenterDistY );
        //Point newPosition = new Point(point.x-this.getDraggingPoint().x ,point.y- this.getDraggingPoint().y);
        setPosition(newPosition);
        this.setHandlers();
        //setPosition(point);
    }
    
    public Oval clone() throws CloneNotSupportedException{
        
        Oval o = (Oval) super.clone();
        o.setPosition(this.getPosition());
        o.setHeight(this.height);
        o.setWidth(this.width);
        o.setBorderColor(this.getBorderColor());
        o.setFillColor(this.getFillColor());
        o.setHandlers();
        return o;
    }
    public Oval copy() throws CloneNotSupportedException{
        return clone();
    }
    
    public void setHandlers(){
        handlers[0] = this.getPosition();
        handlers[1] = new Point(this.getPosition().x + width , this.getPosition().y);
        handlers[2] = new Point(this.getPosition().x  , this.getPosition().y + height);
        handlers[3] = new Point(this.getPosition().x + width , this.getPosition().y + height);
    }

    public Point[] getHandlers(){
        return handlers;
    }
    
    public Point selectedHandler(Point p){
        for(int i = 0; i< 4; i++){
        int xDiff =Math.abs(handlers[i].x - p.x);
        int yDiff =Math.abs(handlers[i].y - p.y);
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
        int wDiff = newPoint.x - this.getResizingPoint().x;
        int hDiff = newPoint.y - this.getResizingPoint().y;
        for(int i = 0; i< 4; i++){
        xDiff = Math.abs(handlers[i].x - this.getResizingPoint().x);
        yDiff = Math.abs(handlers[i].y - this.getResizingPoint().y);
            if( xDiff <= Panel.HANDLER_SIZE && xDiff >= 0 && yDiff <= Panel.HANDLER_SIZE && yDiff >= 0){
                h = i;
                break;
            }
        }
        switch (h) {
            case 0:
                Point newPosition = new Point(newPoint.x,newPoint.y);
                //this.setPosition(newPosition);
                this.setPosition(newPoint);
                this.setHeight(this.height - hDiff);
                this.setWidth(this.width - wDiff);
                this.setHandlers();
                break;
            case 1:
                newPosition = new Point(this.getPosition().x ,newPoint.y+yDiff);
                this.setPosition(newPosition);
                this.setHeight(this.height - hDiff);
                this.setWidth(this.width + wDiff);
                this.setHandlers();
                break;
            case 2:
                newPosition = new Point(newPoint.x + xDiff,this.getPosition().y);
                this.setPosition(newPosition);
                this.setHeight(this.height + hDiff);
                this.setWidth(this.width - wDiff);
                this.setHandlers();
                break;
            case 3:
                this.setHeight(this.height + hDiff);
                this.setWidth(this.width + wDiff);
                this.setHandlers();
                break;
            default:
        }
    }

    @Override
    public JSONObject tojson() {
        
        String hexBorderColor = "#"+Integer.toHexString(super.getBorderColor().getRGB()).substring(2);
        String hexFillColor = "#"+Integer.toHexString(super.getFillColor().getRGB()).substring(2);
        Point[] po = this.getHandlers();
        
        JSONObject obj = new JSONObject();
        obj.put("Type", "Oval");
        obj.put("Width",this.getWidth());
        obj.put("Height",this.height);
        obj.put("borderColor", hexBorderColor);
        obj.put("fillColor", hexFillColor);
        obj.put("Positionx", super.getPosition().x);
        obj.put("Positiony",super.getPosition().y );
        
        
        return obj;
    }
    

    
    

    
}
