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
public class ManageTagform extends Form implements CommandListener {

    Ticker newsTicker = new Ticker("Java J2ME");
    private Display display;
    Form form;
    Command comBack;
    Command comAdd;
    Command comEdit;
    Command comSave;
    Command comDeleteAll;
    Command comDelete;
    ChoiceGroup list;
    TextField txtTag = new TextField("", "", 10, TextField.ANY);
    int iItemList;
    public BusinessTag businessAccess;
    Tag editTag;
    Tag newTag;
    Tag deletTag;
    Vector listTag;

    public ManageTagform(String title) {
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
            businessAccess.addTag(newTag);
            System.out.println(newTag.getId() + " " + newTag.getName());
            showListTag();
        } else if (c == comEdit) {
            txtTag.setString(list.getString(list.getSelectedIndex()));
            System.out.println(editTag.getId() + " " + editTag.getName());
            editTag.setId(list.getSelectedIndex());
            editTag.setName(list.getString(editTag.getId()));
            System.out.println(editTag.getId() + " " + editTag.getName());
        } else if (c == comSave) {
            editTag.setName(txtTag.getString());
            businessAccess.editTag(editTag);
            System.out.println(editTag.getId() + " " + editTag.getName());
            showListTag();
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
                        businessAccess.deleteAllTask();
                    }//delete all
                    display.setCurrent(form);
                    showListTag();
                }
            });
            deleteAllAlert.setTimeout(Alert.FOREVER);
            display.setCurrent(deleteAllAlert);
        } else if (c == comBack) {
            RecordingTimeform f1 = new RecordingTimeform("Recording Time");
            f1.setTicker(newsTicker);
            f1.setDisplay(this.display);
            this.display.setCurrent(f1);
        }
    }

    private void showMenu() {
        append(txtTag);
        form = this;
        comBack = new Command("Back", Command.OK, 2);
        comAdd = new Command("Add", Command.OK, 2);
        comEdit = new Command("Edit", Command.OK, 2);
        comSave = new Command("Save", Command.OK, 2);
        comDeleteAll = new Command("Delete All", Command.OK, 2);
        comDelete = new Command("Delete", Command.OK, 2);
        list = new ChoiceGroup("List Tag:", Choice.EXCLUSIVE);
        businessAccess = new BusinessTag();
        editTag = new Tag();
        newTag = new Tag();
        deletTag = new Tag();

        //ADD ITEM
        this.addCommand(comBack);
        this.addCommand(comAdd);
        this.addCommand(comEdit);
        this.addCommand(comSave);
        this.addCommand(comDeleteAll);
        this.addCommand(comDelete);

        //display.setCurrent(form);
        this.setCommandListener(this);

        //append(txtTag);
        iItemList = append(list);
        showListTag();
    }

    public void showListTag() {
        list.deleteAll();
        listTag = businessAccess.getAllTag();
        for (int i = 0; i < listTag.size(); i++) {
            Tag tag = (Tag) listTag.elementAt(i);
            list.append(tag.getName(), null);
            System.out.println(tag.getId() + " " + tag.getName());
        }
    }
}
