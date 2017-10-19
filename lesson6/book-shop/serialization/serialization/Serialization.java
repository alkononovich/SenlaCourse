package serialization;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;
import com.senla.training.kononovich.storage.Container;

import config.Configuration;

public class Serialization {
	private static final Logger logger = Logger.getLogger(Serialization.class);
	private static Configuration conf = Configuration.getInstance();
	private static String path = "object.dat";

	public static String getPath() {
		return path;
	}

	public static void setPath(String path) {
		Serialization.path = path;
	}
	private static void initPath() {
		if (conf.getProps().getSerialPath() != null) {
			path = conf.getProps().getSerialPath();
		}
	}
	
	public static void serialToFile() {
		Container toSerialize = Container.getInstance();
		initPath();
		try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(path))) {
			out.writeObject(toSerialize);
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
			System.out.println("error serialization");
		}
	}

	public static void serialFromFile() {
		initPath();
		try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(path))) {
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
