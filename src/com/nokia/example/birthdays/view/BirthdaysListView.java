/*
 * Copyright © 2012 Nokia Corporation. All rights reserved.
 * Nokia and Nokia Connecting People are registered trademarks of Nokia Corporation.
 * Oracle and Java are trademarks or registered trademarks of Oracle and/or its
 * affiliates. Other product and company names mentioned herein may be trademarks
 * or trade names of their respective owners.
 *
 * See LICENSE.TXT for license information.
 */
package com.nokia.example.birthdays.view;

import com.nokia.example.birthdays.BirthdayMidlet;
import com.nokia.example.birthdays.BirthdayMidlet.ExitListener;
import com.nokia.example.birthdays.data.BirthdayListModel;
import com.nokia.example.birthdays.data.PIMContactHandler.PIMNotAccessibleException;
import com.sun.lwuit.Command;
import com.sun.lwuit.Form;
import com.sun.lwuit.List;
import com.sun.lwuit.events.ActionEvent;

public class BirthdaysListView extends Form {

    private List birthdayList;
    private Command addCommand;
    private Command exitCommand;
    private BirthdayInsertionListener birthdayListener;
    private BirthdayListModel listModel;

    public static interface BirthdayInsertionListener {
        public void birthdayInsertionRequested();
    }

    public BirthdaysListView(BirthdayInsertionListener birthdayInsertionListener,
        final ExitListener exitListener) throws PIMNotAccessibleException {
        super("Birthdays");
        this.birthdayListener = birthdayInsertionListener;
        
        addCommands();
        createList();
    }
    
    private void createList() throws PIMNotAccessibleException {        
        birthdayList = new List();
        listModel = BirthdayListModel.getInstance();
        birthdayList.setModel(listModel);
        birthdayList.setRenderer(new BirthdayListItemRenderer());
        
        addComponent(birthdayList);
    }
    
    private void addCommands() {
        addCommand = new Command("Add") {
            public void actionPerformed(ActionEvent e) {
                birthdayListener.birthdayInsertionRequested();
            }
        };
        addCommand(addCommand);

        exitCommand = new Command("Exit") {
            public void actionPerformed(ActionEvent e) {
                BirthdayMidlet.getInstance().notifyDestroyed();
            }
        };
        addCommand(exitCommand);
        setBackCommand(exitCommand);        
    }
}
