/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package keskusteluakuvista.tui;

import keskusteluakuvista.ApplicationLogic;

/**
 *
 * @author aatukallio
 */
public class GuiController {
    private Gui view;
    private ApplicationLogic logic;
    
    public GuiController(Gui view,ApplicationLogic logic) {
        this.view = view;
        this.logic = logic;
        
    }
    
}
