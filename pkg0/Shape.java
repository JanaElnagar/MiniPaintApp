/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package paintapp2.pkg0;

import org.json.simple.JSONObject;


public interface Shape extends Copyable{

    public void setPosition(java.awt.Point position);
    public java.awt.Point getPosition();
    /* colorize */
    public void setBorderColor(java.awt.Color color);
    public java.awt.Color getBorderColor();
    public void setFillColor(java.awt.Color color);
    public java.awt.Color getFillColor();
    /* redraw the shape on the canvas */
    public void draw(java.awt.Graphics canvas);
    public JSONObject tojson(); 
}
