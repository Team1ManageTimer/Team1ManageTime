/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managetime;

import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.*;

/**
 *
 * @author BaoNguyen
 */
public final class ManageTimeForm extends Form implements CommandListener {

    Display display;
    Displayable main;
    Command Back;
    Command Add;
    Command Edit;
    Command Delete;

    public ManageTimeForm(String title) {
        super(title);
        this.drawGui();
        this.showMenu();
    }
    public void setDisplay(Display display) {
        this.display = display;
    }

    //Action
    public void commandAction(Command c, Displayable dsplbl) {
        if (c == Back) {
            RecordingTimeform f1 = new RecordingTimeform("Recording Time");
            f1.setDisplay(this.display);
            this.display.setCurrent(f1);
        }

    }

    protected void drawGui() {

        //DateField
        DateField date = new DateField("Recorded", DateField.DATE);
        date.setDate(new java.util.Date());
        this.append(date);
        //
        TextField content = new TextField("Content:", "", 30, TextField.ANY);
        this.append(content);
        //beginTime
        DateField beginTime = new DateField("Start", DateField.TIME);
        date.setDate(new java.util.Date());
        this.append(beginTime);
        //endTime
        DateField endTime = new DateField("End", DateField.TIME);
        date.setDate(new java.util.Date());
        this.append(endTime);



    }

    public void showMenu() {
        Add = new Command("Add", Command.OK, 2);
        Edit = new Command("Edit", Command.OK, 2);
        Delete = new Command("Delete", Command.OK, 2);

        Back = new Command("Back", Command.BACK, 1);
        this.addCommand(Back);
        this.addCommand(Add);
        this.addCommand(Edit);
        this.addCommand(Delete);


        this.setCommandListener(this);
    }
}
