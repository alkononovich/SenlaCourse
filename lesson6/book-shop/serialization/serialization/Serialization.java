package serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;


public class Serialization {
	private static final Logger logger = Logger.getLogger(Serialization.class);
	
	public void serialToFile(Object o, String path) {
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
			out.writeObject(o);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			System.out.println("error serialization");
		}
	}

	public Object serialFromFile(String path) {
		Object clone = null;
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
			clone = in.readObject();
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		return clone;
	}

}
