package serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;
import com.senla.training.kononovich.storage.Container;

public class Serialization {
	private static final Logger logger = Logger.getLogger(Serialization.class);

	public static void serialToFile() {
		Container toSerialize = Container.getInstance();
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("object.dat"))) {
			out.writeObject(toSerialize);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			System.out.println("error serialization");
		}
	}

	public static void serialFromFile() {

		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("object.dat"))) {
			Container container = Container.getInstance();
			Container clone = (Container) in.readObject();

			container.setBooks(clone.getBooks());
			container.setOrders(clone.getOrders());
			container.setClaims(clone.getClaims());
		} catch (ClassNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (FileNotFoundException e) {
			logger.error(e.getMessage(), e);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
	}

}
