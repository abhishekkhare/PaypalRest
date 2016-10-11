package com.edu.abhi.paypal.dao;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author abhishekkhare
 *
 */
@SuppressWarnings("unchecked")
public class DataStore {
	private static Map<Integer, List<String>> dataStore = new HashMap<Integer, List<String>>();

	static {
		try {
			FileInputStream fis = new FileInputStream("dataStore.ser");
			@SuppressWarnings("resource")
			ObjectInputStream ois = new ObjectInputStream(fis);
			Object obj = ois.readObject();
			if (obj != null) {
				dataStore = (Map<Integer, List<String>>) obj;
			}
		} catch (Exception e) {
			// e.printStackTrace();
		}
	}

	public static boolean store(String id) {
		Integer key = convertIDToKey(id);
		if (dataStore.containsKey(key)) {
			dataStore.get(key).add(id);
		} else {
			List<String> tempList = new ArrayList<String>();
			tempList.add(id);
			dataStore.put(key, tempList);
		}
		serializeTheMap();
		return true;
	}

	public static List<String> getDataFromStore(Integer id) {
		List<String> tempList = new ArrayList<String>();
		if (dataStore.containsKey(id)) {
			tempList = dataStore.get(id);
		}
		return tempList;
	}

	private static void serializeTheMap() {
		try {
			FileOutputStream fos = new FileOutputStream("dataStore.ser");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dataStore);
			oos.close();
			fos.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException("Failed to store the data");
		}
	}

	private static Integer convertIDToKey(String id) {
		if (id != null && id.length() > 0) {
			Integer temp = 0;
			for (int i = 0; i < id.length(); i++) {
				if (i == 0) {
					temp += (int) (id.charAt(i));

				} else if (i > 0) {
					temp += (int) (id.charAt(i - 1)) + (int) (id.charAt(i));
				}
			}
			System.out.println("id:" + id +" key:" + temp);
			return temp;
		}
		throw new RuntimeException("Invalid Input String");
	}


}
