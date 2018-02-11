package com.allow2;

public class Allow2 {
	
	// static variable single_instance of type Allow2
    private static Allow2 _shared = null;
    
    private volatile int  x = 0;

    /**
     * No-argument constructor.
     * @return 
     */
    private Allow2()
    {
    	this.x = 1;
    }
 
    // static method to create instance of Singleton class
    public static Allow2 getShared()
    {
        if (_shared == null)
        	_shared = new Allow2();
 
        return _shared;
    }

    
    /**
     * Method to get the current integer value.
     * @return the value (int)
     */
    public final int getX()  { return this.x; }

    /**
     * Method for changing the current integer value;
     * @param x new value for integer (int)
     */
    public final void setX( int x )  { this.x = x; }

    /**
     * Render the current integer value as a string.
     * @return the integer as a string (String)
     */
    public final String toString()  { return x + ""; }

    /**
     * Test this lightly.
     * @param args (none are ever processed)
     */
    public static void main( String[] args )
    {
        Allow2 allow2 = Allow2.getShared();
        
        System.out.println( "X is " + allow2.toString() );
    }
}
