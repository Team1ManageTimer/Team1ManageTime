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
public final class StatisticForm extends Form implements CommandListener {

    Display display;
    Command Back;
    Command Exit;

    public StatisticForm(String title) {
        super(title);
        this.drawGui();
        this.showMenu();
    }

    void setDisplay(Display display) {
        this.display = display;
    }
    
    //action
    public void commandAction(Command c, Displayable dsplbl) {
        if (c == Back) {
            RecordingTimeform f1 = new RecordingTimeform("Recording Time");
            f1.setDisplay(this.display);
            this.display.setCurrent(f1);
        }

    }
    
    protected void drawGui() {
        //Begin
        DateField date1 = new DateField("Begin Date: ", DateField.DATE);
        date1.setDate(new java.util.Date());
        this.append(date1);
        //End
        DateField date2 = new DateField("  End Date: ", DateField.DATE);
        date2.setDate(new java.util.Date());
        this.append(date2);
        //

    }

    public void showMenu() {
        Exit = new Command("Exit", Command.OK, 2);
        Back = new Command("Back", Command.BACK, 1);
        this.addCommand(Back);
        this.addCommand(Exit);


        this.setCommandListener(this);
    }
}
