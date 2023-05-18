/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package paintapp2.pkg0;

/**
 *
 * @author ADMIN
 */
public interface Copyable extends Cloneable{
    
    //public Object copy() throws CloneNotSupportedException;
    public Shape copy() throws CloneNotSupportedException;
}
