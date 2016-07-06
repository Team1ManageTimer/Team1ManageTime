/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managetime;

import data.BusinessTag;
import java.util.Vector;
import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.*;
import pojo.Tag;

/**
 *
 * @author BaoNguyen
 */
public class ManageTagform1 extends Form implements CommandListener {

    private Display display;
    Form form;
    Command comAdd;
    Command comEdit;
    Command comSave;
    Command comDeleteAll;
    Command comDelete;
    ChoiceGroup list;
    TextField txtTag = new TextField("", "", 10, TextField.ANY);
    int iItemList;
    public BusinessTag businessTag;
    Tag editTag;
    Tag newTag;
    Tag deletTag;

    public ManageTagform1(String title) {
        super(title);
         //append(txtTag);
        showMenu();
    }

    void setDisplay(Display display) {
        this.display = display;
    }

    //Action
    public void commandAction(Command c, Displayable dsplbl) {
        //String label = c.getLabel();
        if (c == comAdd) {
            newTag.setName(txtTag.getString());
            businessTag.addTag(newTag);
            System.out.println(newTag.getId() + " " + newTag.getName());
            showListTag();
            //drawGui();
        } else if (c == comEdit) {
            txtTag.setString(list.getString(list.getSelectedIndex()));
            System.out.println(editTag.getId() + " " + editTag.getName());
            editTag.setId(list.getSelectedIndex());
            editTag.setName(list.getString(editTag.getId()));
            System.out.println(editTag.getId() + " " + editTag.getName());
            //drawGui();
        } else if (c == comSave) {
            editTag.setName(txtTag.getString());
            businessTag.editTag(editTag);
            System.out.println(editTag.getId() + " " + editTag.getName());
            showListTag();
            //drawGui();
        } else if (c == comDeleteAll) {
            Alert deleteAllAlert = new Alert("Delete All",
                    "Are you sure you want to delete all tag?", null, AlertType.WARNING);
            deleteAllAlert.addCommand(new Command("Yes", Command.EXIT, 1));
            deleteAllAlert.addCommand(new Command("No", Command.SCREEN, 2));
            // --> set command listener for ExitAlrt prior to invoking setCurrent
            deleteAllAlert.setCommandListener(new CommandListener() {
                public void commandAction(Command c, Displayable d) {
                    String label = c.getLabel();
                    if (label.equals("Yes")) {
                        businessTag.deleteAllTask();
                        showListTag();
                    }//delete all
                    //display.setCurrent(form);
                    //drawGui();
                }
            });
            deleteAllAlert.setTimeout(Alert.FOREVER);
            display.setCurrent(deleteAllAlert);

        }
//        else if (label.equals("Delete")) {
//            Alert deleteAlert = new Alert("Delete",
//                    "Are you sure you want to delete tag?", null, AlertType.WARNING);
//            deleteAlert.addCommand(new Command("Yes", Command.EXIT, 1));
//            deleteAlert.addCommand(new Command("No", Command.SCREEN, 2));
//            // --> set command listener for ExitAlrt prior to invoking setCurrent
//            deleteAlert.setCommandListener(new CommandListener() {
//                public void commandAction(Command c, Displayable d) {
//                    String label = c.getLabel();
//                    if (label.equals("Yes")) {
//                        deletTag.setId(list.getSelectedIndex());
//                        deletTag.setName(list.getString(deletTag.getId()));
//                        System.out.println(deletTag.getId() + " " + deletTag.getName());
//                        businessTag.deleteTag(deletTag);
//                        showListTag();
//                    }//delete 
//                    display.setCurrent(form);
//                }
//            });
//            deleteAlert.setTimeout(Alert.FOREVER);
//            display.setCurrent(deleteAlert);
//        }
    }

//    private void drawGui() {
//        append(txtTag);
//    }

    private void showMenu() {
        //display = Display.getDisplay(this);
        append(txtTag);
        comAdd = new Command("Add", Command.OK, 2);
        comEdit = new Command("Edit", Command.OK, 2);
        comSave = new Command("Save", Command.OK, 2);
        comDeleteAll = new Command("Delete All", Command.OK, 2);
        comDelete = new Command("Delete", Command.OK, 2);
        list = new ChoiceGroup("List Tag:", Choice.EXCLUSIVE);
        businessTag = new BusinessTag();
        editTag = new Tag();
        newTag = new Tag();
        deletTag = new Tag();
        
        //ADD ITEM
        this.addCommand(comAdd);
        this.addCommand(comEdit);
        this.addCommand(comSave);
        this.addCommand(comDeleteAll);
        this.addCommand(comDelete);

        //display.setCurrent(form);

        this.setCommandListener(this);
       
        //append(txtTag);
        iItemList=append(list);
    }

    public void showListTag() {
        //drawGui();
        //append(txtTag);
        //this.deleteAll();
        //list.deleteAll();
        
        Vector listTag = businessTag.getAllTag();
        for (int i = 0; i < listTag.size(); i++) {
            Tag tag = (Tag) listTag.elementAt(i);
            list.append(tag.getName(), null);
            System.out.println(tag.getId() + " " + tag.getName());
        }
       //append(txtTag);
       //append(list);
    }

}
