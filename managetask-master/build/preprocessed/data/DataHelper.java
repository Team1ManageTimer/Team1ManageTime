/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import javax.microedition.rms.RecordStoreException;
import javax.microedition.rms.RecordStoreFullException;
import javax.microedition.rms.RecordStoreNotFoundException;
import pojo.Tag;
import java.util.Vector;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;

/**
 *
 * @author I320499
 */
public class DataHelper {

    static final String DATABASE_TASKNAME = "MANAGER_TASK";

    public void deleteAllTask() {
        try {
            RecordStore.deleteRecordStore(DATABASE_TASKNAME);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void writeTask(Tag task) {
        try {
            RecordStore rs = RecordStore.openRecordStore(DATABASE_TASKNAME, true);
            rs.addRecord(task.getName().getBytes(), 0, task.getName().getBytes().length);
            rs.closeRecordStore();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public boolean deleteTask(String name) {
        boolean result = false;
        int id = -1;
        int i = 0;
        try {
            RecordStore rs = RecordStore.openRecordStore(DATABASE_TASKNAME, true);
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            int[] ArrId = new int[rs.getSize()];
            while (re.hasNextElement()) {
                ArrId[i] = re.nextRecordId();
                i++;
            }
            re = rs.enumerateRecords(null, null, false);
            i = 0;
            while (re.hasNextElement()) {
                byte[] data = re.nextRecord();
                String str = new String(data);
                if (str.toUpperCase().equals(name.toUpperCase())) {
                    id = ArrId[i];
                }
                i++;
            }
            if(id >0){
                rs.deleteRecord(id);
            }
            rs.closeRecordStore();
            result = true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }

    public Vector getAllTask() {
        try {
            RecordStore rs = RecordStore.openRecordStore(DATABASE_TASKNAME, true);
            int numTask = rs.getNumRecords();
            Vector tasks = new Vector();
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            while (re.hasNextElement()) {
                byte[] record = re.nextRecord();
                String str = new String(record);
                Tag task = new Tag();
                task.setName(str);
                // task.setId(re.nextRecordId());
                tasks.addElement(task);
            }
            rs.closeRecordStore();
            return tasks;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return null;
    }
}
