
package paintapp2.pkg0;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import javax.swing.JPanel;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import javax.swing.JComboBox;

public class Panel extends JPanel implements DrawingEngine{
    
    private static ArrayList<Shape> shapes;
    private Moveable selectedShape;
    //private AbstractShapeClass selectedShape;
    private JComboBox combo;
    private Point previousPoint;
    private Point currentPoint;
    public static final int HANDLER_SIZE = 8;
    private boolean resizingMode;
    Graphics thisG;
    
    public Panel(){
        //this.shapes = new ArrayList<Shape>(); 
        this.shapes = new ArrayList<>();
        this.combo=null;
        ClickListener clickListener = new ClickListener();
        this.addMouseListener(clickListener);
        DragListener dragListener = new DragListener();
        this.addMouseMotionListener(dragListener);
    }

    
    @Override
    public void setCombo(JComboBox combo) {
        this.combo = combo;
    }
    
    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        thisG = g;
        for(int i=0;i<shapes.size();i++){
            shapes.get(i).draw(g);
        }
        if(selectedShape != null)
            displayHandles(g);
    }
    
    private class ClickListener extends MouseAdapter{
        
        
        @Override
        public void mousePressed(MouseEvent evt){
            
            //previousPoint = evt.getPoint();super.mousePressed(evt);
            previousPoint = evt.getPoint();
            //selectedShape = null;
            if(selectedShape != null ){//&&selectedShape.contains(previousPoint) 
                    //&&selectedShape.selectedHandler(previousPoint) != null){
                
              if(selectedShape.selectedHandler(previousPoint) != null){
                    resizingMode = true;
                    return;
                }
            }
            refresh(null);
            selectedShape=null;
            resizingMode = false;
            for(int i = 0; i < shapes.size(); i++)
             {

                 final Moveable m = (Moveable)shapes.get(i);

                 if(m.contains(previousPoint))
                 {
                     Panel.this.combo.setSelectedIndex(i);
                     selectedShape = m;
                     //selectedShape =(AbstractShapeClass)m;
                     ((Moveable)selectedShape).setDraggingPoint(previousPoint);
                    // Graphics g = null;
                     //displayHandles(thisG);
                    //resizingMode = true;
                     refresh(null);
                 }    
            } 
        }
    }
    
        
    
    
    private class DragListener extends MouseMotionAdapter{
        
        @Override
        public void mouseDragged(MouseEvent evt){
            
            {
                //super.mouseDragged(evt);
                if(selectedShape==null){return;}
                
                currentPoint = evt.getPoint();
                refresh(null);
                if(resizingMode){
                    selectedShape.resize(currentPoint);  
                    Panel.this.repaint();
                    selectedShape.setResizingPoint(currentPoint);
                    previousPoint = currentPoint;  
                }
                else{
                    selectedShape.moveTo(currentPoint);
                    Panel.this.repaint();
                    selectedShape.setDraggingPoint(currentPoint);
                    previousPoint = currentPoint;  
                }
//                Point p = new Point((int)(currentPoint.getX() - previousPoint.getX()),
//                                    (int)(currentPoint.getY() - previousPoint.getY())); 
            }
            
        }
    }
    
    public void displayHandles(Graphics g){
        

        //Point[] handlers =((Resizable)s).getHandlers();
        if(selectedShape == null)
            refresh(null);
        else{
        Point[] handlers = selectedShape.getHandlers();
        
        for(Point h : handlers){

//           g.setColor(Color.WHITE);
//           g.fillRect(h.x, h.y, HANDLER_SIZE, HANDLER_SIZE);
//           g.setColor(Color.BLACK);
//           g.drawRect(h.x, h.y, HANDLER_SIZE, HANDLER_SIZE);
             g.setColor(Color.WHITE);
             g.fillRect(h.x-(HANDLER_SIZE/2), h.y-(HANDLER_SIZE/2), HANDLER_SIZE, HANDLER_SIZE);
             g.setColor(Color.BLACK);
             g.drawRect(h.x-(HANDLER_SIZE/2), h.y-(HANDLER_SIZE/2), HANDLER_SIZE, HANDLER_SIZE);
           }
        }
         //refresh(null);
        
        
        
    }
    
    @Override
    public void addShape(Shape shape) {
        shapes.add(shape);
        
    }

    @Override
    public void removeShape(Shape shape) {
        shapes.remove(shape);
    }

    @Override
    public Shape[] getShapes() {
        
        //return shapes.toArray(new Shape[shapes.size()]);
        return shapes.toArray(new Shape[0]);
    }
    
    public void clearShapes(){
        shapes.clear();
    }

    @Override
    public void refresh(Graphics canvas) {
        repaint();
        //canvas.clearRect(0, 0, 500, 235);
        //shapes.clear();
    }
    

}
