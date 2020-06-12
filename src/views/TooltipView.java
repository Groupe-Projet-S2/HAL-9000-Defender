package views;

import javafx.scene.control.Tooltip;
import models.entities.bonus.AdBlock;
import models.entities.bonus.CKleaner;
import models.entities.bonus.SudVPN;
import models.entities.tower.*;


public class TooltipView {

    public TooltipView(){}

    public static Tooltip assignateTooltip(int id){
        Tooltip tooltip = new Tooltip();
        switch (id){
            case 1:
                tooltip.setText("Afast Tower\n" +
                        "Shoots in direction of the first targeted enemy\n" +
                        "Explosive projectiles\n" +
                        "Costs : "+ Afast.price);
                break;
            case 2:
                tooltip.setText("Goodwarebyte Tower\n" +
                        "Leaves a projectile on the path that slows the enemy\n" +
                        "Control projectiles\n" +
                        "Costs : "+ GoodwareBytes.price);
                break;
            case 3:
                tooltip.setText("IVG Tower\n" +
                        "Kills the enemies in its field\n" +
                        "Force field\n" +
                        "Costs : "+ GoodwareBytes.price);
                break;
            case 4:
                tooltip.setText("Kilobitdefender Tower\n" +
                        "Shoots targeted projectiles\n" +
                        "Damage projectiles\n" +
                        "Costs : "+ KiloBitDefender.price);
                break;
            case 5:
                tooltip.setText("Firewall Bonus\n" +
                        "Places a firewall on the path, stopping the enemies until its destruction\n" +
                        "Costs : "+ Firewall.price);
                break;
            case 6:
                tooltip.setText("Adblock Bonus\n" +
                        "Prevents the apparition of ads and ransoms\n" +
                        "Costs : "+ AdBlock.price);
                break;
            case 7:
                tooltip.setText("SudVPN Bonus\n" +
                        "Returns the enemies to their spawning point\n" +
                        "Costs : "+ SudVPN.price);
                break;
            case 8:
                tooltip.setText("CKleaner Bonus\n" +
                        "Removes every enemies\n" +
                        "Costs : "+ CKleaner.price);
                break;
            default: return null;
        }
        return tooltip;

    }
}
