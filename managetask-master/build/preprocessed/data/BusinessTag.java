/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Vector;
import javax.microedition.rms.RecordEnumeration;
import javax.microedition.rms.RecordStore;
import pojo.Tag;

/**
 *
 * @author TrungKien
 */
public class BusinessTag {

    RecordStore rs = null;
    public static String strRecordName = "RECORDNAME";

    public void openRecStore() {
        try {
            rs = RecordStore.openRecordStore(strRecordName, true);
        } catch (Exception e) {
            db("OPEN REC STORE");
        }
    }

    public void closeRecStore() {
    }

    public boolean addTag(Tag newTag) {
        openRecStore();
        byte[] rec = newTag.getName().getBytes();
        try {
            newTag.setId(rs.getNumRecords());
            System.out.println("IDRECORE: " + rs.addRecord(rec, 0, rec.length));
            rs.closeRecordStore();
            return true;
        } catch (Exception e) {
            db("Add Tag");
            return false;
        }
    }

    public boolean editTag(Tag editTag) {
        openRecStore();
        byte[] rec = editTag.getName().getBytes();
        try {
            rs.setRecord(editTag.getId() + 1, rec, 0, rec.length);//test         
            rs.closeRecordStore();
            return true;
        } catch (Exception e) {
            db("Edit Tag");
            return false;
        }
    }

    public boolean deleteTag(Tag deleteTag) {
        openRecStore();
        try {
            rs.deleteRecord(deleteTag.getId() + 1);//test
            rs.closeRecordStore();
            return true;
        } catch (Exception e) {
            db(e.toString());
            return false;
        }
    }

    public Vector getAllTag(){
        try {
            openRecStore();
            int numTask = rs.getNumRecords();
            Vector tasks = new Vector();
            RecordEnumeration re = rs.enumerateRecords(null, null, false);
            while (re.hasNextElement()) {
                byte[] record = re.nextRecord();
                String str = new String(record);
                Tag task = new Tag();
                task.setName(str);
                tasks.addElement(task);
            }
            rs.closeRecordStore();
            return tasks;
        } catch (Exception e) {
            db(e.toString());
        }
        return null;
    } 

    public void deleteAllTask() {
        try {
            RecordStore.deleteRecordStore(strRecordName);
        } catch (Exception e) {
            db("getAllTag");
        }
    }

    private void db(String str) {
        System.err.println("Msg: " + str);
    }
}
