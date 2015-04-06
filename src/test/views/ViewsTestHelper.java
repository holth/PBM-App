package test.views;

import java.lang.reflect.Field;

/**
 * This class contains 'helper' methods
 * to be used in 'test.views' classes
 * @author LAO Q. and HO L.T.H.
 */
public class ViewsTestHelper {

	
	/**
  	 * Gets the field value from an instance.  The field we wish to retrieve is
  	 * specified by passing the name.  The value will be returned, even if the
  	 * field would have private or protected access.
  	 */
  	public Object getField( Object instance, String name ) throws Exception
  	{
  		Class c = instance.getClass();

  		// Retrieve the field with the specified name
  		Field f = c.getDeclaredField( name );

  		// *MAGIC* make sure the field is accessible, even if it
  		// would be private or protected
  		f.setAccessible( true );

  		// Return the value of the field for the instance
  		return f.get( instance );
  	}
}
