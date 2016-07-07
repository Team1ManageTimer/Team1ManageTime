/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package managetime;

//import javax.microedition.lcdui.Form;
//import javax.microedition.lcdui.Command;
import javax.microedition.lcdui.*;
/**
 *
 * @author BaoNguyen
 */
public class Historyform extends Form implements CommandListener{
    Command Back;
    
    Display display;
    public Historyform(String title) {
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

            RecordTimeform f1 = new RecordTimeform("Record Time");
            f1.setDisplay(this.display);
            this.display.setCurrent(f1);
        }

   }
      protected void drawGui() {
//D
      }
      public void showMenu() {
        
        Back = new Command("Back", Command.BACK, 1);
        this.addCommand(Back);
        
        this.setCommandListener(this);
      }
}
