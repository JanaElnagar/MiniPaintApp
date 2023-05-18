
package paintapp2.pkg0;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import org.json.simple.JSONObject;


public class LineSegment extends AbstractShapeClass{
    

    private Point p2;
    private Point[] handlers = new Point[2];
    
    public LineSegment(){
        this.setBorderColor(Color.BLACK);
        
    }
    public LineSegment(Point p1, Point p2)
    {
        super(p1);
        this.p2 = p2;
    }
    
    public void setP2(Point position) {
           p2=position;
    }

    public Point getP2() {
            return p2;
    }


    @Override
    public void draw(Graphics canvas) {
        canvas.setColor(getBorderColor());
        canvas.drawLine(getPosition().x, getPosition().y, p2.x, p2.y);
    }
    
    private double HANDLER_SIZEance(Point p1, Point p2)
    {
        return Math.sqrt((p2.y - p1.y) * (p2.y - p1.y) + (p2.x - p1.x) * (p2.x - p1.x));
    }
    
    public boolean contains(Point p)
    {
        return HANDLER_SIZEance(this.getPosition(), p) + HANDLER_SIZEance(this.getP2(), p) == HANDLER_SIZEance(this.getPosition(), this.getP2());
    }
    
    public void moveTo(Point p)
    {
        int xDiff1 = (this.getDraggingPoint().x - this.getPosition().x);
        int yDiff1 = (this.getDraggingPoint().y - this.getPosition().y);
        int xDiff2 = (this.getDraggingPoint().x - this.p2.x);
        int yDiff2 = (this.getDraggingPoint().y - this.p2.y);
        
        this.setPosition(new Point(p.x - xDiff1, p.y - yDiff1));
        this.p2 = new Point(p.x - xDiff2, p.y - yDiff2);
        this.setHandlers();
        
    }
    
    public LineSegment clone() throws CloneNotSupportedException{
        
        LineSegment l = (LineSegment) super.clone();
        l.setPosition(this.getPosition());
        l.setP2(this.p2);
        l.setBorderColor(this.getBorderColor());
        l.setFillColor(this.getFillColor());
        l.setHandlers();
        
        return l;
    }
    public LineSegment copy() throws CloneNotSupportedException{
        return clone();
    }
    
    public void setHandlers(){
        handlers[0] = this.getPosition();
        handlers[1] = this.getP2();
    }

    public Point[] getHandlers(){
        return handlers;
    }
    public Point selectedHandler(Point p){
        for(int i = 0; i< 2; i++){
//        int xDiff = handlers[i].x - p.x;
//        int yDiff = handlers[i].y - p.y;
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
        for(int i = 0; i< 2; i++){
        xDiff = handlers[i].x - this.getResizingPoint().x;
        yDiff = handlers[i].y - this.getResizingPoint().y;
            if( xDiff <= Panel.HANDLER_SIZE && xDiff >= 0 && yDiff <= Panel.HANDLER_SIZE && yDiff >= 0){
                h = i;
                break;
            }
        }
        switch (h) {
            case 0:
//                Point newPosition = new Point(newPoint.x-xDiff ,newPoint.y-yDiff );
//                this.setPosition(newPosition);
                this.setPosition(newPoint);
                this.setHandlers();

                break;
            case 1:
//                newPosition = new Point(this.getPosition().x + xDiff,this.getPosition().y + yDiff);
//                this.setP2(newPosition);
                this.setP2(newPoint);
                this.setHandlers();

                break;
            default:
        }
    
    }

    @Override
    public JSONObject tojson() {
        JSONObject obj=new JSONObject();
        String hexBorderColor = "#"+Integer.toHexString(super.getBorderColor().getRGB()).substring(2);
        
        obj.put("Type", "Line");
        obj.put("Positionx1", super.getPosition().x);
        obj.put("Positiony1",super.getPosition().y );
        obj.put("Positionx2", this.p2.x);
        obj.put("Positiony2",this.p2.y );
        obj.put("borderColor", hexBorderColor);
        
      
       return obj;
    }   
    
}
